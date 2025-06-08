package robos;
//robo aereo que nao sobe nem desce se for ficar a uma certa distancia em z de outros robos aereos
import ambiente.*;
import exceptions.*;
import interfacesRobos.*;
import java.util.ArrayList;
import sensores.Sensor;

public class RoboAereoConsciente extends RoboAereo implements Sensoreavel{
    protected int distanciaMin;
    protected int novaZ;
    protected ArrayList<Robo> robosProximos;
    protected ArrayList<Sensor> sensores;
    protected String mensagem;
    
    public RoboAereoConsciente(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int distMin){
        super(nomeIn, posXIn, posYIn,posZIn, altMax);
        distanciaMin = distMin;

        so.setRaio(distMin);
        sr.setRaio(distMin);

        robosProximos = new ArrayList<>();

        tipo  = "Consciente";
        
        mensagem = "Ei, saia do caminho!";
        comandoTarefa = "ctc"; //comando tarefa consciente
        descricaoTarefa = " imprime todos os robos proximos e manda a mensagem '" + mensagem + "' para os comunicaveis (Comunicavel, Sensoreavel)";
    }

    @Override public void subir(int deltaZ){
        novaZ = this.posicaoZ + deltaZ;
        this.posicaoZ = novaZ;
    }

    @Override public void descer(int deltaZ){
        novaZ = this.posicaoZ - deltaZ;
        this.posicaoZ = novaZ;
    }

    //incrementa/subtrai da distancia minima que ele pode ficar de outros robos aereos no eixo z
    public void mudarDistancia(int deltaDist){
        if(distanciaMin + deltaDist > 0){
            distanciaMin += deltaDist;
            so.setRaio(distanciaMin);
            sr.setRaio(distanciaMin);
        }
        else{
            System.out.println("Erro: tentativa de tornar distancia minima 0 ou negativa");
        }
    }

    @Override
    public void acionarSensores(Ambiente amb) throws RoboDesligadoException{
        if(this.getEstado() == true){
            robosProximos = sr.getRobos_dentro(posicaoX, posicaoY, novaZ, amb);
            //remove ele mesmo da lista de robos proximos
            robosProximos.remove(this);
        }
        else{
            throw new RoboDesligadoException();
        }
    } 

    @Override
    public ArrayList<Robo> getRobosDentro(Ambiente amb) throws RoboDesligadoException{
        if(this.getEstado() == true){
            ArrayList<Robo> robosDentro = sr.getRobos_dentro(posicaoX, posicaoY, posicaoZ, amb);
            //remove ele mesmo da lista de robos proximos
            robosDentro.remove(this);
            return robosDentro;
        }
        else{
            throw new RoboDesligadoException();
        }
    }

    @Override
    public ArrayList<Obstaculo> getObstaculosDentro(Ambiente amb) throws RoboDesligadoException{
        if(this.getEstado() == true){
            ArrayList<Obstaculo> obstDentro = so.getObstaculos_dentro(posicaoX, posicaoY, posicaoZ, amb);
            return obstDentro;
        }
        else{
            throw new RoboDesligadoException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ". distMin = " + this.distanciaMin;
    }

    //como esse envia, ele sobrescreve o metodo vazio de envio de mensagem da classe superior
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException{
        if(ligado)
            CentralComunicacao.registrarMensagem(this, destinatario, mensagem);
        else
            throw new RoboDesligadoException();
    }

    @Override 
    public void executarTarefa() throws RoboDesligadoException{
        System.out.println("Mandando mensagem e listando robos...");
        System.out.println("----Utilizando sensor de robos, de raio " + sr.getRaio() + ": ----");

        for(Robo robo : robosProximos){
            robo.exibirPosicao();
            if(robo instanceof Comunicavel comunicavel){
                try{
                    enviarMensagem(comunicavel, mensagem);
                    System.out.println("Enviou mensagem para o robo comunicavel " + robo.getNome());
                }
                catch(RoboDesligadoException e){
                    throw e;
                }
            }
            else{
                System.out.println(robo.getNome() + " nao eh comunicavel: nao recebeu mensagem");
            }
        }
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void removerSensor(Sensor s) {
        for (Sensor sensor : sensores){
            if (sensor == s)
                sensores.remove(s);
        }
    }

    @Override
    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }
    
}
