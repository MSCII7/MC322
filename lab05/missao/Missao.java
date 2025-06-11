package missao;

import ambiente.*;
import robos.*;

public interface  Missao {
    void executar (AgenteInteligente ai, Ambiente a);

    //para atribuir a missao.
    //por exemplo, podemos ter "<COMANDOMISSAO> <identificador robo> (x, y, z)"
    void imprimirFormatoMissao();
}
