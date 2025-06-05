package interfacesRobos;

import ambiente.Ambiente;
import exceptions.RoboDesligadoException;

public interface Sensoreavel{
    //atualiza os sensores
    void acionarSensores(Ambiente amb) throws RoboDesligadoException;

}