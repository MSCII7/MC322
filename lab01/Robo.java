public class Robo{
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String nomeIn, int posXIn, int posYIn){
        nome = nomeIn;
        posicaoX = posXIn;
        posicaoY = posYIn;
    }

    public void mover(int deltaX, int deltaY){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public void exibirPosicao(){
        System.out.println("posicao: " + posicaoX + ", " + posicaoY);
    }
}