public class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;
    //Adicionar o atributo velocidadeMaxima no construtor
    public RoboTerrestre(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn);
        velocidadeMaxima = vMax;       
        this.so.setRaio(vMax);
        this.sr.setRaio(vMax);
    }
    //Adicionar a verificação da velocidade para a locomoção do robo
    @Override public void mover(int deltaX, int deltaY, Ambiente amb){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        int novo_y = posicaoY + deltaY;
        int novo_x = posicaoX + deltaX;
        if(moduloQuadrado <= velocidadeMaxima*velocidadeMaxima)
            if (!colisao_robo(identificarRobos(amb), novo_x, novo_y) 
            && !colisao_obs(identificarObstaculos(amb), novo_x, novo_y)){
                if(posicaoX + deltaX > 0) //para nao ir para negativo
                    this.posicaoX += deltaX;
                if(posicaoY + deltaY > 0) //para nao ir para negativo
                    this.posicaoY += deltaY;
            else
                System.out.println("Alerta de colisao: movimento impedido");
        } else{
            System.out.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido");
        }
    }
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }
}

