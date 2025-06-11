package missao;

import ambiente.*;
import arquivos.Salvar;
import exceptions.RoboDesligadoException;

import java.util.ArrayList;
import robos.*;

//o robo com essa missao inverte o estado de todos os robos no raio do seu sensor de robos
//(liga os desligados, desliga os ligados)

public class MissaoEMP implements Missao{

    public MissaoEMP(){
        
    }

    @Override
    public void executar(AgenteInteligente ai, Ambiente a) {
        String msgMissao = "Rodando Missao EMP com robo " + ai.getNome() + ": \n";
        try{
            ArrayList<Robo> robosProximos = ai.getRobosDentro(a);
            msgMissao += "Ativou o sensor de robos do " + ai.getNome() + "\n";
            for(Robo r : robosProximos){
                if(r.getEstado() == true){
                    r.desligar();
                    msgMissao += "Desligou o robo " + r.getNome() + "\n";
                }
                else{
                    r.ligar();
                    msgMissao += "Ligou o robo " + r.getNome() + "\n";
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
    public void imprimirFormatoMissao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
