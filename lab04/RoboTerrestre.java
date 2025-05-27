public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;
    //Adicionar o atributo velocidadeMaxima no construtor
    public RoboTerrestre(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn);
        velocidadeMaxima = vMax;       
        this.so.setRaio(vMax);
        this.sr.setRaio(vMax);
        tipo = "Terrestre";
    }
    //Adicionar a verificação da velocidade para a locomoção do robo
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;

        if(moduloQuadrado <= velocidadeMaxima*velocidadeMaxima){

            if(posicaoX + deltaX > 0) //para nao ir para negativo
                this.posicaoX += deltaX;

            if(posicaoY + deltaY > 0) //para nao ir para negativo
                this.posicaoY += deltaY;
                
        } else{
            System.out.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido");
        }
    }
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }
    @Override
    public String toString() {
        return super.toString() + ". vMax = "+ getVelocidadeMaxima();
    }
    @Override
    public void executarTarefa() throws RoboDesligadoException {
        if (this.ligado)
            throw new RoboDesligadoException();
    }

}

