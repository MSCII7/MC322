public class RoboTerrestreTeletransporte extends RoboTerrestre{
    private int barra_teletransporte;
    public RoboTerrestreTeletransporte(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn, vMax);
        barra_teletransporte = 0;
    }
    //Adiciona o aumento da barra_teletransporte ao andar
    @Override public void mover(int deltaX, int deltaY, Ambiente amb){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        int novo_y = posicaoY + deltaY;
        int novo_x = posicaoX + deltaX;
        if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima 
            && !colisao_robo(identificarRobos(amb), novo_x, novo_y) 
            && !colisao_obs(identificarObstaculos(amb), novo_x, novo_y)){
            int deslocamento = 0;
            if(posicaoX + deltaX > 0){ //para nao ir para negativo
                this.posicaoX += deltaX;
                deslocamento += deltaX;
            }
            if(posicaoY + deltaY > 0){ //para nao ir para negativo
                this.posicaoY += deltaY;
                deslocamento += deltaY;
            }
            //Ate 100, a barra aumente de acordo com a distancia andada
            if (this.barra_teletransporte < 100){
                if (this.barra_teletransporte + deslocamento < 100)
                    this.barra_teletransporte += deslocamento;
                else
                    this.barra_teletransporte = 100;
            } 
        } else
            System.out.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido");
        
        
    }
    //Função que joga o robo para uma posição determinada
    public void teletransportar(int posX, int posY){
        if (this.barra_teletransporte == 100){
            this.posicaoX = posX;
            this.posicaoY = posY;
            this.barra_teletransporte = 0; 
        } else
            System.out.println("Barra de telestransporte não está carregada! Faltam "+(100-this.barra_teletransporte)+" passos");
    }
    //Imprimi a barra em porcentagem
    public int getBarra_teletransporte(){
        return this.barra_teletransporte;
    }
}
