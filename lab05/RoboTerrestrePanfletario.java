public class RoboTerrestrePanfletario extends RoboTerrestre implements Comunicavel, Sensoreavel{
    private String mensagem;
    
    public RoboTerrestrePanfletario(String nome, int posX, int posY, int vMax){
        super(nome, posX, posY, vMax);
        this.sr.setRaio(100);

        this.tipo = "Panfletario";
        this.descricaoTarefa = " envia propaganda imobiliaria para os robos moradores proximos (Comunicavel, Sensoreavel)";
        this.comandoTarefa = "ep";
        this.mensagem = "Atencao, novas ofertas de casas e apartamentos, todos murados e bem arborizados, em regioes proximas de voce\nAproveite, venha comprar na Imoveis Prateados!!";
    }

    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException{
        if(ligado)
            CentralComunicacao.registrarMensagem(this, destinatario, mensagem);
        else
            throw new RoboDesligadoException();
    }

    //so envia mensagens
    @Override
    public void receberMensagens() throws RoboDesligadoException{
    }

    @Override
    public void acionarSensores(Ambiente amb) throws RoboDesligadoException {
        this.sr.monitorar(posicaoX, posicaoY, posicaoX, amb);
    }
    @Override
    public void executarTarefa() throws RoboDesligadoException {
        System.out.println("Propaganda a ser enviada: ");
        System.out.println(this.mensagem);
        System.out.println("");

        for (Robo r : this.sr.getRobos_dentro()){
            if (r instanceof RoboTerrestreMorador m)
                try{
                    enviarMensagem(m, mensagem);
                    System.out.println("Enviou mensagem para " + m.getNome());
                }
                catch(RoboDesligadoException e){
                    throw e;
                }
        }
        System.out.println("--Finalizou envio--");
    }
   

    
}
