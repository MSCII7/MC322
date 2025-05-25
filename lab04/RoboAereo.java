
public class RoboAereo extends Robo{
    int posicaoZ; //representa altitude(para seguir convecao de posicaoX e posicaoY)
    int altitudeMaxima;
    

    public RoboAereo(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax){
        super(nomeIn, posXIn, posYIn);
        posicaoZ = posZIn;
        altitudeMaxima = altMax;
        tipo = "Aereo";
    }

    public void subir(int deltaZ){
        int nova_z = posicaoZ + Math.abs(deltaZ);
        this.posicaoZ = nova_z;
    }

    public void descer(int deltaZ){
        int nova_z = posicaoZ - Math.abs(deltaZ);
        this.posicaoZ = nova_z;
    }

    @Override public int getZ(){
        return this.posicaoZ;
    }

    public int getAltitudeMaxima(){
        return this.altitudeMaxima;
    }
    
    //Varios dos metodos do robo devem ser alterados para utilizar a posicao Z
    @Override public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + posicaoX + ", " + posicaoY + ", " + posicaoZ);
    }

    @Override
    public void moverPara(int novoX, int novoY, int novoZ){
        mover(novoX - posicaoX, novoY - posicaoY);
        int deltaZ = novoZ - posicaoZ;

        if(deltaZ >= 0) 
            subir(deltaZ);
        else 
            descer(deltaZ);
    }

    @Override 
    public void mover(int deltaX, int deltaY){
        int novo_x = posicaoX + deltaX;
        int novo_y = posicaoY + deltaY;

        if(novo_x > 0) //para nao ir para negativo
            this.posicaoX += deltaX;
        if(novo_y > 0) //para nao ir para negativo
            this.posicaoY += deltaY;
    }


    @Override
    public String toString() {
        return getNome() + "("+ this.tipo+"): " + getX() + ", " + getY()+ ", " + getZ() + ". altMax = "+ getAltitudeMaxima();
    }

    @Override
    public void executarTarefa(){
        
    }
}