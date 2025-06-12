package sensores;

import ambiente.*;
import java.util.ArrayList;
import robos.*;

public class SensorRobos extends Sensor {
    private ArrayList<Robo> robos_dentro;
    public SensorRobos(double raio){
        super(raio);
    } 
    @Override
    public void monitorar(int posX, int posY, int posZ, Ambiente amb){
        this.robos_dentro = new ArrayList<>();
        for (Entidade ent : amb.getEntidades()){
            if(ent instanceof Robo robo){
                if (dentro_do_raio(posX, posY, posZ, robo, this.raio)){
                    robos_dentro.add(robo);
                }
            }
        }
    }
    


    private boolean dentro_do_raio(int posX, int posY, int posZ, Robo robo, double raio){
        double dist;
        if (robo instanceof RoboAereo roboaereo){
            dist = Math.sqrt(Math.pow(posX - roboaereo.getX(), 2) + Math.pow(posY - roboaereo.getY(), 2) + Math.pow(posZ - roboaereo.getZ(), 2));
            return (dist<=raio);
        }
        dist = Math.sqrt(Math.pow(posX - robo.getX(), 2) + Math.pow(posY - robo.getY(), 2));

        return (dist<=raio);
    }

    public ArrayList<Robo> getRobos_dentro(int posX, int posY, int posZ, Ambiente amb){
        monitorar(posX, posY, posZ, amb);
        return robos_dentro;
    }

    public ArrayList<Robo> getRobos_dentro(){
        return robos_dentro;
    }

}