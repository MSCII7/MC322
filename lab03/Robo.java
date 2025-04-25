import java.util.ArrayList;

public class Robo{
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected String direcao;
    protected SensorRobos sr;
    protected SensorObstaculos so;
    protected ArrayList<Sensor> sensores; //Por padrão iremos adicionar os sensores de Robos e os de Obstáculos em todos os robos
    
    public Robo(String nomeIn, int posXIn, int posYIn){
        nome = nomeIn;
        posicaoX = posXIn;
        posicaoY = posYIn;
        sensores = new ArrayList<Sensor>();
        //Adicionar sensores essenciais para a movimentacao do robo
        sr = new SensorRobos(10);
        so = new SensorObstaculos(10);
    }

    public int getPosX(){
        return this.posicaoX;
    }

    public int getPosY(){
        return this.posicaoY;
    }

    public String getNome(){
        return this.nome;
    }

    public void mover(int deltaX, int deltaY, Ambiente amb){
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;
        if (!colisao_robo(identificarRobos(amb), novo_x, novo_y) && !colisao_obs(identificarObstaculos(amb), novo_x, novo_y)){
            if(posicaoX + deltaX > 0) //para nao ir para negativo
                this.posicaoX += deltaX;
            if(posicaoY + deltaY > 0) //para nao ir para negativo
                this.posicaoY += deltaY;
        }
    }

    public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + this.posicaoX + ", " + this.posicaoY);
    }

    public ArrayList<Obstaculo> identificarObstaculos(Ambiente amb){
        //para cada robo do ambiente, utiliza pitagoras da diferenca de posicao para ver se esta dentro do raio
        return so.getObstaculos_dentro(this.posicaoX, this.posicaoY, 0, amb);

    }

    public ArrayList<Robo> identificarRobos(Ambiente amb){
        //para cada robo do ambiente, utiliza pitagoras da diferenca de posicao para ver se esta dentro do raio
        return sr.getRobos_dentro(this.posicaoX, this.posicaoY, 0, amb);
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
                if (nova_x == ra.getPosX() && nova_y == ra.getPosY() && ra.getPosZ() == 0)
                    return true;
            } else if (nova_x == robo.getPosX() && nova_y == robo.getPosY())
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
}
