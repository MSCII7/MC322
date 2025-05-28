//Interface para robos corretores, aqueles que reconhecem obstáculos moradias (casa, prédio)
public interface Referenciavel {
    void setReferencia(Obstaculo ref) throws TipoInconpativelException;
    int getPosXReferencia(); 
    int getPosYReferencia();
    void encontraReferencia();
    Obstaculo getReferencia();
}
