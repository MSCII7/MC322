package missao;

import ambiente.*;
import exceptions.RoboDesligadoException;

import java.util.ArrayList;
import robos.*;

//o robo com essa missao inverte o estado de todos os robos no raio do seu sensor de robos
//(liga os desligados, desliga os ligados)
/* 
public class MissaoEMP implements Missao{

    @Override
    public void executar(Robo r, Ambiente a) {
        if(r instanceof AgenteInteligente ai){
            try{
                ArrayList<Robo> robosProximos = ai.getRobosDentro(a);
                ArrayList
            }
            catch(RoboDesligadoException e){
                System.err.println(e.getMessage());
            }

        }
        
    }
}
    */
