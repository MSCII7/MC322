
public class RoboTerrestreMorador extends RoboTerrestre implements Referenciavel, Comunicavel{
    private Obstaculo moradia;
    public RoboTerrestreMorador(String nome, int posX, int posY, int vMax) {
        super(nome, posX,posY, vMax);
    }
    
   
    @Override
    //Como o intuito eh apenas receber pelo correio, fica vazio
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
    }

    @Override
    public void receberMensagens() throws RoboDesligadoException{
        if(ligado){
            System.out.println("Mensagens recebidas:");
            for(GrupoMensagemRobo grupoMensagem : CentralComunicacao.getGrupos()){
                if(grupoMensagem.destinatario == this){
                    System.out.println("Do robo " + ((Robo)(grupoMensagem.remetente)).getNome() + ": " + grupoMensagem.mensagem);
                }
            }
        }
        else
            throw new RoboDesligadoException();
    }


    @Override
    public void executarTarefa() {
        encontraReferencia();
    }
    
    @Override
    public void encontraReferencia(){
        //Fica a uma distancia de 5 passos da moradia
        int deltaX, deltaY;
        if (this.posicaoX > getPosXReferencia()){
            deltaX = this.posicaoX - getPosXReferencia() - moradia.getTipoObstaculo().getComprimento()/2 - 5;
        }else
            deltaX = this.posicaoX + getPosXReferencia() + moradia.getTipoObstaculo().getComprimento()/2 + 5;
        if (this.posicaoY > getPosYReferencia()){
            deltaY = this.posicaoY - getPosYReferencia() - moradia.getTipoObstaculo().getLargura()/2 - 5;
        }else
            deltaY = this.posicaoY + getPosYReferencia() + moradia.getTipoObstaculo().getLargura()/2 + 5;
        mover(deltaX, deltaY);
    }  


    @Override
    public void setReferencia(Obstaculo ref) {
        this.moradia = ref;
    }
    @Override
    public Obstaculo getReferencia(){
        return moradia;
    }

    @Override
    public int getPosXReferencia() {
        return (this.moradia.getX()+this.moradia.getPosicaoX2())/2;
    }
    @Override
    public int getPosYReferencia() {
        return (this.moradia.getY()+this.moradia.getPosicaoY2())/2;
    }
}
