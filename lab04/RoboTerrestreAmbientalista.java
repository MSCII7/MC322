public class RoboTerrestreAmbientalista extends RoboTerrestre implements Construtor{

    public RoboTerrestreAmbientalista(String nomeIn, int posXIn, int posYIn, int vMax) {
            super(nomeIn, posXIn, posYIn, vMax);
        }
    
    @Override
    public void construir(int x, int y, Ambiente amb) throws EntidadeInvalidaException{
        Obstaculo arvore = new Obstaculo(x, y, TipoObstaculo.ARVORE);
        try {
            amb.adicionarEntidade(arvore);
        } catch (EntidadeInvalidaException e) {
            System.out.println("Entidade Invalida");
        }
    }

    @Override
    public void executarTarefa(){
        System.out.println("Erro, envie as coordenadas e o ambiente");
    }
    public void executarTarefa(int x, int y, Ambiente amb) {
        try {
            construir(x, y, amb);
        } catch (EntidadeInvalidaException e) {
            System.out.println("Erro ao adicionar arvore");
        }
    }

}
