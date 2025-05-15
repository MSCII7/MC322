import java.util.ArrayList;

public class RoboAereo extends Robo implements Comunicavel{
    int posicaoZ; //representa altitude(para seguir convecao de posicaoX e posicaoY)
    int altitudeMaxima;
    

    public RoboAereo(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax){
        super(nomeIn, posXIn, posYIn);
        posicaoZ = posZIn;
        altitudeMaxima = altMax;
        tipo = "Aereo";
    }

    public void subir(int deltaZ, Ambiente amb){
        int nova_z = posicaoZ + deltaZ;
        if (!colisao_robo(identificarRobos(amb), posicaoX, posicaoY, nova_z) && !colisao_obs(identificarObstaculos(amb), posicaoX, posicaoY, nova_z)){
            this.posicaoZ = nova_z;
        }else
            System.err.println("Alerta de colisao: movimento impedido");
    }

    public void descer(int deltaZ, Ambiente amb){
        int nova_z = posicaoZ - deltaZ;
        if (nova_z > 0 && !colisao_robo(identificarRobos(amb), posicaoX, posicaoY, nova_z) && !colisao_obs(identificarObstaculos(amb), posicaoX, posicaoY, nova_z)){
            this.posicaoZ = nova_z;
        }else
            System.err.println("Alerta de colisao: movimento impedido");
    }

    @Override public int getZ(){
        return this.posicaoZ;
    }

    public int getAltitudeMaxima(){
        return this.altitudeMaxima;
    }
    
    //Varios dos metodos do robo devem ser alterados para utilizar a posicao Z
    @Override public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + posicaoX + ", " + posicaoY + ", " + posicaoZ);
    }

    @Override 
    public void mover(int deltaX, int deltaY, Ambiente amb) {
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;
        if (!colisao_robo(identificarRobos(amb), novo_x, novo_y, posicaoZ) && !colisao_obs(identificarObstaculos(amb), novo_x, novo_y, posicaoZ)){
            if(posicaoX + deltaX > 0) //para nao ir para negativo
                this.posicaoX += deltaX;
            if(posicaoY + deltaY > 0) //para nao ir para negativo
                this.posicaoY += deltaY;
        }else
            System.err.println("Alerta de colisao: movimento impedido");
    }


    @Override public ArrayList<Obstaculo> identificarObstaculos(Ambiente amb){
        //Pegar obstaculos captados pelo sensor essencial SensorObstaculo
        return so.getObstaculos_dentro(this.posicaoX, this.posicaoY, this.posicaoZ, amb);

    }
    
    @Override public ArrayList<Robo> identificarRobos(Ambiente amb){
        //Pegar os robos captados pelo sensor essencial SensorRobo
        ArrayList<Robo> robos = sr.getRobos_dentro(this.posicaoX, this.posicaoY, this.posicaoZ, amb);
        robos.remove(this); //remove o proprio robo que contem o sensor
        return robos;

    }
    public boolean colisao_obs(ArrayList<Obstaculo> obs_dentro, int nova_x, int nova_y, int nova_z){
        for (Obstaculo obs : obs_dentro){
            if ((obs.getPosicaoX1() < nova_x) && (nova_x < obs.getPosicaoX2()) 
            && (obs.getPosicaoY1() < nova_y) && (nova_y < obs.getPosicaoY2())
            && (0 < nova_z) && (nova_z < obs.getAltura()))
                return true;
        }
        return false;
    }
    
    public boolean colisao_robo(ArrayList<Robo> robos_dentro, int nova_x, int nova_y, int nova_z){
        for (Robo robo : robos_dentro){
            if (robo instanceof RoboAereo ra){
                if (nova_x == ra.getX() && nova_y == ra.getY() && ra.getZ() == nova_z)
                    return true;
            } else if (nova_x == robo.getX() && nova_y == robo.getY() && nova_z == 0)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getNome() + "("+ this.tipo+"): " + getX() + ", " + getY()+ ", " + getZ() + ". altMax = "+ getAltitudeMaxima();
    }

    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        CentralComunicacao.registrarMensagem(this, destinatario, "Estou voando na altura " + posicaoZ);
    }

    @Override
    public void receberMensagem(){
        System.out.println("Mensagens recebidas:");
        for(GrupoMensagemRobo grupoMensagem : CentralComunicacao.getGrupos()){
            if(grupoMensagem.destinatario == this){
                System.out.println("Do robo " + grupoMensagem.remetente + ": " + grupoMensagem.mensagem);
            }
        }
    }
}