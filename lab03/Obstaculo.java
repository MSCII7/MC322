
public class Obstaculo {
    private int posicaoX1, posicaoY1, altura;
    private int posicaoX2, posicaoY2;
    private int centroX, centroY;

    private TipoObstaculo tipo;

    public Obstaculo(int centroX, int centroY, TipoObstaculo tipo){
        this.centroX = centroX;
        this.centroY = centroY;

        this.posicaoX1 = centroX - tipo.getLargura()/2;
        this.posicaoX2 = centroX + tipo.getLargura()/2;

        this.posicaoY1 = centroY - tipo.getAltura()/2;
        this.posicaoY2 = centroY + tipo.getAltura()/2;

        this.altura = tipo.getAltura();

    }

    public void exibirObstaculo(){
        System.out.printf("Obstaculo do tipo " + tipo.name());
        System.out.printf(", com largura(x)" + tipo.getLargura());
        System.out.printf(", comprimento(y)" + tipo.getComprimento());
        System.out.printf(" e altura(z) " + tipo.getAltura());
        System.err.println(", com centro: " + this.centroX + ", " + this.centroY);

    }

    public int getPosicaoX1() {
        return posicaoX1;
    }

    public int getPosicaoY1() {
        return posicaoY1;
    }

    public int getAltura() {
        return altura;
    }

    public int getPosicaoX2() {
        return posicaoX2;
    }

    public int getPosicaoY2() {
        return posicaoY2;
    }

    public TipoObstaculo getTipo() {
        return tipo;
    }

    

}
