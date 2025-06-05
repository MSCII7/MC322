//Interface para robos que colocam entidades no ambiente
public interface Construtor {
    void construir(int x, int y, Ambiente amb) throws EntidadeInvalidaException;
    void setConstrucaoX(int x);
    void setConstrucaoY(int y);
}
