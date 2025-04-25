public class RoboTerrestreEletrico extends RoboTerrestre{
    private int nivel_bateria; //determina a bateria restante do robo
    public RoboTerrestreEletrico(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn, vMax);
        nivel_bateria = 100;
    }
    //Alterar o mover() para que o robo nao se mmova quando novel_bateria = 0    
    @Override public void mover(int deltaX, int deltaY, Ambiente amb){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        int novo_y = posicaoY + deltaY;
        int novo_x = posicaoX + deltaX;
        
        //Adicionar o fator do nível da bateria como um limitante do deslocamento
        if (this.nivel_bateria >= (deltaX + deltaY)){
            if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima 
                && !colisao_robo(identificarRobos(amb), novo_x, novo_y) 
                && !colisao_obs(identificarObstaculos(amb), novo_x, novo_y)){
                if(posicaoX + deltaX > 0) //para nao ir para negativo
                    this.posicaoX += deltaX;
                if(posicaoY + deltaY > 0) //para nao ir para negativo
                    this.posicaoY += deltaY;
                }
        } else
            System.out.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido");
    }

    //Carregar o robo, imprimindo o seu status e colocar seu nivel para 100
    public void carregar(){
        System.out.println(this.nome+" foi carregado!");
        this.nivel_bateria = 100;
    }
    //Imprimir a bateria por "%"
    public int getNivel_bateria(){
        return this.nivel_bateria;
    }
}
