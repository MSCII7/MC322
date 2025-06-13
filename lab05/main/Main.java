package main;

import ambiente.*;
import exceptions.*;
import missao.MissaoEMP;
import missao.MissaoVerificarVazio;
import robos.*;

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
            Teste.lerArquivo("teste.txt");
            //Criar robos e ambiente para menu interativo
            Ambiente meuAmbiente = new Ambiente(100, 100, 200);
            criarObstaculos(meuAmbiente);  
        

            Robo meuRobo = new RoboTerrestre("FRED",10, 24,20);
            meuRobo.setId(234);
           

            meuAmbiente.adicionarEntidade(meuRobo);



            meuRobo.exibirPosicao();

            //funcoes de teste para cada subclasse de robo, retornam o robo e o adicionam ao ambiente.
            RoboTerrestre terrestre = new RoboTerrestre("Carro", 30, 10, 20);
            terrestre.setId(433);
            meuAmbiente.adicionarEntidade(terrestre);

            RoboTerrestreEletrico eletrico = new RoboTerrestreEletrico("SHOCK", 5, 5,20);
            eletrico.setId(902);
            meuAmbiente.adicionarEntidade(eletrico);

            RoboTerrestreTeletransporte teletransporte = new RoboTerrestreTeletransporte("TP", 20, 10, 10);
            teletransporte.setId(940);
            meuAmbiente.adicionarEntidade(teletransporte);

            RoboAereo aereo = new RoboAereo("AGUIA", 5,10, 20, 40);
            aereo.setId(111);
            meuAmbiente.adicionarEntidade(aereo);

            RoboAereoRefletor refletor = new RoboAereoRefletor("REFRED", 10, 30, (60 - 30)/2, 60, 30);
            refletor.setId(880);
            meuAmbiente.adicionarEntidade(refletor);

            RoboAereoConsciente consciente = new RoboAereoConsciente("Seguro", 20, 20, 10, 100, 20);
            consciente.setId(911);
            meuAmbiente.adicionarEntidade(consciente);

            RoboTerrestreMorador morador = new RoboTerrestreMorador("DERF", 90, 90,101);
            morador.setId(875);
            meuAmbiente.adicionarEntidade(morador);

            RoboTerrestreAmbientalista ambientalista = new RoboTerrestreAmbientalista("Greta", 80, 70,101);
            ambientalista.setId(333);
            meuAmbiente.adicionarEntidade(ambientalista);

            RoboTerrestrePanfletario panfletario = new RoboTerrestrePanfletario("PrateadosInc", 80, 60,101);
            panfletario.setId(1440);
            meuAmbiente.adicionarEntidade(panfletario);

            MissaoVerificarVazio m = new MissaoVerificarVazio(30, 50, 10);
            RoboAgente roboAgente1 = new RoboAgente("Bond", 43, 32);
            roboAgente1.setId(770);
            meuAmbiente.adicionarEntidade(roboAgente1);

            MissaoEMP emp = new MissaoEMP();
            RoboAgente roboAgente2 = new RoboAgente("Craig", 70, 7);
            roboAgente2.setId(707);
            meuAmbiente.adicionarEntidade(roboAgente2);
            
            try{
                roboAgente1.executarMissao(meuAmbiente);
            }
            catch(RoboDesligadoException e){
                System.err.println("Robo desligado");
            }

            try{
                roboAgente2.executarMissao(meuAmbiente);
            }
            catch(RoboDesligadoException e){
                System.err.println("Robo desligado");
            }

            //Testar os sensores
            //Teste.testeSensorObstaculo(meuRobo, meuAmbiente);
            //Teste.testeSensorRobo(meuRobo, meuAmbiente); Dando problema aqui
            //imprimir posicoes finais dos robos
            MenuHelper.imprimirRobos(meuAmbiente); 

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