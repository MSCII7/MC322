//o robo, ao mover, reflete nos limites de altitude superior e inferior se o deslocamento faz ele chegar neles
class RoboAereoRefletor extends RoboAereo{
    protected int altitudeMinima;

    public RoboAereoRefletor(String nomeIn, int posXIn, int posYIn, int posZIn, int altMax, int altMin){
        super(nomeIn, posXIn, posYIn, posZIn, altMax);
        altitudeMinima = altMin;
    }

    //se o deslocamento deltaZ for colocar o robo acima do limite superior, ele sobe ate o limite e desce a 
    //quantia restante do deltaZ
    @Override public void subir(int deltaZ, Ambiente amb){
        int nova_z = posicaoZ + deltaZ;
        int z_refletida = posicaoZ - Math.abs(deltaZ - (altitudeMaxima - posicaoZ));
        if (nova_z > altitudeMinima)
            if (!colisao_robo(identificarRobos(amb), posicaoX, posicaoZ, nova_z) && !colisao_obs(identificarObstaculos(amb), posicaoX, posicaoY, nova_z)){
                this.posicaoZ = nova_z;
            }
        else
            if (!colisao_robo(identificarRobos(amb), posicaoX, posicaoZ, z_refletida) && !colisao_obs(identificarObstaculos(amb), posicaoX, posicaoY, z_refletida)){
                this.posicaoZ = z_refletida;
            }
        }
    

    //se o deslocamento deltaZ for colocar o robo fora do limite inferior, ele desce ate o limite e sobe a 
    //quantia restante do deltaZ
    @Override public void descer(int deltaZ, Ambiente amb){
        int nova_z = posicaoZ - deltaZ;
        int z_refletida = altitudeMinima + Math.abs(deltaZ - (posicaoZ - altitudeMinima));
        if (nova_z > altitudeMinima)
            if (!colisao_robo(identificarRobos(amb), posicaoX, posicaoZ, nova_z) && !colisao_obs(identificarObstaculos(amb), posicaoX, posicaoY, nova_z)){
                this.posicaoZ = nova_z;
            }
        else
            if (!colisao_robo(identificarRobos(amb), posicaoX, posicaoZ, z_refletida) && !colisao_obs(identificarObstaculos(amb), posicaoX, posicaoY, z_refletida)){
                this.posicaoZ = z_refletida;
            }
        }
}
