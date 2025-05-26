public class RoboTerrestreAmbientalista extends RoboTerrestre implements Construtor{
    private int construcaoX, construcaoY;
    public RoboTerrestreAmbientalista(String nomeIn, int posXIn, int posYIn, int vMax) {
            super(nomeIn, posXIn, posYIn, vMax);
            this.comandoTarefa = "pa";
            this.descricaoTarefa = " escolhe uma posicao para plantar uma arvore";
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
    }
    public void executarTarefa(int x, int y, Ambiente amb) {
        try {
            construir(x, y, amb);
        } catch (EntidadeInvalidaException e) {
            System.out.println("Erro ao adicionar arvore");
        }
    }
    @Override
    public void setConstrucaoX(int x){
        this.construcaoX = x;
    }
    @Override
    public void setConstrucaoY(int y){
        this.construcaoY = y;
    }
}
