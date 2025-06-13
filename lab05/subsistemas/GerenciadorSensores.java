package subsistemas;

import ambiente.Ambiente;
import ambiente.Obstaculo;
import java.util.ArrayList;
import robos.AgenteInteligente;
import robos.Robo;
import sensores.*;

public class GerenciadorSensores {
    protected ArrayList<Sensor> sensores;
    protected AgenteInteligente roboGerenciado;
    public GerenciadorSensores(AgenteInteligente r){
        this.roboGerenciado= r;
        sensores = new ArrayList<>();
    }

    public void adicionarSensor(Sensor s){
        sensores.add(s);
    }
    public void removerSensor(Sensor s){
        sensores.remove(s);
    }
    public void removerTodosSensores(){
        sensores = null;
    }

    public SensorLimites getSensorLimites(){
        double maior_raio=0;
        SensorLimites escolhido = null;
        for (Sensor sensor : sensores){
            if (sensor instanceof SensorLimites sl && sl.getRaio()>maior_raio){
                maior_raio = sl.getRaio();
                escolhido = sl;
            }
        }
        return escolhido;
    }

    public SensorRobos getSensorRobos(){
        double maior_raio=0;
        SensorRobos escolhido = null;
        for (Sensor sensor : sensores){
            if (sensor instanceof SensorRobos sr && sr.getRaio()>maior_raio){
                maior_raio = sr.getRaio();
                escolhido = sr;
            }
        }
        return escolhido;
    }
    
    public SensorObstaculos getSensorObstaculos(){
        double maior_raio=0;
        SensorObstaculos escolhido = null;
        for (Sensor sensor : sensores){
            if (sensor instanceof SensorObstaculos so && so.getRaio()>maior_raio){
                maior_raio = so.getRaio();
                escolhido = so;
            }
        }
        return escolhido;
    }

    public double getRaioMinimo(){
        double raioMinimo = Double.MAX_VALUE;
        for(Sensor s : sensores){
            if(s.getRaio() < raioMinimo){
                raioMinimo = s.getRaio();
            }
        }
        return raioMinimo;
    }

    public boolean estaLivre(int x, int y, int z, Ambiente amb){
        int atualX = roboGerenciado.getX();
        int atualY = roboGerenciado.getY();
        int atualZ = roboGerenciado.getZ();
        SensorObstaculos so = this.getSensorObstaculos();
        ArrayList<Obstaculo> obs = so.getObstaculos_dentro(atualX, atualY, atualZ, amb);
        SensorRobos sr = this.getSensorRobos();
        ArrayList<Robo> robos = sr.getRobos_dentro(atualX, atualY, atualZ, amb);
        boolean t = !(colisao_obs(obs, x, y,z) || colisao_robo(robos, x,y,z) || colisaoExtremidades(x, y, z, amb));
        return t;
    } 

    public boolean colisaoExtremidades(int x, int y, int z, Ambiente amb){
        boolean t = !this.getSensorLimites().estaDentroDosLimites(roboGerenciado,x, y, z, amb);
        return t;
    }

    public boolean colisao_obs(ArrayList<Obstaculo> obs_dentro, int nova_x, int nova_y, int nova_z){
        for (Obstaculo obs : obs_dentro){
            if ((obs.getX() < nova_x) && (nova_x < obs.getPosicaoX2()) 
            && (obs.getY() < nova_y) && (nova_y < obs.getPosicaoY2())
            && (0 < nova_z) && (nova_z < obs.getZ()))
                return true;
        }
        return false;
    }
    
    public boolean colisao_robo(ArrayList<Robo> robos_dentro, int nova_x, int nova_y, int nova_z){
        for (Robo robo : robos_dentro)
            if (nova_x == robo.getX() && nova_y == robo.getY() && robo.getZ() == nova_z)
                return true;
        return false;
    }

    public ArrayList<Sensor> getSensores(){
        return sensores;
    }
}
