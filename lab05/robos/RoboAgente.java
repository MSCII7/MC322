package robos;

import ambiente.*;
import exceptions.RoboDesligadoException;
import interfacesRobos.*;
import java.util.ArrayList;
import missao.*;
import sensores.*;
import subsistemas.ControleMovimento;
import subsistemas.GerenciadorSensores;
import subsistemas.ModuloComunicacao;

public class RoboAgente extends AgenteInteligente {
    protected ArrayList<Sensor> sensores;

    public RoboAgente(Missao m, String nomeIn, int posXIn, int posYIn){
        super(nomeIn, posXIn, posYIn);
        this.missao = m;
        this.sensores = new ArrayList<>();
        controleMovimento = new ControleMovimento(this);
        gerenciadorSensores = new GerenciadorSensores(this);
        moduloComunicacao = new ModuloComunicacao(this);
    }

    @Override
    public void removerSensor(Sensor s) {
        for (Sensor sensor : sensores){
            if (sensor == s)
                sensores.remove(s);
        }
    }

    @Override
    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }

    @Override
    public void acionarSensores(Ambiente amb) throws RoboDesligadoException {
        for (Sensor s : sensores)
            s.monitorar(posicaoX, posicaoY, id, amb);
    }

    @Override
    public ArrayList<Obstaculo> getObstaculosDentro(Ambiente amb) throws RoboDesligadoException {
        return so.getObstaculos_dentro(posicaoX, posicaoY, posicaoX, amb);
    }
    
    @Override
    public ArrayList<Robo> getRobosDentro(Ambiente amb) throws RoboDesligadoException {
        return sr.getRobos_dentro(posicaoX, posicaoY, id, amb);
    }

    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException {
        try{
            moduloComunicacao.enviarMensagem(destinatario, mensagem);
        }
        catch(RoboDesligadoException e){
            throw e;
        }
    }

    @Override
    public void receberMensagens() throws RoboDesligadoException {
        try {
            moduloComunicacao.receberMensagens();
        } 
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public void executarMissao(Ambiente a) throws RoboDesligadoException{
		if (this.getEstado())
			if (temMissao()){
				//Criar os subsistemas para controlar a missao de maneira autonoma
				missao.executar(this, a);
			}
		else
			throw new RoboDesligadoException();
    }

    @Override
    public void executarTarefa() throws RoboDesligadoException {
		if (this.getEstado()){
            
		}else
			throw new RoboDesligadoException();
    }
}