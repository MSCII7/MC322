package missao;

import ambiente.*;
import arquivos.Salvar;
import exceptions.RoboDesligadoException;
import interfacesRobos.Comunicavel;
import java.util.ArrayList;
import robos.*;

//o robo com essa missao inverte o estado de todos os robos no raio do seu sensor de robos
//(liga os desligados, desliga os ligados) e envia uma mensagem avisando isso aos comunicaveis

public class MissaoEMP implements Missao{

    String comandoMissao = "EMP";
    
    public MissaoEMP(){
        //A missao nao tem argumentos adicionais
    }

    @Override
    public void executar(AgenteInteligente ai, Ambiente a) {
        String msgMissao = "Rodando Missao EMP com robo " + ai.getNome() + 
        ", com raio " + ai.getGerenciadorSensores().getSensorRobos().getRaio() +": \n";

        String msgRobo = "";

        try{
            ArrayList<Robo> robosProximos = ai.getRobosDentro(a);
            msgMissao += "Ativou o sensor de robos do " + ai.getNome() + "\n";
            for(Robo r : robosProximos){
                if(r.getEstado() == true){
                    r.desligar();
                    msgMissao += "Desligou o robo " + r.getNome() + "\n";
                    if(r instanceof Comunicavel rComunicavel){
                        msgRobo = "Foi desligado por pulso EMP do robo " + ai.getNome();
                        ai.getModuloComunicacao().enviarMensagem(rComunicavel, msgRobo);
                        msgMissao += "Enviou mensagem para o robo " + r.getNome() + "\n";
                    }
                }
                else{
                    r.ligar();
                    msgMissao += "Ligou o robo " + r.getNome() + "\n";
                    if(r instanceof Comunicavel rComunicavel){
                        msgRobo = "Foi ligado por pulso EMP do robo " + ai.getNome();
                        ai.getModuloComunicacao().enviarMensagem(rComunicavel, msgRobo);
                        msgMissao += "Enviou mensagem para o robo " + r.getNome() + "\n";
                    }
                }
            }
        }
        catch(RoboDesligadoException e){
            msgMissao += e.getMessage() + "\n";
        }

        Salvar.escreverMissao(msgMissao);

        System.out.println("---Escreveu no Arquivo---");
        System.out.println(msgMissao);
        System.out.println("--------------------------");
    }


    @Override
    public String getComando() {
        return comandoMissao;
    }

    @Override
    public Missao formatarParaMissao(String[] comDividido){
        return new MissaoEMP();
    }

    //como a missao eh so ram EMP, nao tem nada mais que o proprio comando no formato de entrada
    @Override
    public String formatoEntrada(){
        return comandoMissao;
    }

    @Override
    public String getDescricao(){
        return "----Missao EMP : inverte o estado de todos os robos no entorno e envia mensagem para os comunicaveis avisando isso----";
    }

    @Override
    public String getExemplo(){
        return comandoMissao;
    }
    

    
}
