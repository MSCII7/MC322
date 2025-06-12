package missao;

import ambiente.*;
import arquivos.Salvar;
import exceptions.RoboDesligadoException;
import java.util.ArrayList;
import robos.*;

public class MissaoEncontrar implements Missao {
    TipoObstaculo tipo;
    String comandoMissao = "ENC";

    public MissaoEncontrar(TipoObstaculo t){
        this.tipo = t;
    }

    //exemplo: ME Bond ARVORE atribui a missao de encontrar arvore ao robo Bond



    @Override
    public void executar(AgenteInteligente ai, Ambiente a) {
        //Tentar criar uma lógica de exploração, ativando o sensor de Obstáculos para encontrar os obstáculos ao redor e ver se é do tipo desejado
        String msgMissao = "Rodando Missao de Encontrar Obstaculos com robo " + ai.getNome() + ": \n";

        try{
            ai.acionarSensores(a);
            ArrayList<Obstaculo> obstaculos = ai.getObstaculosDentro(a);

            msgMissao += "Ativou Sensor de Obstaculos do robo " + ai.getNome() + 
            ", de raio " + ai.getGerenciadorSensores().getSensorObstaculos().getRaio() + "\n";
            
            msgMissao += "Encontrou os seguintes obstaculos do tipo " + tipo.toString() + ": \n";

            for(Obstaculo ob : obstaculos){
                if(ob.getTipoObstaculo() == tipo){
                    msgMissao += ob.getDescricao() + "\n";
                }
            }
        }
        catch(RoboDesligadoException e){
            System.err.println(e.getMessage());
        }

        Salvar.escreverMissao(msgMissao);

        System.out.println("---Escreveu no Arquivo---");
        System.out.println(msgMissao);
        System.out.println("--------------------------");
        
    }

    @Override
    public String getComando() {
        return comandoMissao;
    }

    @Override
    public Missao formatarParaMissao(String[] comDividido){
        MissaoEncontrar novaMissao = null;
        if(comDividido.length > 2){
            for(TipoObstaculo tipo : TipoObstaculo.values()){
                if(comDividido[2].equals(tipo.toString())){
                    novaMissao = new MissaoEncontrar(tipo);
                }
            }
        }
        return novaMissao;
    }

    @Override
    public String formatoEntrada(){
        return comandoMissao + " <TipoObstaculo>";
    }

    @Override
    public String getDescricao(){
        return "----Missao Encontrar : encontra todos os obstaculos de um determinado tipo no entorno----";
    }

    @Override
    public String getExemplo(){
        return comandoMissao + " ARVORE";
    }

}