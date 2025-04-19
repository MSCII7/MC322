import java.util.ArrayList;

public class RoboAereo extends Robo{
    int posicaoZ; //representa altitude(para seguir convecao de posicaoX e posicaoY)
    int altitudeMaxima;
    

    public RoboAereo(String nomeIn, int posXIn, int posYIn, Ambiente amb, int posZIn, int altMax){
        super(nomeIn, posXIn, posYIn, amb);
        posicaoZ = posZIn;
        altitudeMaxima = altMax;
    }

    public void subir(int deltaZ){
        this.posicaoZ += deltaZ;
    }

    public void descer(int deltaZ){
        if(posicaoZ - deltaZ > 0) //para nao ir para negativo
            this.posicaoZ -= deltaZ;
    }
    
    @Override public void exibirPosicao(){
        System.out.println("posicao do Robo " + this.nome + ": " + posicaoX + ", " + posicaoY + ", " + posicaoZ);
    }

    @Override protected void adicionaSensores(Ambiente amb){
        SensorObstaculosAereo soa = new SensorObstaculosAereo(10, amb);
        SensorRobos sb = new SensorRobos(10, amb);
        sensores.add(soa);
        sensores.add(sb);
    }

    @Override public ArrayList<Obstaculo> identificarObstaculos(){
        //para cada robo do ambiente, utiliza pitagoras da diferenca de posicao para ver se esta dentro do raio
        for (Sensor sensor : this.sensores){
            if (sensor instanceof SensorObstaculosAereo soa){
                return (soa.getObstaculos_dentro());
            }
        }
        return null;
    }

    public int getPosZ(){
        return this.posicaoZ;
    }

    public int getAltitudeMaxima(){
        return this.altitudeMaxima;
    }
}