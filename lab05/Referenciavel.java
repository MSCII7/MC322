//Interface para robos corretores, aqueles que reconhecem obstáculos moradias (casa, prédio)
public interface Referenciavel {
    void setReferencia(Obstaculo ref) throws TipoIncompativelException;
    int getPosX1Referencia(); 
    int getPosX2Referencia();
    int getPosY1Referencia();
    int getPosY2Referencia();
    void encontraReferencia();
    Obstaculo getReferencia();
}
