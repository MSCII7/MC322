package robos;

import ambiente.*;
import interfacesRobos.*;
import missao.*;

public abstract class AgenteInteligente extends Robo implements Sensoreavel, Comunicavel{
    protected Missao missao ;

    public AgenteInteligente(Missao missao, String nomeIn, int posXIn, int posYIn) {
        super(nomeIn, posXIn, posYIn);
        this.missao = missao;
    }

    public void definirMissao (Missao m) {
        this.missao = m ;
    }

    public boolean temMissao () {
        return missao != null ;
    }

    public abstract void executarMissao (Ambiente a) ;
}
