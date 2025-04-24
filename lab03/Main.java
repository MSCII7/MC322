
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        //Criar um novo robô genérico
        //Criar um ambiente
        Ambiente meuAmbiente = new Ambiente(40, 40);
        Robo meuRobo = new Robo("FRED",10, 24, meuAmbiente);
        //Testar reconhecimento dos limites
        Teste.imprimirLimites(meuRobo, meuAmbiente);
        //Testar movimentar o robo
        meuRobo.mover(20, 40);

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

        RoboAereoRefletor refletor = Teste.testeRefletor(30, 132, 0, meuAmbiente);
        meuAmbiente.adicionarRobo(refletor);

        RoboAereoConsciente consciente = Teste.testeConsciente(meuAmbiente);
        meuAmbiente.adicionarRobo(consciente);

        //imprimir posicoes finais dos robos
        imprimirRobos(meuAmbiente); 

        Scanner scanner = new Scanner(System.in);




        /* O que colocar no menu interativo: (o '+' indica que a base ja esta feita)
            -Mensagem falando os comandos
            -Imprimir todos os robos(pr) +
            -Imprimir todos os obstaculos(po) +
         *  -Imprimir status do ambiente(dimensoes, numero de robos e obstaculos)(pa) +
         *  -Utilizar movimentacao basica(mover(), subir(), descer())
         *  -Selecionar robo a partir do nome ou indice: (r <identificador>) +
            *  -Relatar sensores de um robo selecionado
            *  -Imprimir posicao/status do robo selecionado
         *  
         * 
         */
    }
    

    //Imprimir os robos do ambiente, com seu indice na lista de robos na frente
    private static void imprimirRobos(Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();
        for(int i = 0; i < robos.size(); i++){
            System.out.printf(i + ": ");
            robos.get(i).exibirPosicao();
        }
    }

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
        System.err.println("O ambiente tem " + amb.getObstaculos().size() + "obstaculos.");
    }

    
    //escolhe um robo a partir do indice ou nome. Podemos utilizar depois para imprimir o robo ou analisar sensores
    private static Robo escolherRoboEspecifico(String identificador, Ambiente amb){
        ArrayList<Robo> robos = amb.getRobos();

        //ver se string nao eh vazia
        if(identificador.length() > 0){

            //verifica os valores ascii: so eh numero se estiver entre esses valores
            //Obs: considera que o nome nao comeca com numero
            if(identificador.charAt(0) > '0' && identificador.charAt(0) < '9'){
                int indice = Integer.parseInt(identificador);
                Robo r = robos.get(indice);
                System.out.println("Foi escolhido o robo " + r.getNome());
                return r;
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
}
