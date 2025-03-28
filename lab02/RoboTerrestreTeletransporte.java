public class RoboTerrestreTeletransporte extends RoboTerrestre{
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
            int deslocamento = deltaX + deltaY;
            if (this.barra_teletransporte < 100){
                if (this.barra_teletransporte + deslocamento < 100)
                    this.barra_teletransporte += deslocamento;
                else
                    this.barra_teletransporte = 100;
            } 
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