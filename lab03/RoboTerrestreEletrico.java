public class RoboTerrestreEletrico extends RoboTerrestre{
    private int nivel_bateria; //determina a bateria restante do robo
    public RoboTerrestreEletrico(String nomeIn, int posXIn, int posYIn, Ambiente amb, int vMax){
        super(nomeIn, posXIn, posYIn, amb, vMax);
        nivel_bateria = 100;
    }
    //Alterar o mover() para que o robo nao se mmova quando novel_bateria = 0    
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        //Adicionar o fator do nível da bateria como um limitante do deslocamento
        if (this.nivel_bateria >= (deltaX + deltaY)){
            if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
                this.posicaoX += deltaX;
                this.posicaoY += deltaY;
                //Diminuir a bateria de acordo com a distancia andada
                this.nivel_bateria -= (deltaX + deltaY);
            }
        } else
            System.out.println(this.nome+" está sem bateria, carregue-o para que ele possa se mover");
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
