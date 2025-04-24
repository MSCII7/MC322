import java.util.ArrayList;

public class SensorObstaculos extends Sensor{
    protected ArrayList<Obstaculo> obstaculos_dentro;

    public SensorObstaculos(double raio, Ambiente amb){
        super(raio, amb);
    } 

    @Override
    public void monitorar(Ambiente amb, int posX, int posY){
        this.obstaculos_dentro = new ArrayList<>();
        for (Obstaculo obs : this.amb.getObstaculos()){
            if (dentro_do_raio(obs, posX, posY)){
                obstaculos_dentro.add(obs);
            }
        }
    }

    public void monitorar(Ambiente amb, int posX, int posY, int posZ){
        this.obstaculos_dentro = new ArrayList<>();
        for (Obstaculo obs : this.amb.getObstaculos()){
            if (dentro_do_raio(obs, posX, posY, posZ)){
                obstaculos_dentro.add(obs);
            }
        }
    }

    protected boolean dentro_do_raio(Obstaculo obs, int posX, int posY){
        return (menor_dist(posX, posY, obs) < this.raio);
    }

    protected boolean dentro_do_raio(Obstaculo obs, int posX, int posY, int posZ){
        return (menor_dist(posX, posY, posZ, obs) < this.raio);
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
    protected double menor_dist(int posX, int posY, int posZ, Obstaculo obs){
        //Declarando as variáveis
        int dx, dy, dz;
        int x1 = obs.getPosicaoX1();
        int x2 = obs.getPosicaoX2();
        int y1 = obs.getPosicaoY1();
        int y2 = obs.getPosicaoY2();
        int z1 = obs.getAltura();
        int z2 = 0;
        double dist;

        //Calculando as distâncias mínimas
        dx = Math.max(x1, Math.min(posX, x2));
        dy = Math.max(y1, Math.min(posY, y2));
        dz = Math.max(z1, Math.min(posZ, z2));
        dist = Math.sqrt(dx*dx + dy*dy + dz*dz);
        return dist;
    }

    
    public ArrayList<Obstaculo> getObstaculos_dentro(int posX, int posY){
        monitorar(amb, posX, posY, posZ);
        return obstaculos_dentro;
    }
    public ArrayList<Obstaculo> getObstaculos_dentro(int posX, int posY, int posZ){
        monitorar(amb, posX, posY, posZ);
        return obstaculos_dentro;
    }
}