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

    public void subir(int deltaZ){
        int nova_z = posicaoZ + Math.abs(deltaZ);
        this.posicaoZ = nova_z;
    }

    public void descer(int deltaZ){
        int nova_z = posicaoZ - Math.abs(deltaZ);
        this.posicaoZ = nova_z;
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
    public void moverPara(int novoX, int novoY, int novoZ){
        mover(novoX - posicaoX, novoY - posicaoY);
        int deltaZ = novoZ - posicaoZ;

        if(deltaZ >= 0) 
            subir(deltaZ);
        else 
            descer(deltaZ);
    }

    @Override 
    public void mover(int deltaX, int deltaY){
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;

        if(novo_x > 0) //para nao ir para negativo
            this.posicaoX += deltaX;
        if(novo_y > 0) //para nao ir para negativo
            this.posicaoY += deltaY;
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

    @Override
    public void executarTarefa(){
        
    }
}