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

    String comandoSelecionarMissao;
    String descricaoComandoSelecionar;

    public AgenteInteligente(String nomeIn, int posXIn, int posYIn) {
        super(nomeIn, posXIn, posYIn);
        
        comandoTarefa = "em";
        descricaoTarefa = " executa a missao do agente inteligente";

        comandoSelecionarMissao = "sm";
        descricaoComandoSelecionar = " lista as missoes possiveis para ser selecionada uma";
    }

    public void definirMissao (Missao m) {
        this.missao = m;
    }

    public boolean temMissao () {
        return missao != null ;
    }

    public abstract void executarMissao (Ambiente a) throws RoboDesligadoException;

    public void imprimirDescricaoSelecionar(){

    }

    public String getComandoSelecionar(){
        return comandoSelecionarMissao;
    }
}
