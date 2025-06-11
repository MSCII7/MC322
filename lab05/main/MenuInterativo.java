package main;

import ambiente.*;
import exceptions.*;
import interfacesRobos.*;
import java.util.Scanner;
import robos.*;


public class MenuInterativo {

    //comandos gerais do menu interativo
    static final String comImprimirRobos = "itr";
    static final String comImprimirLigados = "itl";
    static final String comImprimirDesligados = "itd";
    static final String comImprimirObstaculos = "ito";
    static final String comImprimirAmbiente = "ia";
    static final String comImprimirMapa = "im";
    static final String comSelecionarRobo = "r";
    static final String comSair = "s";
    static final String comHelp = "h";

    //comandos do robo no menu interativo
    static final String comHelpRobo = "rh";
    static final String comPosRobo = "rp";
    static final String comLigarRobo = "rl";
    static final String comDesligarRobo = "rd";
    static final String comListarMensagens = "rlm";


    //vetor com as 3 dimensoes do deslocamento, utilizado para tornar o codigo de movimentacao menos repetitivo
    //o robo ainda se movimenta apenas em uma direcao por comando
    static int deltaMov[] = new int[3];

    //mensagem de comandos do menu interativo
    static String msgComandos = "\nComandos gerais possiveis: \n" + 
    comImprimirRobos +" - imprime todos os robos \n" + 
    comImprimirLigados +" - imprime todos os robos ligados \n"+
    comImprimirDesligados + " - imprime todos os robos desligados \n"+
    comImprimirObstaculos + " - imprime todos os obstaculos \n" +
    comImprimirAmbiente + " - imprime dados do ambiente \n" + 
    comImprimirMapa + " - imprime o mapa do ambiente \n" +
    comSelecionarRobo + " <identificador> - seleciona um robo, "
    +"sendo <identificador> o nome do robo, o id unico do robo ou o indice do robo no ambiente. \n" +
    comSair + " - sai do programa \n" + 
    comHelp + " - imprime essa mensagem novamente.";

    static String msgComandosRobo = "\nComandos do robo selecionado: \n" + 
    comPosRobo + " - imprime sua posicao e status \n"+
    comLigarRobo+ " - liga o robo \n" +
    comDesligarRobo+ " - desliga o robo \n" +
    comListarMensagens + " - lista as mensagens recebidas pelo robo (se for comunicavel) \n" +
    "rmx, rmy, rmz <delta> - movem o robo selecionado na direcao escolhida uma quantidade <delta> "+
    "(delta pode ser negativo, rmz so pode ser usado para o aereo). ";
    //+"O valor maximo para o modulo de delta eh " + maxMover + " unidades";
    

    public static void entrarMenuInterativo(Ambiente meuAmbiente){

        System.out.println("");
        System.out.println("---Bem vindo ao menu de interacao---");

        System.out.println(msgComandos);

        //scanner utilizado para a entrada
        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine();

        Robo roboSelecionado = null;

        while(!comando.equals(comSair)) {
            String[] divisor = comando.split(" ");
        
            if(comando.equals(comImprimirRobos)) {
                MenuHelper.imprimirRobos(meuAmbiente);
            }
            else if(comando.equals(comImprimirLigados)){
                MenuHelper.imprimirPorEstado(meuAmbiente, true);
            }
            else if(comando.equals(comImprimirDesligados)){
                MenuHelper.imprimirPorEstado(meuAmbiente, false);
            }
            else if(comando.equals(comImprimirObstaculos)) {
                MenuHelper.imprimirObstaculos(meuAmbiente);
            }
            else if(comando.equals(comImprimirAmbiente)) {
                MenuHelper.imprimirAmbiente(meuAmbiente);
            }
            else if(comando.equals(comImprimirMapa)) {
                meuAmbiente.visualizarAmbiente(roboSelecionado);
            }
            else if(comando.equals(comHelp)) {
                System.out.println(msgComandos);
            }
            //escolher robo
            else if(divisor[0].equals(comSelecionarRobo)) {
                if(divisor.length > 1) {
                    Robo escolha = MenuHelper.escolherRoboEspecifico(divisor[1], meuAmbiente);
                    if(escolha != null) {
                        roboSelecionado = escolha;
                        System.out.println("Foi escolhido o robo " + roboSelecionado.getNome());
                        System.out.println("Utilize " + comHelpRobo + " para ver os comandos do robo");
                    }
                }
            }
            //Se nao eh nenhum dos comandos gerais, eh um comando dos robos
            else {
                if(roboSelecionado != null) {
                    if(comando.equals(comHelpRobo)) {
                        System.out.println(msgComandosRobo);
                        System.out.println();
                        System.out.println("Tarefa especifica do robo (Palavras em parenteses sao as interfaces adicionais utilizadas na tarefa, se houver):");
                        roboSelecionado.imprimirDescricaoTarefa();
                    }
                    //imprimir posicao robo selecionado
                    else if(comando.equals(comPosRobo)) {
                        roboSelecionado.exibirPosicao();
                    }
                    else if(comando.equals(comLigarRobo)) {
                        roboSelecionado.ligar();
                        System.out.println("Robo foi ligado");
                    }
                    else if(comando.equals(comDesligarRobo)) {
                        roboSelecionado.desligar();
                        System.out.println("Robo foi desligado");
                    }

                    else if(comando.equals(comListarMensagens)){
                        if(roboSelecionado instanceof Comunicavel roboComunicavel){
                            try{
                                roboComunicavel.receberMensagens();
                            }
                            catch(RoboDesligadoException e){
                                System.err.println(e.getMessage());
                            }
                        }
                        else{
                            System.err.println(roboSelecionado.getNome() + " nao eh Comunicavel!");
                        }
                    }
                    

                    //comandos de movimentacao
                    else if(divisor[0].equals("rmx")) {
                        deltaMov[0] = MenuHelper.getDeltaRobo(divisor);
                    }
                    else if(divisor[0].equals("rmy")) {
                        deltaMov[1] = MenuHelper.getDeltaRobo(divisor);
                    }
                    else if(divisor[0].equals("rmz")) {
                        deltaMov[2] = MenuHelper.getDeltaRobo(divisor);
                    }
                    else if(comando.equals(roboSelecionado.getComandoTarefa())) {
                        //pode estar tanto desligado para acionar sensores quanto para executar a tarefa
                        try {
                            //assumimos que os robos sensoreaveis precisam de sensores atualizados para suas tarefas
                            if(roboSelecionado instanceof Sensoreavel rSensoreavel) {
                                rSensoreavel.acionarSensores(meuAmbiente);
                            } 
                            else if (roboSelecionado instanceof Referenciavel ref) {
                                System.out.println("Necessario a escolha de um Obstaculo para continuar: ");
                                
                                while(ref.getReferencia()==null){
                                    MenuHelper.imprimirObstaculos(meuAmbiente);
                                    Obstaculo obstaculo = MenuHelper.escolherObstaculoEspecifico(scanner.nextLine(), meuAmbiente);
                                    if(obstaculo != null) {
                                        try {
                                            ref.setReferencia(obstaculo);  
                                        } catch (TipoIncompativelException e) {
                                            System.err.println(e.getMessage());
                                            System.out.println("\nEscolha outro obstaculo:");
                                        }
                                        System.out.println("Foi escolhido o obstaculo " + obstaculo.getTipoObstaculo());
                                    } 
                                }
                            } 
                            else if (roboSelecionado instanceof Construtor rConstrutor) {
                                System.out.println("Necessario inserir posicoes de construcao (x y):");
                                String[] coords = scanner.nextLine().split(" ");
                                
                                try {
                                    int x = Integer.parseInt(coords[0]);
                                    int y = Integer.parseInt(coords[1]);
                                    rConstrutor.construir(x, y, meuAmbiente);
                                } 
                                catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                                    System.err.println("Formato inválido! Digite dois números separados por espaço. Exemplo: 32 12");
                                }
                                catch(EntidadeInvalidaException e){
                                    System.err.println("Posicao ja ocupada por entidades!");
                                }
                            }

                            if(roboSelecionado instanceof AgenteInteligente ai){
                                ai.executarMissao(meuAmbiente);
                            }
                            else{
                                roboSelecionado.executarTarefa();
                            }
                        }
                        catch(RoboDesligadoException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    
                    //so executa mover se vetor delta de deslocamento nao for nulo
                    if(deltaMov[0] != 0 || deltaMov[1] != 0 || deltaMov[2] != 0){
                        meuAmbiente.moverEntidade(roboSelecionado,
                                roboSelecionado.getX() + deltaMov[0],
                                roboSelecionado.getY() + deltaMov[1],
                                roboSelecionado.getZ() + deltaMov[2]);
                    }
                } 
                else {
                    System.out.println("Nenhum Robo foi selecionado");
                }
            }
        
            //reseta vetor delta de deslocamento
            deltaMov[0] = 0; 
            deltaMov[1] = 0; 
            deltaMov[2] = 0;
        
            comando = scanner.nextLine();
        }
        
        scanner.close();

    }

}
