package robos;

import missao.*;
import ambiente.*;
public abstract class AgenteInteligente extends Robo{
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
