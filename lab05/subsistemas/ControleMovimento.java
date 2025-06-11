package subsistemas;

import ambiente.*;
import exceptions.NaoAereoException;
import exceptions.RoboDesligadoException;
import robos.*;

public class ControleMovimento {
    protected AgenteInteligente roboControlado;
    protected Ambiente amb;
    
    public ControleMovimento(AgenteInteligente r){
        this.roboControlado = r;

    }
    public void mover(int delX, int delY, int delZ, Ambiente amb) throws RoboDesligadoException, NaoAereoException{
        try{
            int novaX = roboControlado.getX()+delX;
            int novaY = roboControlado.getY()+delY;
            int novaZ = roboControlado.getZ()+delZ;
            amb.moverEntidade(roboControlado, novaX, novaY, novaZ);
            roboControlado.moverPara(novaX, novaY, novaZ);
        }catch (RoboDesligadoException | NaoAereoException e){

        }
    
    }
}
