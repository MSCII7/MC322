
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
        if(posicaoX + deltaX > 0) //para nao ir para negativo
            this.posicaoX += deltaX;
        if(posicaoY + deltaY > 0) //para nao ir para negativo
            this.posicaoY += deltaY;
    }

    public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + this.posicaoX + ", " + this.posicaoY);
    }

    public void identificarObstaculos(Ambiente amb1, int raio){
        //para cada robo do ambiente, utiliza pitagoras da diferenca de posicao para ver se esta dentro do raio
        for (Robo robo : amb1.getRobos()){
            if(Math.pow(this.posicaoX - robo.posicaoX, 2) + Math.pow(this.posicaoY - robo.posicaoY, 2) < raio*raio){
                System.out.println("Robo " + robo.nome + " esta no raio " + raio);
            }
        }
    }
}
