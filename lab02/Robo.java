
public class Robo{
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected String direcao;

    public Robo(String nomeIn, int posXIn, int posYIn){
        nome = nomeIn;
        posicaoX = posXIn;
        posicaoY = posYIn;
    }

    public int getPosX(){
        return this.posicaoX;
    }

    public int getPosY(){
        return this.posicaoY;
    }

    public void mover(int deltaX, int deltaY){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + this.posicaoX + ", " + this.posicaoY);
    }

    public void identificarObstaculos(Ambiente amb1, int raio){
        for (Robo robo : amb1.getRobos()){
            if(Math.pow(this.posicaoX - robo.posicaoX, 2) + Math.pow(this.posicaoY - robo.posicaoY, 2) < raio*raio){
                System.out.println("Robo " + robo.nome + " esta no raio " + raio);
            }
        }
    }
}