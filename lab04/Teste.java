
//classe de testes fora do menu interativo
public class Teste {
    public static void testeCarregaveis(){
        System.out.println("\nTeste Robos da Interface Carregavel ---------------------");
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
        System.out.println("\nTeste Robos da Interface Construtor ---------------------");
        Ambiente ambTeste = new Ambiente(100, 10, 20);
        RoboTerrestreAmbientalista arb = new RoboTerrestreAmbientalista("arboreo", 2, 2, 9);
        try{
            ambTeste.adicionarEntidade(arb);
        }catch (EntidadeInvalidaException e){
            System.err.println("Nao pode adicionar robo nessa posicao");
        }
        //Iniciar teste
        ambTeste.visualizarAmbiente(arb);
        System.out.println(arb);
        try{
            arb.executarTarefa(4, 4, ambTeste);
        }
        catch(EntidadeInvalidaException e){
            System.err.println("Nao pode adicionar arvore: posicao ja ocupada");
        }
        try{
            arb.construir(20, 5, ambTeste);
        }
        catch(EntidadeInvalidaException e){
            System.err.println("Nao pode adicionar arvore: posicao ja ocupada");
        }
        ambTeste.visualizarAmbiente(arb);
        try{
            arb.construir(19, 5, ambTeste);
        }
        catch(EntidadeInvalidaException e){
            System.err.println("Nao pode adicionar arvore: posicao ja ocupada");
        }

        ambTeste.visualizarAmbiente(arb);

        System.out.println("-----FIM DO TESTE------\n");
    }
    public static void testeComunicavel(){
        System.out.println("\nTeste Robos da Interface Comunicavel ---------------------");
        Ambiente ambTeste = new Ambiente(100, 10, 20);
        
        RoboAereoConsciente consciente = new RoboAereoConsciente("consciente", 2, 2, 5, 20, 5);
        RoboAereo aereo = new RoboAereo("receptor", 3, 4, 4, 20);
        try{
            ambTeste.adicionarEntidade(consciente);
            ambTeste.adicionarEntidade(aereo);
        }catch (EntidadeInvalidaException e){
            System.err.println("Nao pode adicionar robo nessa posicao");
        }
        
        //Iniciar teste
        ambTeste.visualizarAmbiente(consciente);
        try{
            consciente.acionarSensores(ambTeste);
            consciente.executarTarefa();
        }
        catch(RoboDesligadoException e){
            System.err.println("Robo esta desligado!");
        }
        try{
            aereo.executarTarefa();
        }
        catch(RoboDesligadoException e){
            System.err.println(e.getMessage());
        }

        System.out.println("-----FIM DO TESTE------\n");
    }   
    public static void testeSensoreavel(){
        System.out.println("\nTeste Robos da Interface Sensoreavel ---------------------");
        Ambiente ambTeste = new Ambiente(100, 10, 20);
        
        RoboAereoConsciente consciente = new RoboAereoConsciente("consciente", 2, 2, 3, 20, 7);
        RoboAereo aereo = new RoboAereo("receptor", 3, 4, 4, 20);
        RoboAereo aereo2 = new RoboAereo("receptor2", 2, 4, 4, 20);
        RoboTerrestre carro = new RoboTerrestre("carro", 2, 3, 40);
        
        try{
            ambTeste.adicionarEntidade(consciente);
            ambTeste.adicionarEntidade(aereo);
            ambTeste.adicionarEntidade(aereo2);
            ambTeste.adicionarEntidade(carro);

        }catch (EntidadeInvalidaException e){
            System.err.println("Nao pode adicionar robo nessa posicao");
        }
        
        //Iniciar teste
        ambTeste.visualizarAmbiente(consciente);
        try{
            consciente.acionarSensores(ambTeste);
            consciente.executarTarefa();
        }
        catch(RoboDesligadoException e){
            System.err.println("Robo esta desligado!");
        }
        
        System.out.println("-----FIM DO TESTE------\n");
    } 
    public static void testeTerrestrePanfletario(){

    }
}