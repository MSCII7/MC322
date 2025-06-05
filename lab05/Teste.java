
public class Teste {
    public static void testeCarregaveis(){
        System.out.println("\nTeste Robos da Entidade Comunicavel ---------------------");
        Ambiente ambTeste = new Ambiente(100, 10, 5);
        RoboTerrestreEletrico elet = new RoboTerrestreEletrico("shok", 0, 0, 9);
        RoboTerrestreTeletransporte tp = new RoboTerrestreTeletransporte("tp", 90, 9, 3);
        try{
            ambTeste.adicionarEntidade(tp);
            ambTeste.adicionarEntidade(elet);
        }catch (EntidadeInvalidaException e){}
        //Iniciar teste
        ambTeste.visualizarAmbiente(null);
        System.out.println(elet);
        //Andar ate descarregar
        for (int i = 0; i < 18; i++) {
            if (i%2 == 0)
                ambTeste.moverEntidade(elet, 3, 3,0);
            else
                ambTeste.moverEntidade(elet,0, 0,0);
        }
        ambTeste.visualizarAmbiente(elet);
        System.out.println("Carregar o robo");
        try {
            elet.executarTarefa();
        } catch (RoboDesligadoException e) {}
        ambTeste.moverEntidade(elet, 2, 2, 0);
        ambTeste.visualizarAmbiente(elet);
        System.out.println(tp+"\nTentar mover para alem da velocidade maxima com a barra descarregada");
        ambTeste.moverEntidade(tp, 8, 9, 0);
        ambTeste.visualizarAmbiente(tp);
        try {
            tp.executarTarefa();
        } catch (RoboDesligadoException e) {}
        System.out.println("Mover para (80,9)");
        ambTeste.moverEntidade(tp, 8, 9, 0);
        ambTeste.visualizarAmbiente(tp);
        System.out.println("Agora a barra descarregou:\n"+tp+"\nFIM DO TESTE ---------------------------------------------------\n");

    }
    public static void testeReferenciaveis(){

    }
    public static void testeConstrutor(){

    }
    public static void testeComunicavel(){

    }   
    public static void testeSensoriavel(){

    } 
    public static void testeTerrestrePanfletario(){

    }
}