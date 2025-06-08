package robos;

import ambiente.*;
import exceptions.RoboDesligadoException;
import interfacesRobos.*;
import java.util.ArrayList;
import missao.*;
import sensores.*;
import subsistemas.*;

public abstract class AgenteInteligente extends Robo implements Sensoreavel, Comunicavel{
    protected Missao missao ;
    protected ArrayList<Sensor> sensores;
    ControleMovimento controleMovimento;
    GerenciadorSensores gerenciadorSensores;
    ModuloComunicacao moduloComunicacao;

    public AgenteInteligente(String nomeIn, int posXIn, int posYIn) {
        super(nomeIn, posXIn, posYIn);
    }

    public void definirMissao (Missao m) {
        this.missao = m;
    }

    public boolean temMissao () {
        return missao != null ;
    }

    public abstract void executarMissao (Ambiente a) throws RoboDesligadoException;
}
