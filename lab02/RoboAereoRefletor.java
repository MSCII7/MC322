//o robo, ao mover, reflete nos limites de altitude superior e inferior se o deslocamento faz ele chegar neles
class RoboAereoRefletor extends RoboAereo{
    protected int altitudeMinima;

    public RoboAereoRefletor(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int altMin){
        super(nomeIn, posXIn, posYIn, posZIn, altMax);
        altitudeMinima = altMin;
    }

    //se o deslocamento deltaZ for colocar o robo acima do limite superior, ele sobe ate o limite e desce a 
    //quantia restante do deltaZ
    @Override public void subir(int deltaZ){
        if(posicaoZ + deltaZ < altitudeMaxima)
            this.posicaoZ += deltaZ;
        else
            this.posicaoZ = altitudeMaxima - Math.abs(deltaZ - (altitudeMaxima - posicaoZ));
    }

    //se o deslocamento deltaZ for colocar o robo fora do limite inferior, ele desce ate o limite e sobe a 
    //quantia restante do deltaZ
    @Override public void descer(int deltaZ){
        if(posicaoZ - deltaZ > altitudeMinima)
            this.posicaoZ -= deltaZ;
        else
            this.posicaoZ = altitudeMinima + Math.abs(deltaZ - (posicaoZ - altitudeMinima));
    }
}
