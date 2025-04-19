
public class SensorObstaculosAereo extends SensorObstaculos {

    public SensorObstaculosAereo(double raio, Ambiente amb){
        super(raio, amb);
    } 

    @Override protected boolean dentro_do_raio(Obstaculo obs, int posX, int posY, int posZ){
        return (menor_dist(posX, posY, posZ, obs) < this.raio);
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
    
}