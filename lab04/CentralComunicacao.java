
import java.util.ArrayList;

public class CentralComunicacao{
    private static ArrayList<GrupoMensagemRobo> gruposMensagem;

    static public void registrarMensagem(Comunicavel remetente, Comunicavel dest, String mensagem){
        gruposMensagem.add(new GrupoMensagemRobo(remetente, dest, mensagem));
    }

    static public void exibirMensagens(){
        for(GrupoMensagemRobo grupo  : gruposMensagem){
            System.out.println(grupo.getMensagem());
        }
    }

    static public ArrayList<GrupoMensagemRobo> getGrupos(){
        return gruposMensagem;
    }

    static public void removerMensagem(GrupoMensagemRobo grupoMensagem){
        gruposMensagem.remove(grupoMensagem);
    }
}

class GrupoMensagemRobo{
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