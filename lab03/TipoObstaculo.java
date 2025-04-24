public enum TipoObstaculo {

    MURO(10, 2, 3),
    MEGAMURO(30, 2, 3),
    ARVORE(2, 2, 8),
    PREDIO(10, 10, 20),
    LIXEIRA(1, 1, 1),
    CASA(10, 10, 10);

    private final int largura, comprimento, altura;

    TipoObstaculo(int largura, int comprimento, int altura){
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    public int getLargura(){
        return largura;
    }

    public int getComprimento(){
        return comprimento;
    }

    public int getAltura(){
        return altura;
    }


}