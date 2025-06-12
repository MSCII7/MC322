package missao;

import ambiente.*;
import robos.*;

public interface  Missao {
    void executar (AgenteInteligente ai, Ambiente a);

    String getComando(); //pega o comando da missao para verificar se eh igual
    //formata a string dividida do comando em uma missao do mesmo tipo. Utilizada para tornar mais modular
    Missao formatarParaMissao(String[] comDividido);

    String formatoEntrada();

    String getDescricao();
}
