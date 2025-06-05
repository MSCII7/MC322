package sensores;

import ambiente.*;
import java.util.ArrayList;

public class SensorObstaculos extends Sensor{
    protected ArrayList<Obstaculo> obstaculos_dentro;

    public SensorObstaculos(double raio){
        super(raio);
    } 

    @Override
    public void monitorar(int posX, int posY, int posZ, Ambiente amb){
        this.obstaculos_dentro = new ArrayList<>();
        for (Entidade ent : amb.getEntidades()){
            if(ent instanceof Obstaculo obs){
                if (dentro_do_raio(obs, posX, posY, posZ)){
                    obstaculos_dentro.add(obs);
            }
            }
        }
    }
    protected boolean dentro_do_raio(Obstaculo obs, int posX, int posY, int posZ){
        return (menor_dist(posX, posY, posZ, obs) <= this.raio);
    }
  
    protected double menor_dist(int posX, int posY, int posZ, Obstaculo obs){
        //Declarando as variáveis
        int dx, dy, dz;
        int x1 = obs.getX();
        int x2 = obs.getPosicaoX2();
        int y1 = obs.getY();
        int y2 = obs.getPosicaoY2();
        int z1 = obs.getZ();
        int z2 = 0;
        double dist;

        /*Calculando as distâncias mínimas
        A forma como esse calculo funciona eh analogo para cada direcao, entao podemos escolher uma para analisar. Vamos escolher dx.
        Se posX < x1, (x1 - posX) vai ser o unico positivo, logo ele eh o max e eh retornado, e representa a distancia ate a borda inferior dessa direcao
        Se x1 < posX < x2, os dois calculos serao negativos, entao eh retornado 0 (esta dentro, logo distancia nessa direcao eh 0)
        Se posX > x2, (posX - x2) eh o unico positivo, logo ele eh retornado, e representa a distancia ate a borda superior dessa direcao
        Depois, so fazemos pitagoras das distancias em cada direcao para calcular a distancia total
        */
        dx = Math.max(x1 - posX, Math.max(0, posX - x2));
        dy = Math.max(y1 - posY, Math.max(0, posY - y2));
        dz = Math.max(z1 - posZ, Math.max(0, posZ - z2));
        dist = Math.sqrt(dx*dx + dy*dy + dz*dz);
        return dist;
    }

    
  
    public ArrayList<Obstaculo> getObstaculos_dentro(int posX, int posY, int posZ, Ambiente amb){
        monitorar( posX, posY, posZ,amb);
        return obstaculos_dentro;
    }
}