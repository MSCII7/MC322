package subsistemas;

import ambiente.*;
import interfacesRobos.*;
import java.util.ArrayList;
import sensores.*;

public class GerenciadorSensores {
    protected ArrayList<Sensor> sensores;
    protected Sensoreavel roboGerenciado;
    protected Ambiente amb;
    public GerenciadorSensores(Sensoreavel r, Ambiente a){
        this.roboGerenciado= r;
        this.amb = a;
    }

    public void adicionarSensor(Sensor s){
        roboGerenciado.adicionarSensor(s);
    }
    public void removerSensor(Sensor s){
        roboGerenciado.removerSensor(s);
    }
}
