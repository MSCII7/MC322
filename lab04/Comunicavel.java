public interface Comunicavel {
    enviarMensagem(Comunicavel destinatario, String mensagem);
    receberMensagem(String mensagem);
}