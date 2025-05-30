

public class Main{
    public static void main(String[] args){
        try{
            //Criar testes para as novas funcionalidades
            Teste.testeCarregaveis();
            Teste.testeReferenciaveis();
            Teste.testeConstrutor();
            Teste.testeComunicavel();
            Teste.testeSensoreavel();
            Teste.testeTerrestrePanfletario();
            //Criar robos e ambiente para menu interativo
            Ambiente meuAmbiente = new Ambiente(100, 100, 200);
            criarObstaculos(meuAmbiente);  
        

            Robo meuRobo = new RoboTerrestre("FRED",10, 24,20);
           

            meuAmbiente.adicionarEntidade(meuRobo);



            meuRobo.exibirPosicao();

            //funcoes de teste para cada subclasse de robo, retornam o robo e o adicionam ao ambiente.
            RoboTerrestre terrestre = new RoboTerrestre("Carro", 30, 10, 20);
            meuAmbiente.adicionarEntidade(terrestre);
            RoboTerrestreEletrico eletrico = new RoboTerrestreEletrico("SHOCK", 5, 5,20);
            meuAmbiente.adicionarEntidade(eletrico);
            RoboTerrestreTeletransporte teletransporte = new RoboTerrestreTeletransporte("TP", 20, 10, 10);
            meuAmbiente.adicionarEntidade(teletransporte);

            RoboAereo aereo = new RoboAereo("AGUIA", 5,10, 20, 40);
            meuAmbiente.adicionarEntidade(aereo);

            RoboAereoRefletor refletor = new RoboAereoRefletor("REFRED", 10, 30, (60 - 30)/2, 60, 30);
            meuAmbiente.adicionarEntidade(refletor);

            RoboAereoConsciente consciente = new RoboAereoConsciente("Seguro", 20, 20, 10, 100, 20);
            meuAmbiente.adicionarEntidade(consciente);

            RoboTerrestreMorador morador = new RoboTerrestreMorador("DERF", 90, 90,101);
            meuAmbiente.adicionarEntidade(morador);

            RoboTerrestreAmbientalista ambientalista = new RoboTerrestreAmbientalista("Greta", 80, 70,101);
            meuAmbiente.adicionarEntidade(ambientalista);

            RoboTerrestrePanfletario panfletario = new RoboTerrestrePanfletario("PrateadosInc", 80, 60,101);
            meuAmbiente.adicionarEntidade(panfletario);


            //Testar os sensores
            //Teste.testeSensorObstaculo(meuRobo, meuAmbiente);
            //Teste.testeSensorRobo(meuRobo, meuAmbiente); Dando problema aqui
            //imprimir posicoes finais dos robos
            MenuInterativo.imprimirRobos(meuAmbiente); 

            MenuInterativo.entrarMenuInterativo(meuAmbiente);
            
        }catch(EntidadeInvalidaException e){

        }
    }
    

    private static void criarObstaculos(Ambiente amb) throws EntidadeInvalidaException{
        amb.adicionarEntidade(new Obstaculo(70, 80, TipoObstaculo.CASA));
        amb.adicionarEntidade(new Obstaculo(10, 10, TipoObstaculo.PREDIO));
        amb.adicionarEntidade(new Obstaculo(20, 90, TipoObstaculo.MEGAMURO));
        amb.adicionarEntidade(new Obstaculo(90, 20, TipoObstaculo.MURO));
        amb.adicionarEntidade(new Obstaculo(80, 30, TipoObstaculo.ARVORE));    
    }
}