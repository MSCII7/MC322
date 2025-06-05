package robos;

import ambiente.*;
import exceptions.*;
import interfacesRobos.*;

public class RoboTerrestreMorador extends RoboTerrestre implements Referenciavel, Comunicavel{
    private Obstaculo moradia;

    public RoboTerrestreMorador(String nome, int posX, int posY, int vMax) {
        super(nome, posX,posY, vMax);

        this.tipo = "Morador";
        this.descricaoTarefa = " imprime a moradia de referencia (Referenciavel, Comunicavel)";
        this.comandoTarefa = "mr"; //mover para moradia
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
                if(grupoMensagem.getDestinatario() == this){
                    System.out.println("Do robo " + ((Robo)(grupoMensagem.getRemetente())).getNome() + ": " + grupoMensagem.getMensagem());
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
    public void executarTarefa(Obstaculo obstaculo) throws TipoIncompativelException{
        setReferencia(obstaculo);
    }    
    @Override
    public void encontraReferencia() {
        System.out.println("Robo " + this.getNome() + " procurando moradia...");
        System.out.println("Moradia encontra-se em: " + getPosX1Referencia() +" <= x <= "+ getPosX2Referencia() +" e "
                            + getPosY1Referencia() + " <= y <= " + getPosY2Referencia() +")");
    }

    @Override
    public void setReferencia(Obstaculo ref) throws TipoIncompativelException{
        TipoObstaculo refTipo = ref.getTipoObstaculo();

        if(refTipo == TipoObstaculo.CASA ||refTipo == TipoObstaculo.PREDIO){
            this.moradia = ref;
        }else
            throw new TipoIncompativelException("Obstaculo escolhido nao eh uma moradia!");
    }
    @Override
    public Obstaculo getReferencia(){
        return moradia;
    }



    @Override
    public int getPosX1Referencia() {
        return moradia.getX();
    }


    @Override
    public int getPosX2Referencia() {
        return moradia.getPosicaoX2();
    }


    @Override
    public int getPosY1Referencia() {
        return moradia.getY();
    }


    @Override
    public int getPosY2Referencia() {
        return moradia.getPosicaoY2();
    }
}
