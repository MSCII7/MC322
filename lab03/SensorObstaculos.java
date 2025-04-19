import java.util.ArrayList;

public class SensorObstaculos extends Sensor{
    protected ArrayList<Obstaculo> obstaculos_dentro;

    public SensorObstaculos(double raio, Ambiente amb){
        super(raio, amb);
        this.obstaculos_dentro = new ArrayList<>();
    } 

    @Override
    public void monitorar(Ambiente amb, int posX, int posY, int posZ){
        for (Obstaculo obs : obstaculos_dentro){
            if (dentro_do_raio(obs, posX, posY, posZ)){
                obstaculos_dentro.add(obs);
            }
        }
    }

    protected boolean dentro_do_raio(Obstaculo obs, int posX, int posY, int posZ){
        return (menor_dist(posX, posY, obs) < this.raio);
    }

    protected double menor_dist(int posX, int posY, Obstaculo obs){
        //Declarando as variáveis
        int dx, dy;
        int x1 = obs.getPosicaoX1();
        int x2 = obs.getPosicaoX2();
        int y1 = obs.getPosicaoY1();
        int y2 = obs.getPosicaoY2();
        double dist;

        //Calculando as distâncias mínimas
        dx = Math.max(x1, Math.min(posX, x2));
        dy = Math.max(y1, Math.min(posY, y2));
        dist = Math.sqrt(dx*dx + dy*dy);
        return dist;
    }
    
    public ArrayList<Obstaculo> getObstaculos_dentro(){
        return obstaculos_dentro;
    }
}