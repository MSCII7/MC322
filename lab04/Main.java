

public class Main{
    public static void main(String[] args){
        try{
            //Criar um novo robô genérico
            //Criar um ambiente
            Ambiente meuAmbiente = new Ambiente(100, 100, 200);
            criarObstaculos(meuAmbiente);  
        

            Robo meuRobo = new RoboTerrestre("FRED",10, 24,20);
            //Testar reconhecimento dos limites
            //Teste.imprimirLimites(meuRobo, meuAmbiente);
            //Testar movimentar o robo
            //meuRobo.mover(20, 40, meuAmbiente);

            meuAmbiente.adicionarEntidade(meuRobo);


            //Teste.imprimirLimites(meuRobo, meuAmbiente);

            meuRobo.exibirPosicao();

            //funcoes de teste para cada subclasse de robo, retornam o robo e o adicionam ao ambiente.
            RoboTerrestre terrestre = Teste.testeTerrestre(meuAmbiente);
            meuAmbiente.adicionarEntidade(terrestre);

            RoboTerrestreEletrico eletrico = Teste.testeEletrico(meuAmbiente);
            meuAmbiente.adicionarEntidade(eletrico);
            //Dando problema aqui
            RoboTerrestreTeletransporte teletransporte = Teste.testeTeletransporte(meuAmbiente); 
            meuAmbiente.adicionarEntidade(teletransporte);

            RoboAereo aereo = Teste.testeAereo(meuAmbiente);
            meuAmbiente.adicionarEntidade(aereo);

            RoboAereoRefletor refletor = Teste.testeRefletor(30, meuAmbiente.getAltura(), 0, meuAmbiente);
            meuAmbiente.adicionarEntidade(refletor);

            RoboAereoConsciente consciente = Teste.testeConsciente(meuAmbiente);
            meuAmbiente.adicionarEntidade(consciente);

            RoboTerrestreMorador morador = Teste.testeTerrestreMorador(meuAmbiente);
            meuAmbiente.adicionarEntidade(morador);

            RoboTerrestreAmbientalista ambientalista = Teste.testeTerrestreAmbientalista(meuAmbiente);
            meuAmbiente.adicionarEntidade(ambientalista);

            RoboTerrestrePanfletario panfletario = Teste.testeTerrestrePanfletario(meuAmbiente);
            meuAmbiente.adicionarEntidade(panfletario);


            //Testar os sensores
            Teste.testeSensorObstaculo(meuRobo, meuAmbiente);
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