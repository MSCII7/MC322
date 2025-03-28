public class RoboAereo extends Robo{
    int posicaoZ; //representa altitude(para seguir convecao de posicaoX e posicaoY)
    int altitudeMaxima;

    public RoboAereo(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax){
        super(nomeIn, posXIn, posYIn);
        posicaoZ = posZIn;
        altitudeMaxima = altMax;
    }

    public void subir(int deltaZ){
        this.posicaoZ += deltaZ;
    }

    public void descer(int deltaZ){
        if(posicaoZ - deltaZ > 0) //para nao ir para negativo
            this.posicaoZ -= deltaZ;
    }
    
    @Override public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + posicaoX + ", " + posicaoY + ", " + posicaoZ);
    }

    public int getPosZ(){
        return this.posicaoZ;
    }

    public int getAltitudeMaxima(){
        return this.altitudeMaxima;
    }
}