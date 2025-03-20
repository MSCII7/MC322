public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;

    public RoboTerrestre(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn);
        velocidadeMaxima = vMax;       
    }

    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        
        if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
        }
    }
}
