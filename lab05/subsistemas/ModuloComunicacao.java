package subsistemas;

import exceptions.RoboDesligadoException;
import interfacesRobos.CentralComunicacao;
import interfacesRobos.Comunicavel;
import interfacesRobos.GrupoMensagemRobo;
import robos.Robo;


public class ModuloComunicacao {
    protected Comunicavel roboComunicavel;

    public ModuloComunicacao(Comunicavel r){
        this.roboComunicavel = r;
    }


    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException {
        if(((Robo)(roboComunicavel)).getEstado())
            CentralComunicacao.registrarMensagem(roboComunicavel, destinatario, mensagem);
        else
            throw new RoboDesligadoException();
    }

    public void receberMensagens() throws RoboDesligadoException {
        if(((Robo)(roboComunicavel)).getEstado()){
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


}
