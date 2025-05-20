import java.util.ArrayList;

public abstract class Robo implements Entidade{
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;

    protected String tipo;
    protected String id;
    protected boolean ligado;
    TipoEntidade tipoEntidade;

    protected SensorRobos sr;
    protected SensorObstaculos so;
    protected ArrayList<Sensor> sensores; //Por padrão iremos adicionar os sensores de Robos e os de Obstáculos em todos os robos
    
    public Robo(String nomeIn, int posXIn, int posYIn){
        nome = nomeIn;
        posicaoX = posXIn;
        posicaoY = posYIn;

        tipo = "Simples";
        ligado = true;
        tipoEntidade = TipoEntidade.ROBO;

        //Adicionar sensores essenciais para a movimentacao do robo
        sensores = new ArrayList<Sensor>();
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

    public abstract void executarTarefa();

    void moverPara(int novoX, int novoY, int novoZ, Ambiente amb) throws NaoAereoException{
        //Robo nao eh aereo, logo nao pode ir para Z diferente de 0. No aereo, esse metodo sera sobrescrito e a excecao removida
        if(novoZ != 0){
            throw new NaoAereoException();
        }
        else{
            try{
                mover(novoX - posicaoX, novoY - posicaoY, amb);
            } catch(ColisaoException colException){
                System.err.println("Houve colisao!");
            }
        }

    }

    public void mover(int deltaX, int deltaY, Ambiente amb) throws ColisaoException{
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;
        if (!colisao_robo(identificarRobos(amb), novo_x, novo_y) && !colisao_obs(identificarObstaculos(amb), novo_x, novo_y)){
            if(posicaoX + deltaX > 0) //para nao ir para negativo
                this.posicaoX += deltaX;
            if(posicaoY + deltaY > 0) //para nao ir para negativo
                this.posicaoY += deltaY;
        }else
            throw new ColisaoException();
    }

    public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + this.posicaoX + ", " + this.posicaoY);
    }

    public ArrayList<Obstaculo> identificarObstaculos(Ambiente amb){
        //Pegar obstaculos captados pelo sensor essencial SensorObstaculo
        return so.getObstaculos_dentro(this.posicaoX, this.posicaoY, 0, amb);

    }

    public ArrayList<Robo> identificarRobos(Ambiente amb){
        //Pegar os robos captados pelo sensor essencial SensorRobo
        ArrayList<Robo> robos = sr.getRobos_dentro(this.posicaoX, this.posicaoY, 0, amb);
        robos.remove(this); //remove o proprio robo que contem o sensor
        return robos;
    }

    public boolean colisao_obs(ArrayList<Obstaculo> obs_dentro, int nova_x, int nova_y){
        for (Obstaculo obs : obs_dentro){
            if ((obs.getPosicaoX1() < nova_x) && (nova_x < obs.getPosicaoX2()) && (obs.getPosicaoY1() < nova_y) && (nova_y < obs.getPosicaoY2()))
                return true;
        }
        return false;
    }
    
    public boolean colisao_robo(ArrayList<Robo> robos_dentro, int nova_x, int nova_y){
        for (Robo robo : robos_dentro){
            if (robo instanceof RoboAereo ra){
                if (nova_x == ra.getX() && nova_y == ra.getY() && ra.getZ() == 0)
                    return true;
            } else if (nova_x == robo.getX() && nova_y == robo.getY())
                return true;
        }
        return false;
    }
    /* public void identificarObstaculos(Ambiente amb1, int raio){

        for (Robo robo : amb1.getRobos()){
            if(Math.pow(this.posicaoX - robo.posicaoX, 2) + Math.pow(this.posicaoY - robo.posicaoY, 2) < raio*raio){
                System.out.println("Robo " + robo.nome + " esta no raio " + raio);
            }
        }
    }*/
    protected void adicionaSensores(Ambiente amb, Sensor s){
        this.sensores.add(s);
    }
    
    @Override
    public String toString() {
        return getNome() + "("+ this.tipo +"): " + getX() + ", " + getY();
    }
    public void usarSensores(Ambiente amb) {
        for (Sensor sensor : sensores){
            sensor.monitorar(this.posicaoX, this.posicaoY, 0, amb);
        }
    }
}
