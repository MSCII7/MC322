public class RoboTerrestrePanfletario extends RoboTerrestre implements Comunicavel, Sensoreavel{
    public RoboTerrestrePanfletario(String nome, int posX, int posY, int vMax){
        super(nome, posX, posY, vMax);
    }
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem){}
    @Override
    public void receberMensagens(){}
    @Override
    public void acionarSensores(Ambiente amb) throws RoboDesligadoException {
    }
    @Override
    public void executarTarefa() throws RoboDesligadoException {
    }
   

    
}
