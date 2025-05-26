
public abstract class Robo implements Entidade{
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;

    protected String tipo;
    protected String id;

    //String do comando utilizado para tarefas especificas
    protected String comandoTarefa;
    //Quando estiver selecionado robo, comandoTarefa + descricaoTarefa sera impresso para o usuario
    //saber utilizar o comando de tarefas especificas
    protected String descricaoTarefa;

    protected boolean ligado;
    TipoEntidade tipoEntidade;

    protected SensorRobos sr;
    protected SensorObstaculos so;
    
    public Robo(String nomeIn, int posXIn, int posYIn){
        nome = nomeIn;
        posicaoX = posXIn;
        posicaoY = posYIn;

        tipo = "Simples";

        //Ou seja, sera impresso "et executa a tarefa do robo"
        comandoTarefa = "et";
        descricaoTarefa = " executa a tarefa do robo";


        ligado = true;
        tipoEntidade = TipoEntidade.ROBO;

        //Adicionar sensores essenciais para a movimentacao do robo
        sr = new SensorRobos(50);
        so = new SensorObstaculos(50);
    }

    @Override public int getX(){
        return this.posicaoX;
    }

    @Override public int getY(){
        return this.posicaoY;
    }

    @Override public int getZ(){
        return 0;
    }

    @Override public TipoEntidade getTipo(){
        return tipoEntidade;
    }

    @Override public char getRepresentacao(){
        return tipoEntidade.getRepresentacao();
    }

    @Override public String getDescricao(){
        return "Robo -_-";
    }

    public String getNome(){
        return this.nome;
    }

    public void ligar(){
        this.ligado = true;
    }

    public void desligar(){
        this.ligado = false;
    }

    public boolean getEstado(){
        return this.ligado;
    }

    //metodo abstrato para tarefas
    public abstract void executarTarefa();

    @Override
    public void moverPara(int novoX, int novoY, int novoZ) throws NaoAereoException{
        //Robo nao eh aereo, logo nao pode ir para Z diferente de 0. No aereo, esse metodo sera sobrescrito e a excecao removida
        if(novoZ != 0){
            throw new NaoAereoException();
        }
        else{
            mover(novoX - posicaoX, novoY - posicaoY);
        }

    }

    public void mover(int deltaX, int deltaY){
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;

        if(novo_x > 0) //para nao ir para negativo
            this.posicaoX += deltaX;
        if(novo_y > 0) //para nao ir para negativo
            this.posicaoY += deltaY;
    }

    public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + this.posicaoX + ", " + this.posicaoY);
    }
    
    @Override
    public String toString() {
        return getNome() + "("+ this.tipo +"): " + getX() + ", " + getY();
    }
}
