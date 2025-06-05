public interface Entidade{
    int getX();
    int getY();
    int getZ();
    void moverPara(int novoX, int novoY, int novoZ) throws NaoAereoException, RoboDesligadoException;
    TipoEntidade getTipo();
    String getDescricao();
    char getRepresentacao();

}
