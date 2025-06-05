package ambiente;

public enum TipoEntidade{
    VAZIO('-'),
    OBSTACULO('#'),
    ROBO('@'),
    ROBOAEREO('v'),
    DESCONHECIDO('?');

    private final char representacao;

    TipoEntidade(char representacao){
        this.representacao = representacao;
    }

    public char getRepresentacao(){
        return this.representacao;
    }  
}