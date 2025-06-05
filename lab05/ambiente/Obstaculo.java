package ambiente;

public class Obstaculo implements Entidade{
    private final int posicaoX1, posicaoY1, altura;
    private final int posicaoX2, posicaoY2;

    private final TipoObstaculo tipo;

    public Obstaculo(int posX1, int posY1, TipoObstaculo tipo){

        this.posicaoX1 = posX1;
        this.posicaoX2 = posX1 + tipo.getLargura();

        this.posicaoY1 = posY1;
        this.posicaoY2 = posY1 + tipo.getComprimento();

        this.altura = tipo.getAltura();

        this.tipo = tipo;

    }

    public void exibirObstaculo(){
        System.out.printf("Obstaculo do tipo " + tipo.name());
        System.out.printf(", com largura(x) " + tipo.getLargura());
        System.out.printf(", comprimento(y) " + tipo.getComprimento());
        System.out.printf(" e altura(z) " + tipo.getAltura());
        System.err.println(", com posicao inicial: " + this.posicaoX1 + ", " + this.posicaoY1);

    }

    public int getPosicaoX2() {
        return posicaoX2;
    }

    public int getPosicaoY2() {
        return posicaoY2;
    }

    public TipoObstaculo getTipoObstaculo() {
        return tipo;
    }
   
    @Override
    public String toString() {
        return "Obstaculo do tipo " + tipo.name()+", com largura(x) " + tipo.getLargura()+", comprimento(y) " + tipo.getComprimento()+
        " e altura(z) " + tipo.getAltura()+", com posicao inicial: " + this.posicaoX1 + ", " + this.posicaoY1;
    }

    //implementacao de Entidade

    @Override
    public int getX(){
        return posicaoX1;
    }

    @Override
    public int getY(){
        return posicaoY1;
    }

    @Override
    public int getZ(){
        return altura;
    }

    @Override
    public char getRepresentacao(){
        return TipoEntidade.OBSTACULO.getRepresentacao();
    }

    @Override
    public TipoEntidade getTipo(){
        return TipoEntidade.OBSTACULO;
    }

    @Override
    public String getDescricao(){
        return "Obstaculo do tipo " + tipo.name()+", com largura(x) " + tipo.getLargura()+", comprimento(y) " + tipo.getComprimento()+
        " e altura(z) " + tipo.getAltura()+", com posicao inicial: " + this.posicaoX1 + ", " + this.posicaoY1;
    }

    //implementa o metodo da interface, nesse caso sem fazer nada (nao faz sentido mover obstaculo)
    @Override
    public void moverPara(int novoX, int novoY, int novoZ){}

}
