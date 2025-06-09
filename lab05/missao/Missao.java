package missao;

import ambiente.*;
import robos.*;

public interface  Missao {
    void executar (Robo r, Ambiente a);

    //para atribuir a missao.
    //por exemplo, podemos ter "<COMANDOMISSAO> <identificador robo> (x, y, z)"
    void imprimirFormatoMissao();
}
