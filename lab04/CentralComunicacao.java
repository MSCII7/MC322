
import java.util.ArrayList;

// Mensagens ficam armezenadas como um conjunto contendo o destinatario, o remetente e a mensagem em si

public class CentralComunicacao{
    private static ArrayList<GrupoMensagemRobo> gruposMensagem = new ArrayList<>();

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