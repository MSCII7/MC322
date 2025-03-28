public class Main{
    public static void main(String[] args){
        //Criar um novo robô genérico
        Robo meuRobo = new Robo("FRED",10, 24);
        //Criar um ambiente
        Ambiente meuAmbiente = new Ambiente(40, 40);
        //Testar reconhecimento dos limites
        imprimirLimites(meuRobo, meuAmbiente);
        //Testar movimentar o robo
        meuRobo.mover(20, 40);

        imprimirLimites(meuRobo, meuAmbiente);

        meuRobo.exibirPosicao();
        //Teste RoboAereo
        //Teste RoboAereoDevagar
        //Teste RoboAereoRefletor
        //Teste RoboTerrestre
        //Teste RoboTerrestreEletrico
        testeEletrico();
        testeTeletransporte(); 
        
    }

    private static void imprimirLimites(Robo r1, Ambiente amb1){

        if(amb1.dentroDosLimites(r1)){
            System.out.println("dentro dos limites do ambiente!");
        }
        else{
            System.out.println("fora dos limites do ambiente!");
        }
    }

    private static void testeEletrico(){
        RoboTerrestreEletrico eletrico = new RoboTerrestreEletrico("SHOCK", 5, 5, 101);
        System.out.println("\nTESTE RoboTerrestreEletrico:\n");

        eletrico.mover(100,0);

        System.out.print("ESPERADO: 105\nOBTIDO: ");
        System.out.println(eletrico.getPosX());
        System.out.println("");

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
    }
    //Teste RoboTerrestreTeletransporte 
    private static void testeTeletransporte(){
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
    }
}