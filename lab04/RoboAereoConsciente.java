//robo aereo que nao sobe nem desce se for ficar a uma certa distancia em z de outros robos aereos
import java.util.ArrayList;

class RoboAereoConsciente extends RoboAereo implements Sensoreavel{
    protected int distanciaMin;
    protected int novaZ;
    protected ArrayList<Robo> robosProximos;
    protected ArrayList<Obstaculo> obstaculosProximos;
    
    public RoboAereoConsciente(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int distMin){
        super(nomeIn, posXIn, posYIn,posZIn, altMax);
        distanciaMin = distMin;

        so.setRaio(distMin);
        sr.setRaio(distMin);

        robosProximos = new ArrayList<>();
        obstaculosProximos = new ArrayList<>();

        tipo  = "Consciente";
    }

    @Override public void subir(int deltaZ){
        //Ver se ha robos ou obstaculos dentro do raio minimo 
        novaZ = this.posicaoZ + deltaZ;
        if (obstaculosProximos.isEmpty() && robosProximos.isEmpty()) 
            this.posicaoZ = novaZ;
        else
            System.out.println("Robo " + nome + " nao moveu: ficaria muito proximo de obstaculos ou robos");
    }

    @Override public void descer(int deltaZ){
        novaZ = this.posicaoZ - deltaZ;
        if (obstaculosProximos.isEmpty() && robosProximos.isEmpty()) 
            this.posicaoZ = novaZ;
        else
            System.out.println("Robo " + nome + " nao moveu: ficaria muito proximo de obstaculos ou robos");
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
            obstaculosProximos = so.getObstaculos_dentro(posicaoX, posicaoY, novaZ, amb);
            robosProximos = sr.getRobos_dentro(posicaoX, posicaoY, novaZ, amb);
        }
        else{
            throw new RoboDesligadoException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ". distMin = " + this.distanciaMin;
    }
    
}
