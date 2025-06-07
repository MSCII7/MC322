package missao;

import ambiente.*;
import robos.*;

public interface  Missao {
    void executar (Robo r, Ambiente a);
    void ControleMovimento();
    void GerenciadorSensores();
    void ModuloComunicacao();
}
