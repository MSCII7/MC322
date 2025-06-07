package interfacesRobos;

import ambiente.*;
import exceptions.RoboDesligadoException;
import java.util.ArrayList;
import robos.Robo;

public interface Sensoreavel{
    //atualiza os sensores
    void acionarSensores(Ambiente amb) throws RoboDesligadoException;
    //metodos para pegar diretamente a informacao importante dos sensores.
    //como precisam ativar os sensores para isso, tem que ver se esta ligado
    ArrayList<Obstaculo> getObstaculosDentro(Ambiente amb) throws RoboDesligadoException;
    ArrayList<Robo> getRobosDentro(Ambiente amb) throws RoboDesligadoException;
}