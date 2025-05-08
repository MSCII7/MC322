public enum TipoEntidade{
    VAZIO('-'),
    OBSTACULO('#'),
    ROBO('@'),
    DESCONHECIDO('?');

    private final char representacao;

    TipoEntidade(char representacao){
        this.representacao = representacao;
    }

    public char getRepresentacao(){
        return this.representacao;
    }  
}