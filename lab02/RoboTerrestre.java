public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;
    //Adicionar o atributo velocidadeMaxima no construtor
    public RoboTerrestre(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn);
        velocidadeMaxima = vMax;       
    }
    //Adicionar a verificação da velocidade para a locomoção do robo
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        
        if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
        }
    }
}

class RoboTerrestreEletrico extends RoboTerrestre{
    private int nivel_bateria;
    public RoboTerrestreEletrico(String nomeIn, int posXIn, int posYIn, int vMax, int nivel_bateria){
        super(nomeIn, posXIn, posYIn, vMax);
        nivel_bateria = 100;
    }
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        //Adicionar o fator do nível da bateria como um limitante do deslocamento
        if (this.nivel_bateria >= (deltaX + deltaY)){
            if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
                this.posicaoX += deltaX;
                this.posicaoY += deltaY;
                this.nivel_bateria -= (deltaX + deltaY);
            }
        } else
            System.out.println(this.nome+" está sem bateria, carregue-o para que ele possa se mover");
    }
    public void carregar(){
       //String text = "Carregando...........\n";
       //for(int i=0; i<text.length();i++){
        
       //System.out.print(text.charAt(i));
       //try {
       //    Thread.sleep(100); 
       //    } catch (Exception e) {
       //    e.printStackTrace();
       //    }
       //}
        System.out.println(this.nome+" foi carregado!");
        this.nivel_bateria = 100;
    }
    public void getNivel_bateria(){
        System.out.println(this.nivel_bateria+"%");
    }
}

class RoboTerrestreTeletransporte extends RoboTerrestre{
    private int barra_teletransporte;
    public RoboTerrestreTeletransporte(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn, vMax);
        barra_teletransporte = 0;
    }
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
            if (this.barra_teletransporte <= 100){
                this.barra_teletransporte += (deltaX + deltaY);
            } else
                this.barra_teletransporte = 100; //Limitar a barra para um valor máximo
        }
    }
    //Função que joga o robo para uma posição 
    public void teletransportar(int posX, int posY){
        if (this.barra_teletransporte == 100){
            this.posicaoX = posX;
            this.posicaoY = posY;
            this.barra_teletransporte = 0; 
        } else
            System.out.println("Barra de telestransporte não está carregada! Faltam "+(100-this.barra_teletransporte)+" passos");
    }
    public void getBarra_teletransporte(){
        System.out.println(this.barra_teletransporte+"%");
    }
}