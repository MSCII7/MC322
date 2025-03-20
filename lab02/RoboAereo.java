public class RoboAereo extends Robo{
    int posicaoZ;
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
        this.posicaoZ -= deltaZ;
    }

    public int getPosZ(){
        return this.posicaoZ;
    }

    public int getAltitudeMaxima(){
        return this.altitudeMaxima;
    }
}
