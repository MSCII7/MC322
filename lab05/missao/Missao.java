import robos;
import ambiente;
public interface  Missao {
    void executar (Robo r, Ambiente a);
    void ControleMovimento();
    void GerenciadorSensores();
    void ModuloComunicacao();
}
