import java.util.ArrayList;
import java.util.Scanner;

public class MenuInterativo {

    public static void entrarMenuInterativo(Ambiente meuAmbiente){
        //comandos gerais do menu interativo
        String comImprimirRobos = "itr";
        String comImprimirLigados = "itl";
        String comImprimirDesligados = "itd";
        String comImprimirObstaculos = "ito";
        String comImprimirAmbiente = "ia";
        String comImprimirMapa = "im";
        String comSelecionarRobo = "r";
        String comSair = "s";
        String comHelp = "h";

        //comandos do robo no menu interativo
        String comHelpRobo = "rh";
        String comPosRobo = "rp";
        String comLigarRobo = "rl";
        String comDesligarRobo = "rd";
        String comListarMensagens = "rlm";


        int maxMover = 20;
        //vetor com as 3 dimensoes do deslocamento, utilizado para tornar o codigo de movimentacao menos repetitivo
        //o robo ainda se movimenta apenas em uma direcao por comando
        int deltaMov[] = new int[3];

        //mensagem de comandos do menu interativo
        String msgComandos = "\nComandos gerais possiveis: \n" + 
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

        String msgComandosRobo = "\nComandos do robo selecionado: \n" + 
        comPosRobo + " - imprime sua posicao e status \n"+
        comLigarRobo+ " - liga o robo \n" +
        comDesligarRobo+ " - desliga o robo \n" +
        comListarMensagens + " - lista as mensagens recebidas pelo robo (se for comunicavel) \n" +
        "rmx, rmy, rmz <delta> - movem o robo selecionado na direcao escolhida uma quantidade <delta> "+
        "(delta pode ser negativo, rmz so pode ser usado para o aereo). ";
        //+"O valor maximo para o modulo de delta eh " + maxMover + " unidades";

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
                imprimirRobos(meuAmbiente);
            }
            else if(comando.equals(comImprimirLigados)){
                imprimirPorEstado(meuAmbiente, true);
            }
            else if(comando.equals(comImprimirDesligados)){
                imprimirPorEstado(meuAmbiente, false);
            }
            else if(comando.equals(comImprimirObstaculos)) {
                imprimirObstaculos(meuAmbiente);
            }
            else if(comando.equals(comImprimirAmbiente)) {
                imprimirAmbiente(meuAmbiente);
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
                    Robo escolha = escolherRoboEspecifico(divisor[1], meuAmbiente);
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
                        deltaMov[0] = getDeltaRobo(divisor);
                    }
                    else if(divisor[0].equals("rmy")) {
                        deltaMov[1] = getDeltaRobo(divisor);
                    }
                    else if(divisor[0].equals("rmz")) {
                        deltaMov[2] = getDeltaRobo(divisor);
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
                                    imprimirObstaculos(meuAmbiente);
                                    Obstaculo obstaculo = escolherObstaculoEspecifico(scanner.nextLine(), meuAmbiente);
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

                            roboSelecionado.executarTarefa();
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

     //Imprimir os robos do ambiente, com seu indice na lista de robos na frente
    public static void imprimirRobos(Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();
        for(int i = 0; i < robos.size(); i++){
            System.out.printf(i + "-> ");
            System.out.println(robos.get(i).getDescricao());
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

    private static void imprimirPorEstado(Ambiente amb, boolean ligado){
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

    private static void imprimirEntidades(Ambiente amb){
        ArrayList<Entidade> entidades= amb.getEntidades();
        for(int i = 0; i < entidades.size(); i++){
            System.out.printf(i +  ": ");
            System.out.println(entidades.get(i).getDescricao());
        }
    }
    private static void imprimirAmbiente(Ambiente amb){
        amb.imprimirDimensoes();
        System.out.println("O ambiente tem "+ amb.getEntidades().size() +" entidades.");
        //System.out.println("O ambiente tem " + amb.getRobos().size() + " robos.");
        //System.err.println("O ambiente tem " + amb.getObstaculos().size() + " obstaculos.");
    }

    //escolhe um robo a partir do indice ou nome. Podemos utilizar depois para imprimir o robo ou analisar sensores
    private static Entidade escolherEntidadeEspecifica(String identificador, Ambiente amb){
        ArrayList<Entidade> entidades = amb.getEntidades();

        //ver se string nao eh vazia
        if(identificador.length() > 0){

            //para utilizacao de numero para identificar robo
            if(ehInt(identificador)){
                int indice = Integer.parseInt(identificador);

                if(indice > entidades.size() - 1){
                    System.out.println("Indice invalido");
                    return null;
                }
                else{
                    Entidade ent = entidades.get(indice);
                    return ent;
                }
            }

            //para utilizacao de nome para identificar robo
            else{
                for(Entidade ent : entidades){
                    
                    if((ent instanceof Robo r) && identificador.equals(r.getNome())){
                        return ent;
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
    private static Obstaculo escolherObstaculoEspecifico(String identificador, Ambiente amb){
        ArrayList<Obstaculo> obstaculos = amb.getObstaculos();

        //ver se string nao eh vazia
        if(identificador.length() > 0){

            //para utilizacao de numero para identificar obstaculo
            if(ehInt(identificador)){
                int indice = Integer.parseInt(identificador);

                if(indice > obstaculos.size() - 1){
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
    private static Robo escolherRoboEspecifico(String identificador, Ambiente amb){
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
                if(numero > robos.size() - 1){
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
    private static int getDeltaRobo(String[] comDividido){
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

}
