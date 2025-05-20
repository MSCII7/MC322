public interface Entidade{
    int getX();
    int getY();
    int getZ();
    void moverPara(int novoX, int novoY, int novoZ);
    TipoEntidade getTipo();
    String getDescricao();
    char getRepresentacao();

}
