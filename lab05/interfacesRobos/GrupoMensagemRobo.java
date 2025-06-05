package interfacesRobos;

public class GrupoMensagemRobo{
    Comunicavel remetente;
    Comunicavel destinatario;
    String mensagem;

    public GrupoMensagemRobo(Comunicavel remetente, Comunicavel dest, String msg){
        this.mensagem = msg;
        this.remetente = remetente;
        this.destinatario = dest;
    }

    public Comunicavel getRemetente() {
        return remetente;
    }

    public Comunicavel getDestinatario() {
        return destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

}
