package main;

import ambiente.Ambiente;
import ambiente.Obstaculo;
import java.util.ArrayList;
import missao.*;
import robos.AgenteInteligente;
import robos.Robo;

//classe para ter as funcoes adicionais utilizadas pelo menu interativo
public class MenuHelper {

    static Missao[] missoesFake = {new MissaoEMP(), new MissaoEncontrar(null), new MissaoVerificarVazio(0, 0, 0, 1)};

     //Imprimir os robos do ambiente, com seu indice na lista de robos na frente
    public static void imprimirRobos(Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();
        for(int i = 0; i < robos.size(); i++){
            System.out.printf(i + "-> ");
            System.out.println(robos.get(i).getDescricao());
        }
    }

    //Imprimir os obstaculos do ambiente, com seu indice na lista de obstaculos na frente
    public static void imprimirObstaculos(Ambiente amb){
        ArrayList<Obstaculo> obstaculos = amb.getObstaculos();
        for(int i = 0; i < obstaculos.size(); i++){
            System.out.printf(i +  ": ");
            obstaculos.get(i).exibirObstaculo();
        }
    }

    public static void imprimirPorEstado(Ambiente amb, boolean ligado){
        ArrayList<Robo> robos = amb.getRobos();
        ArrayList<Robo> robosEstado = new ArrayList<>();
        ArrayList<Integer> robosIndexAmb = new ArrayList<>();
        for(int i = 0; i < robos.size(); i++){
            //exemplo: se queremos os desligados, teremos false == false -> true
            if(robos.get(i).getEstado() == ligado){
                robosEstado.add(robos.get(i));
                robosIndexAmb.add(i);

            }
        }
        for(int i = 0; i < robosEstado.size(); i++){
            System.out.printf(robosIndexAmb.get(i) + "-> ");
            System.out.println(robosEstado.get(i).getDescricao());
        }
    }

    
    public static void imprimirAmbiente(Ambiente amb){
        amb.imprimirDimensoes();
        System.out.println("O ambiente tem "+ amb.getEntidades().size() +" entidades.");
        //System.out.println("O ambiente tem " + amb.getRobos().size() + " robos.");
        //System.err.println("O ambiente tem " + amb.getObstaculos().size() + " obstaculos.");
    }

   
    public static Obstaculo escolherObstaculoEspecifico(String identificador, Ambiente amb){
        ArrayList<Obstaculo> obstaculos = amb.getObstaculos();

        //ver se string nao eh vazia
        if(identificador.length() > 0){

            //para utilizacao de numero para identificar obstaculo
            if(ehInt(identificador)){
                int indice = Integer.parseInt(identificador);

                if(indice > obstaculos.size() - 1 || indice < 0){
                    System.out.println("Indice invalido");
                    return null;
                }
                else{
                    Obstaculo ob = obstaculos.get(indice);
                    return ob;
                }
            }
        }
        System.out.println("A string idenficadora eh invalida");
        return null;
    }
    //escolhe um robo a partir do indice ou nome. Podemos utilizar depois para imprimir o robo ou analisar sensores
    public static Robo escolherRoboEspecifico(String identificador, Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();

        //ver se string nao eh vazia
        if(identificador.length() > 0){

            //para utilizacao de numero para identificar robo
            if(ehInt(identificador)){
                int numero = Integer.parseInt(identificador);

                //escolha por id
                for(Robo robo : robos){
                    if(robo.getId() == numero)
                        return robo;
                }

                //escolha por indice no ambiente
                if(numero > robos.size() - 1 || numero < 0){
                    System.out.println("Indice invalido");
                    return null;
                }
                else{
                    Robo r = robos.get(numero);
                    return r;
                }
            }

            //para utilizacao de nome para identificar robo
            else{
                for(Robo r : robos){
                    if(identificador.equals(r.getNome())){
                        return r;
                    }
                }
                //se nao retornou ainda, nenhum tem o nome
                System.out.println("Nenhum robo tem o nome dado!");
                return null;
            }
        }
        else{
            System.out.println("A string idenficadora esta vazia!");
            return null;
        }
        
    }

    //ver se pode converter a string para int
    public static boolean ehInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }


    //utilizado para obter o delta a partir do comando de movimentacao do robo (com .split ja aplicado)
    public static int getDeltaRobo(String[] comDividido){
        //se nao for maior que 1, o comando veio sem valor delta, entao podemos considerar delta = 0
        if(comDividido.length > 1){
            //usa o delta se for int e menor que o maximo
            if(ehInt(comDividido[1]))
                return Integer.parseInt(comDividido[1]);
            else{
                System.err.println("Nao foi fornecido numero valido");
                return 0;
            }
        }
        return 0;
    }

    public static void atribuirMissao(String[] comDividido, AgenteInteligente ai, Ambiente amb){
        Missao novaMissao = null;
        if(comDividido.length > 1){
            for(Missao m : missoesFake){
                if(comDividido[1].equals(m.getComando())){
                    novaMissao = m.formatarParaMissao(comDividido);
                }
            }
        }
        
        if(novaMissao != null){
            ai.definirMissao(novaMissao);
        }
    }
}
