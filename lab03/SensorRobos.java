import java.util.ArrayList;

public class SensorRobos extends Sensor {
    private ArrayList<Robo> robos_dentro;
    public SensorRobos(double raio, Ambiente amb){
        super(raio, amb);
        this.robos_dentro = new ArrayList<>();
    } 
    @Override
    public void monitorar(Ambiente amb, int posX, int posY, int posZ){
        for (Robo robo : (this.amb.getRobos())) {
            if (dentro_do_raio(posX, posY, posZ, robo, raio)){
                robos_dentro.add(robo);
            }

        }
    }
    private boolean dentro_do_raio(int posX, int posY, int posZ, Robo robo, double raio){
        double dist;
        if (robo instanceof RoboAereo roboaereo){
            dist = Math.sqrt(Math.pow(posX - roboaereo.getPosX(), 2) + Math.pow(posY - roboaereo.getPosY(), 2) + Math.pow(posZ - roboaereo.getPosZ(), 2));
            return (dist<raio);
        }
        dist = Math.sqrt(Math.pow(posX - robo.getPosX(), 2) + Math.pow(posY - robo.getPosY(), 2));

        return (dist<raio);
    }

    public ArrayList<Robo> getRobos_dentro(){
        return robos_dentro;
    }
}