
import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.printf(i + ": ");
            robos.get(i).exibirPosicao();
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
                    System.out.println("Foi escolhido o robo " + r.getNome());
                    return r;
                }
            }

            //para utilizacao de nome para identificar robo
            else{
                for(Robo r : robos){
                    if(identificador.equals(r.getNome())){
                        System.out.println("Foi escolhido o robo " + r.getNome());
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

    private static void entrarMenuInterativo(Ambiente meuAmbiente){
        //comandos do menu interativo
        String comImprimirRobos = "itr";
        String comImprimirObstaculos = "ito";
        String comImprimirAmbiente = "ia";
        String comSelecionarRobo = "r";
        String comPosRobo = "rp";
        String comSensorRobos = "rsr";
        String comSensorObst = "rso";
        String comSair = "s";
        String comHelp = "h";

        int maxMover = 20;

        //mensagem de comandos do menu interativo
        String msgComandos = "Utilize " + comImprimirRobos +" para imprimir todos os robos, " + 
        comImprimirObstaculos + " para imprimir todos os obstaculos, " +
        comImprimirAmbiente + " para imprimir dados do ambiente ou " + comSelecionarRobo + " <identificador> para selecionar um robo, "
        +"sendo <identificador> o nome ou indice do robo no ambiente. \n" +
        "Com um robo selecionado, utilize " + comPosRobo + " para imprimir "+
        "sua posicao, " + comSensorRobos + " para imprimir status do seu sensor de robos, " +
        comSensorObst +  " para imprimir o status do seu sensor de obstaculos. " +
        "Utilize rmx, rmy, rmz <delta> para mover o robo selecionado na direcao escolhida uma quantidade <delta> "+
        "(delta pode ser negativo, rmz so pode ser usado para o aereo). O valor maximo para o modulo de delta eh " + 
        maxMover + " unidades"+
        ". Utlize " + comSair + " para sair do programa, ou " + comHelp + " para imprimir essa mensagem novamente.\n";

        System.out.println("");
        System.out.println("---Bem vindo ao menu de interacao---");

        System.err.println(msgComandos);

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
                    if(escolha != null) roboSelecionado = escolha;
                }
            }

            //imprimir posicao robo selecionado
            else if(comando.equals(comPosRobo)){
                if(roboSelecionado == null)
                    System.out.println("Nenhum Robo foi selecionado");
                else{
                    roboSelecionado.exibirPosicao();
                }
            }

            //imprimir sensor obstaculos robo selecionado
            else if(comando.equals(comSensorObst)){
                if(roboSelecionado == null)
                    System.out.println("Nenhum Robo foi selecionado");
                else{
                    ArrayList<Obstaculo> obstaculos = roboSelecionado.identificarObstaculos(meuAmbiente);
                    System.out.println("----Utilizando sensor de obstaculos, de raio " + roboSelecionado.so.getRaio() + ": ----");

                    for(Obstaculo obs : obstaculos){
                        obs.exibirObstaculo();
                    }
                    System.err.println("-------------------------------------------------------");

                }
            }

            //imprimir sensor robos robo selecionado
            else if(comando.equals(comSensorRobos)){
                if(roboSelecionado == null)
                    System.out.println("Nenhum Robo foi selecionado");
                else{
                    ArrayList<Robo> robos = roboSelecionado.identificarRobos(meuAmbiente);
                    System.out.println("----Utilizando sensor de robos, de raio " + roboSelecionado.sr.getRaio() + ": ----");

                    for(Robo robo : robos){
                        robo.exibirPosicao();
                    }
                    System.err.println("--------------------------------------------------");
                }
            }

            //comandos de movimentacao

            else if(divisor[0].equals("rmx")){
                if(roboSelecionado == null)
                    System.out.println("Nenhum Robo foi selecionado");
                else{
                    if(divisor.length > 1){
                        //move se for int e menor que o maximo
                        if(ehInt(divisor[1]) && Math.abs(Integer.parseInt(divisor[1])) <= maxMover)
                            roboSelecionado.mover(Integer.parseInt(divisor[1]), 0, meuAmbiente);
                        else
                            System.out.println("Nao foi fornecido numero valido");
                    }
                }
            }

            else if(divisor[0].equals("rmy")){
                if(roboSelecionado == null)
                    System.out.println("Nenhum Robo foi selecionado");
                else{
                    if(divisor.length > 1){
                        //move se for int e menor que o maximo
                        if(ehInt(divisor[1]) && Math.abs(Integer.parseInt(divisor[1])) <= maxMover)
                            roboSelecionado.mover(0, Integer.parseInt(divisor[1]), meuAmbiente);
                        else
                            System.out.println("Nao foi fornecido numero valido");
                    }
                }
            }

            else if(divisor[0].equals("rmz")){
                if(roboSelecionado == null)
                    System.out.println("Nenhum Robo foi selecionado");
                else{
                    //ve se eh aereo
                    if(roboSelecionado instanceof RoboAereo ra){
                        if(divisor.length > 1){
                            //ve se eh menor que maximo deslocamento
                            if(ehInt(divisor[1]) && Math.abs(Integer.parseInt(divisor[1])) <= maxMover){
                                int deltaZ = Integer.parseInt(divisor[1]);

                                if(deltaZ >= 0)
                                    ra.subir(deltaZ, meuAmbiente);
                                else
                                    ra.descer(Math.abs(deltaZ), meuAmbiente);
                            }
                            else
                                System.out.println("Nao foi fornecido numero valido");
                        }
                    }
                    else
                        System.out.println("Robo selecionado nao eh aereo");
                }
            }


            comando = scanner.nextLine();
        }

        scanner.close();

    }
}
