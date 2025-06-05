public interface Comunicavel {
    void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;
    void receberMensagens() throws RoboDesligadoException;
}