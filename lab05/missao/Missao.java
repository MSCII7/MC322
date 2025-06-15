package missao;

import ambiente.*;
import robos.*;

public interface  Missao {
    void executar (AgenteInteligente ai, Ambiente a);

    String getComando(); //pega o comando da missao para verificar se eh igual
    //formata a string dividida do comando em uma missao do mesmo tipo. Utilizada para tornar mais modular
    Missao formatarParaMissao(String[] comDividido);

    //string com o formato do comando para atribuir a missao a um agente
    //a string eh impressa junto com o comando de atribuicao de missao no menu interativo
    String formatoEntrada();

    //string com descricao do que a missao faz
    String getDescricao();

    //string com um exemplo de comando de atribuicao para essa missao
    String getExemplo();
}
