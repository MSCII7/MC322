
public class Obstaculo {
    private int posicaoX1, posicaoY1, altura;
    private int posicaoX2, posicaoY2;
    private TipoObstaculo tipo;

    public Obstaculo(int centroX, int centroY, TipoObstaculo tipo){
        this.posicaoX1 = centroX - tipo.getLargura()/2;
        this.posicaoX2 = centroX + tipo.getLargura()/2;

        this.posicaoY1 = centroY - tipo.getAltura()/2;
        this.posicaoY2 = centroY + tipo.getAltura()/2;

        this.altura = tipo.getAltura();

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
