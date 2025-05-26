
import java.util.ArrayList;
import java.util.Scanner;
import javax.lang.model.util.ElementScanner14;

public class Main{
    public static void main(String[] args){
        //Criar um novo robô genérico
        //Criar um ambiente
        Ambiente meuAmbiente = new Ambiente(400, 400, 200);
        criarObstaculos(meuAmbiente);

        Robo meuRobo = new Robo("FRED",10, 24);
        //Testar reconhecimento dos limites
        Teste.imprimirLimites(meuRobo, meuAmbiente);
        //Testar movimentar o robo
        meuRobo.mover(20, 40, meuAmbiente);

        meuAmbiente.adicionarRobo(meuRobo);

        Teste.imprimirLimites(meuRobo, meuAmbiente);

        meuRobo.exibirPosicao();

        //funcoes de teste para cada subclasse de robo, retornam o robo e o adicionam ao ambiente.
        RoboTerrestre terrestre = Teste.testeTerrestre(meuAmbiente);
        meuAmbiente.adicionarRobo(terrestre);

        RoboTerrestreEletrico eletrico = Teste.testeEletrico(meuAmbiente);
        meuAmbiente.adicionarRobo(eletrico);

        RoboTerrestreTeletransporte teletransporte = Teste.testeTeletransporte(meuAmbiente); 
        meuAmbiente.adicionarRobo(teletransporte);

        RoboAereo aereo = Teste.testeAereo(meuAmbiente);
        meuAmbiente.adicionarRobo(aereo);

        RoboAereoRefletor refletor = Teste.testeRefletor(30, meuAmbiente.getAltura(), 0, meuAmbiente);
        meuAmbiente.adicionarRobo(refletor);

        RoboAereoConsciente consciente = Teste.testeConsciente(meuAmbiente);
        meuAmbiente.adicionarRobo(consciente);

        //Testar os sensores
        Teste.testeSensorObstaculo(meuRobo, meuAmbiente);
        Teste.testeSensorRobo(meuRobo, meuAmbiente);
        //imprimir posicoes finais dos robos
        imprimirRobos(meuAmbiente); 

        entrarMenuInterativo(meuAmbiente);

    }
    

    //Imprimir os robos do ambiente, com seu indice na lista de robos na frente
    private static void imprimirRobos(Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();
        for(int i = 0; i < robos.size(); i++){
            System.out.printf(i + "-> ");
            //robos.get(i).exibirPosicao();
            System.out.println(robos.get(i).toString());
        }
    }

    //Imprimir os obstaculos do ambiente, com seu indice na lista de obstaculos na frente
    private static void imprimirObstaculos(Ambiente amb){
        ArrayList<Obstaculo> obstaculos = amb.getObstaculos();
        for(int i = 0; i < obstaculos.size(); i++){
            System.out.printf(i +  ": ");
            obstaculos.get(i).exibirObstaculo();
        }
    }

    private static void imprimirAmbiente(Ambiente amb){
        amb.imprimirDimensoes();
        System.out.println("O ambiente tem " + amb.getRobos().size() + " robos.");
        System.err.println("O ambiente tem " + amb.getObstaculos().size() + " obstaculos.");
    }

    
    //escolhe um robo a partir do indice ou nome. Podemos utilizar depois para imprimir o robo ou analisar sensores
    private static Robo escolherRoboEspecifico(String identificador, Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();

        //ver se string nao eh vazia
        if(identificador.length() > 0){

            //para utilizacao de numero para identificar robo
            if(ehInt(identificador)){
                int indice = Integer.parseInt(identificador);

                if(indice > robos.size() - 1){
                    System.out.println("Indice invalido");
                    return null;
                }
                else{
                    Robo r = robos.get(indice);
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


    private static void criarObstaculos(Ambiente amb){
        amb.adicionarObstaculo(new Obstaculo(100, 100, TipoObstaculo.CASA));
        amb.adicionarObstaculo(new Obstaculo(10, 10, TipoObstaculo.PREDIO));
        amb.adicionarObstaculo(new Obstaculo(20, 90, TipoObstaculo.MEGAMURO));
        amb.adicionarObstaculo(new Obstaculo(90, 20, TipoObstaculo.MURO));
        amb.adicionarObstaculo(new Obstaculo(120, 30, TipoObstaculo.ARVORE));    
    }

    //ver se pode converter a string para int
    private static boolean ehInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }


    //utilizado para obter o delta a partir do comando de movimentacao do robo (com .split ja aplicado)
    private static int getDeltaRobo(String[] comDividido, int maxMover){
        //se nao for maior que 1, o comando veio sem valor delta, entao podemos considerar delta = 0
        if(comDividido.length > 1){
            //usa o delta se for int e menor que o maximo
            if(ehInt(comDividido[1]) && Math.abs(Integer.parseInt(comDividido[1])) <= maxMover)
                return Integer.parseInt(comDividido[1]);
            else{
                System.err.println("Nao foi fornecido numero valido");
                return 0;
            }
        }
        return 0;
    }

    private static void entrarMenuInterativo(Ambiente meuAmbiente){
        //comandos gerais do menu interativo
        String comImprimirRobos = "itr";
        String comImprimirObstaculos = "ito";
        String comImprimirAmbiente = "ia";
        String comImprimirMapa = "im";
        String comSelecionarRobo = "r";
        String comSair = "s";
        String comHelp = "h";

        //comandos do robo no menu interativo
        String comHelpRobo = "rh";
        String comPosRobo = "rp";
        String comSensorRobos = "rsr";
        String comSensorObst = "rso";
        String comLigarRobo = "rl";
        String comDesligarRobo = "rd";


        int maxMover = 20;
        //vetor com as 3 dimensoes do deslocamento, utilizado para tornar o codigo de movimentacao menos repetitivo
        //o robo ainda se movimenta apenas em uma direcao por comando
        int deltaMov[] = new int[3];

        //mensagem de comandos do menu interativo
        String msgComandos = comImprimirRobos +" imprime todos os robos \n " + 
        comImprimirObstaculos + " imprime todos os obstaculos \n " +
        comImprimirAmbiente + " imprime dados do ambiente \n" + 
        comSelecionarRobo + " <identificador> seleciona um robo, "
        +"sendo <identificador> o nome ou indice do robo no ambiente. \n" +
        comSair + " sai do programa \n" + 
        comHelp + " imprime essa mensagem novamente.\n";

        String msgComandosRobo = "Com um robo selecionado: \n" + 
        comPosRobo + " imprime sua posicao \n"+
        comSensorRobos + " imprime o status do seu sensor de robos \n " +
        comSensorObst +  " imprime o status do seu sensor de obstaculos. \n" +
        "rmx, rmy, rmz <delta> movem o robo selecionado na direcao escolhida uma quantidade <delta> "+
        "(delta pode ser negativo, rmz so pode ser usado para o aereo). "+
        "O valor maximo para o modulo de delta eh " + maxMover + " unidades \n";

        System.out.println("");
        System.out.println("---Bem vindo ao menu de interacao---");

        System.out.println(msgComandos);

        //scanner utilizado para a entrada
        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine();

        Robo roboSelecionado = null;

        while(!comando.equals(comSair)){
            String[] divisor = comando.split(" ");

            if(comando.equals(comImprimirRobos))
                imprimirRobos(meuAmbiente);
            else if(comando.equals(comImprimirObstaculos))
                imprimirObstaculos(meuAmbiente);
            else if(comando.equals(comImprimirAmbiente))
                imprimirAmbiente(meuAmbiente);
            
            else if(comando.equals(comHelp))
                System.out.println(msgComandos);

            //escolher robo
            else if(divisor[0].equals(comSelecionarRobo)){
                if(divisor.length > 1){
                    Robo escolha = escolherRoboEspecifico(divisor[1], meuAmbiente);
                    if(escolha != null) {
                        roboSelecionado = escolha;
                        System.out.println("Foi escolhido o robo " + roboSelecionado.getNome());
                        System.out.println("Utilize " + comHelpRobo + " para ver os comandos do robo");
                    }
                }
            }

            //Se nao eh nenhum dos comandos gerais, eh um comando dos robos
            else{
                if(roboSelecionado != null){
                    if(comando.equals(comHelpRobo)){
                        System.out.println(msgComandosRobo);
                        roboSelecionado.imprimirComandoTarefa();
                    }

                    //imprimir posicao robo selecionado
                    else if(comando.equals(comPosRobo)){
                        System.out.println(roboSelecionado.toString());
                    }

                     //imprimir sensor obstaculos robo selecionado
                    else if(comando.equals(comSensorObst)){
                        ArrayList<Obstaculo> obstaculos = roboSelecionado.identificarObstaculos(meuAmbiente);
                        System.out.println("----Utilizando sensor de obstaculos, de raio " + roboSelecionado.so.getRaio() + ": ----");

                        for(Obstaculo obs : obstaculos){
                            obs.exibirObstaculo();
                        }
                        System.out.println("-------------------------------------------------------");
                    }

                     //imprimir sensor robos robo selecionado
                    else if(comando.equals(comSensorRobos)){
                        ArrayList<Robo> robos = roboSelecionado.identificarRobos(meuAmbiente);
                        System.out.println("----Utilizando sensor de robos, de raio " + roboSelecionado.sr.getRaio() + ": ----");

                        for(Robo robo : robos){
                            robo.exibirPosicao();
                        }
                        System.out.println("--------------------------------------------------");
                    }

                     //comandos de movimentacao
                    else if(divisor[0].equals("rmx"))
                        deltaMov[0] = getDeltaRobo(divisor, maxMover);

                    else if(divisor[0].equals("rmy"))
                       deltaMov[1] = getDeltaRobo(divisor, maxMover);

                    else if(divisor[0].equals("rmz"))
                        deltaMov[2] = getDeltaRobo(divisor, maxMover);

                    else if(comando.equals(roboSelecionado.getComandoTarefa())){
                        roboSelecionado.executarTarefa();
                    }

                    try{
                        roboSelecionado.moverPara(roboSelecionado.getX() + deltaMov[0]
                                                , roboSelecionado.getY() + deltaMov[1]
                                                , roboSelecionado.getZ() + deltaMov[2]);
                    }
                    catch(NaoAereoException e){
                        System.err.println(e.getMessage());
                    }
                } 

                else
                    System.out.println("Nenhum Robo foi selecionado");
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
