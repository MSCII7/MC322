public class RoboTerrestrePanfletario extends RoboTerrestre implements Comunicavel, Sensoreavel{
    public RoboTerrestrePanfletario(String nome, int posX, int posY, int vMax){
        super(nome, posX, posY, vMax);
        this.sr.setRaio(100);
    }
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem){}
    @Override
    public void receberMensagens(){}
    @Override
    public void acionarSensores(Ambiente amb) throws RoboDesligadoException {
        this.sr.monitorar(posicaoX, posicaoY, posicaoX, amb);
    }
    @Override
    public void executarTarefa() throws RoboDesligadoException {
        String mensagem = "Atencao, novas ofertas de casas e apartamentos, todos murados e bem arborizados, em regioes proximas de voce\nAproveite, venha comprar na Imoveis Prateados!!";
        for (Robo r : this.sr.getRobos_dentro()){
            if (r instanceof RoboTerrestreMorador m)
                enviarMensagem(m, mensagem);
        }
    }
   

    
}
