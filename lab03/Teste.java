public class Teste {
    /*Testa se as funcoes do robo eletrico funcionam e comparam com 
    o resultado esperado*/
    public static RoboTerrestreEletrico testeEletrico(){
        RoboTerrestreEletrico eletrico = new RoboTerrestreEletrico("SHOCK", 5, 5, 101);
        System.out.println("\n-----------\nTESTE RoboTerrestreEletrico:\n");

        eletrico.mover(100,0);

        System.out.print("ESPERADO: posicao do Robo SHOCK: 105, 5\nOBTIDO: ");
        eletrico.exibirPosicao();
        System.out.println("");

        System.out.print("ESPERADO: SHOCK está sem bateria, carregue-o para que ele possa se mover\nOBTIDO: ");
        eletrico.mover(1,0);
        System.out.println("");

        System.out.print("ESPERADO: posicao do Robo SHOCK: 105, 5\nOBTIDO: ");
        //Testar se o Robo conseguiu se mover
        eletrico.exibirPosicao();
        System.out.println("");

        System.out.print("ESPERADO: 0% \nOBTIDO: ");
        //Testar o carregamento
        eletrico.getNivel_bateria();
        System.out.println("");

        System.out.print("ESPERADO: SHOCK foi carregado! \nOBTIDO: ");
        eletrico.carregar();
        System.out.println("");

        System.out.print("ESPERADO: 100% \nOBTIDO: ");
        eletrico.getNivel_bateria();

        return eletrico;
    }

     //testa o robo terrestre, excedendo a velocidade maxima em um dos deslocamentos
     public static RoboTerrestre testeTerrestre(){
        RoboTerrestre terrestre = new RoboTerrestre("Carro", 10, 10, 30);

        System.out.println("\n-----------\nTESTE RoboTerrestre:\n");
        terrestre.exibirPosicao();

        terrestre.mover(10, 20);
        terrestre.exibirPosicao();

        terrestre.mover(20, 10);
        terrestre.exibirPosicao();

        terrestre.mover(20, 25);
        terrestre.exibirPosicao();

        return terrestre;
    }

    //Teste RoboTerrestreTeletransporte 
    public static RoboTerrestreTeletransporte testeTeletransporte(){
        RoboTerrestreTeletransporte tele = new RoboTerrestreTeletransporte("TP", 10, 10, 100);
        //Testar as mudanças da posição e da barra de teletransporte após o seu movimento
        System.out.println("\n-----------\nTESTE RoboTerrestreTeletransporte:\n");

        System.out.print("ESPERADO: 0% \nOBTIDO: ");
        tele.getBarra_teletransporte();
        System.out.println("");
        tele.mover(98,0);

        System.out.print("ESPERADO: 98% \nOBTIDO: ");
        tele.getBarra_teletransporte();
        System.out.println("");

        System.out.print("ESPERADO: Barra de telestransporte não está carregada! Faltam 2 passos \nOBTIDO: ");
        tele.teletransportar(0,0);
        System.out.println("");

        tele.mover(3,0);

        System.out.print("ESPERADO: 100% \nOBTIDO: ");
        tele.getBarra_teletransporte();
        System.out.println("");

        tele.teletransportar(0,0);

        System.out.print("ESPERADO: posicao do Robo TP: 0, 0 \nOBTIDO: ");
        tele.exibirPosicao();
        System.out.println("");

        System.out.print("ESPERADO: 0% \nOBTIDO: ");
        tele.getBarra_teletransporte();
        return tele;
    }

    //Testa o Robo refletor, subindo e descendo ate uma hora que ele bate nas bordas e fica indo e voltando batendo nas bordas
    public static RoboAereoRefletor testeRefletor(int passoZ, int altMax, int altMin){
        RoboAereoRefletor refletor = new RoboAereoRefletor("REFRED", 10, 10, (altMax - altMin)/2, altMax, altMin);
        
        System.out.println("\n-----------\nTESTE RoboAereoRefletor:\n");
        refletor.exibirPosicao();
        System.out.println("SUBINDO:");
        System.out.println("");

        //testar subida e limite superior
        for(int i = 0; i < 10; i++){
            refletor.subir(passoZ);
            refletor.exibirPosicao();
        }
        System.out.println("");
        System.out.println("DESCENDO:");
        System.out.println("");

        //testar descida e limite inferior
        for(int i = 0; i < 10; i++){
            refletor.descer(passoZ);
            refletor.exibirPosicao();
        }

        return refletor;
    }

    //Testa o movimento do consciente, incluindo os momentos em que ele nao move por estar perto de outros aereos no eixo Z
    //testa a variacao da distancia minima, incluindo tentar torna-la negativa
    public static RoboAereoConsciente testeConsciente(Ambiente amb){
        RoboAereoConsciente consciente = new RoboAereoConsciente("Seguro", 20, 20, 10, 100, 4);
        System.out.println("\n-----------\nTESTE RoboAereoConsciente:\n");

        consciente.exibirPosicao();
        consciente.descer(5, amb);

        consciente.exibirPosicao();

        consciente.mudarDistancia(20);

        consciente.subir(20, amb);
        
        consciente.exibirPosicao();

        consciente.mudarDistancia(-40);

        return consciente;
    }

    //testa o robo aereo, subindo e descendo e vendo se passa da altura maxima(a altura maxima e testada dentro do imprimirLimites do ambiente)
    public static RoboAereo testeAereo(Ambiente amb){
       int altMax = 40;
       
       RoboAereo aereo = new RoboAereo("AGUIA", 10,10, 20, altMax);
       System.out.println("\n-----------\nTESTE RoboAereo:\n");

       exibirAereo(aereo, amb, altMax);

       aereo.subir(15);
       exibirAereo(aereo, amb, altMax);

       aereo.descer(15);
       exibirAereo(aereo, amb, altMax);

       aereo.subir(25);
       exibirAereo(aereo, amb, altMax);

       return aereo;

    }
    //funcao para facilitar e modularizar a impressao do teste do aereo
    public static void exibirAereo(RoboAereo aereo, Ambiente amb, int altMax){
        aereo.exibirPosicao();
        amb.imprimirDimensoes();
        System.out.println("A altura maxima e " + altMax);
        imprimirLimites(aereo, amb);
        System.out.println("");
    }
}