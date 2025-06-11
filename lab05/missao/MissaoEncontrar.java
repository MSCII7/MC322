package missao;

import ambiente.*;
import exceptions.RoboDesligadoException;
import interfacesRobos.*;
import java.util.ArrayList;
import robos.*;

public class MissaoEncontrar implements Missao {
    TipoObstaculo tipo;
    String comandoMissao = "ME";

    public MissaoEncontrar(TipoObstaculo t){
        this.tipo = t;
    }

    //exemplo: ME Bond ARVORE atribui a missao de encontrar arvore ao robo Bond
    @Override
    public void imprimirFormatoMissao(){
        System.out.println(comandoMissao + " <identificador robo> TipoObstaculo");
    }



    @Override
    public void executar(Robo r, Ambiente a) {
        //Tentar criar uma lógica de exploração, ativando o sensor de Obstáculos para encontrar os obstáculos ao redor e ver se é do tipo desejado
        if(r instanceof Sensoreavel roboSensoreavel){
            try{
                roboSensoreavel.acionarSensores(a);
                ArrayList<Obstaculo> obstaculos = roboSensoreavel.getObstaculosDentro(a);
                System.out.println("Encontrou os seguintes obstaculos:");

                for(Obstaculo ob : obstaculos){
                    if(ob.getTipoObstaculo() == tipo){
                        System.out.println(ob.getDescricao());
                    }
                }
            }
            catch(RoboDesligadoException e){
                System.err.println(e.getMessage());
            }
        }
        else{
            System.err.println("Robo nao pode encontrar obstaculos: nao eh sensoreavel");
        }
        
    }


}