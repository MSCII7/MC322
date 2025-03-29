
//robo aereo que nao sobe nem desce se for ficar a uma certa distancia em z de outros robos aereos
class RoboAereoConsciente extends RoboAereo{
    protected int distanciaMin;

    public RoboAereoConsciente(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int distMin){
        super(nomeIn, posXIn, posYIn, posZIn, altMax);
        distanciaMin = distMin;
    }

    public void subir(int deltaZ, Ambiente amb){
        for(Robo r : amb.getRobos()){
            if(r instanceof RoboAereo){
                if(Math.abs(posicaoZ + deltaZ - ((RoboAereo) r).posicaoZ) < distanciaMin){
                    System.out.println("Robo " + nome + " nao moveu: ficaria muito proximo do robo " + r.nome);
                    r.exibirPosicao();
                    return;
                }
            }
        }
        this.posicaoZ += deltaZ;
    }

    public void descer(int deltaZ, Ambiente amb){
        for(Robo r : amb.getRobos()){
            if(r instanceof RoboAereo){
                if(Math.abs(posicaoZ - deltaZ - ((RoboAereo) r).posicaoZ) < distanciaMin){
                    System.out.println("Robo " + nome + " nao moveu: ficaria muito proximo do robo " + r.nome);
                    r.exibirPosicao();
                    return;
                }
            }
        }
        this.posicaoZ -= deltaZ;
    }

    //incrementa/subtrai da distancia minima que ele pode ficar de outros robos aereos no eixo z
    public void mudarDistancia(int deltaDist){
        if(distanciaMin + deltaDist > 0){
            distanciaMin += deltaDist;
        }
        else{
            System.out.println("Erro: tentativa de tornar distancia 0 ou negativa");
        }
    }
}
