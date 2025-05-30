
public abstract class Robo implements Entidade{
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;

    protected String tipo;

    //o id eh usado na escolha do robo no menu. -1 indica nao inicializado
    protected int id;

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
        id = -1;

        //exemplo de comando e descricao base
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

    //seta o id se o id ainda nao tiver sido setado(-1 representa id nao setado)
    public void setId(int novoID){
        if(id == -1)
            id = novoID;
    }

    public int getId(){
        return this.id;
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

    public String getComandoTarefa(){
        return this.comandoTarefa;
    }

    //metodo abstrato para tarefas
    public abstract void executarTarefa() throws RoboDesligadoException;


    public void imprimirDescricaoTarefa(){
        System.out.println(comandoTarefa +  " -" + descricaoTarefa);
    }

    @Override
    public void moverPara(int novoX, int novoY, int novoZ) throws NaoAereoException, RoboDesligadoException{
        //Robo nao eh aereo, logo nao pode ir para Z diferente de 0. No aereo, esse metodo sera sobrescrito e a excecao removida
        if(ligado){
            if(novoZ != 0){
                throw new NaoAereoException();
            }
            else{
                mover(novoX - posicaoX, novoY - posicaoY);
            }
        }
        else{
            throw new RoboDesligadoException();
        }

    }

    public void mover(int deltaX, int deltaY){
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;

        if(novo_x >= 0) //para nao ir para negativo
            this.posicaoX += deltaX;
        if(novo_y >= 0) //para nao ir para negativo
            this.posicaoY += deltaY;
    }

    public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + this.posicaoX + ", " + this.posicaoY);
        if(ligado)
            System.out.println("Robo esta ligado");
        else 
            System.out.println("Robo esta desligado");
    }
    
    @Override
    public String toString() {
        return getNome() + " ("+ this.tipo +", id = " + this.id + "): " + getX() + ", " + getY();
    }

    @Override
    public String getDescricao(){
        return this.toString();
    }
}
