
//robo aereo que nao sobe nem desce se for ficar a uma certa distancia em z de outros robos aereos
class RoboAereoConsciente extends RoboAereo{
    protected int distanciaMin;
    public RoboAereoConsciente(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int distMin){
        super(nomeIn, posXIn, posYIn,posZIn, altMax);
        distanciaMin = distMin;
        so.setRaio(distMin);
        sr.setRaio(distMin);
        tipo  = "Consciente";
    }

    @Override public void subir(int deltaZ, Ambiente amb){
        //Ver se ha robos ou obstaculos dentro do raio minimo 
        int nova_z = this.posicaoZ + deltaZ;
        if ((so.getObstaculos_dentro(posicaoX, posicaoY, nova_z, amb)).isEmpty() && sr.getRobos_dentro(posicaoX, posicaoY, nova_z, amb).isEmpty()) 
            this.posicaoZ = nova_z;
        else
            System.out.println("Robo " + nome + " nao moveu: ficaria muito proximo de obstaculos ou robos");
    }

    @Override public void descer(int deltaZ, Ambiente amb){
        int nova_z = this.posicaoZ - deltaZ;
        if ((so.getObstaculos_dentro(posicaoX, posicaoY, nova_z, amb)).isEmpty() && sr.getRobos_dentro(posicaoX, posicaoY, nova_z, amb).isEmpty()) 
            this.posicaoZ = nova_z;
        else
            System.out.println("Robo " + nome + " nao moveu: ficaria muito proximo de obstaculos ou robos");
    }

    //incrementa/subtrai da distancia minima que ele pode ficar de outros robos aereos no eixo z
    public void mudarDistancia(int deltaDist){
        if(distanciaMin + deltaDist > 0){
            distanciaMin += deltaDist;
        }
        else{
            System.out.println("Erro: tentativa de tornar distancia minima 0 ou negativa");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ". distMin = " + this.distanciaMin;
    }
    
}
