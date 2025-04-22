public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;
    //Adicionar o atributo velocidadeMaxima no construtor
    public RoboTerrestre(String nomeIn, int posXIn, int posYIn, Ambiente amb, int vMax){
        super(nomeIn, posXIn, posYIn, amb);
        velocidadeMaxima = vMax;       
    }
    //Adicionar a verificação da velocidade para a locomoção do robo
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        
        if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
        }
        else{
            System.out.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido");
        }
    }
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }
}

