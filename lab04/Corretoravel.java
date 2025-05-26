//Interface para robos corretores, aqueles que reconhecem obstáculos moradias (casa, prédio)
public interface Corretoravel {
    void acharCasas(Ambiente amb);
    void acharPredios(Ambiente amb);
    void imprimirCasas();
    void imprimirPredio();
    void imprimirMoradias();
}
