
//robo aereo com velocidade maxima de subida/descida(nao move se a velocidade for acima disso)
class RoboAereoDevagar extends RoboAereo{
    protected int velocidadeZMax;

    public RoboAereoDevagar(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int vZMax){
        super(nomeIn, posXIn, posYIn, posZIn, altMax);
        velocidadeZMax = vZMax;
    }
    @Override public void subir(int deltaZ){
        if(deltaZ < velocidadeZMax)
            this.posicaoZ += deltaZ;
        else
            System.out.println("Velocidade Z do Robo " + nome + " acima do maximo: movimento impedido");
    }

    @Override public void descer(int deltaZ){
        if(deltaZ < velocidadeZMax)
            this.posicaoZ -= deltaZ;
        else
            System.out.println("Velocidade Z do Robo " + nome + " acima do maximo: movimento impedido");
    }

    //maximo que pode subir sem passar o limite de velocidade nem passar a altura
    public int maxSubida(){
        if(posicaoZ + velocidadeZMax < altitudeMaxima)
            return velocidadeZMax;
        else
            return (altitudeMaxima - posicaoZ);
    }
}
