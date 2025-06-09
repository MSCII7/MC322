package subsistemas;

import ambiente.*;
import robos.*;

public class ControleMovimento {
    protected Robo roboControlado;
    protected Ambiente amb;
    
    public ControleMovimento(Robo r, Ambiente a){
        this.roboControlado = r;
        this.amb = a;
    }
    
}
