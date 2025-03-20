public class Main{
    public static void main(String[] args){
        Robo meuRobo = new Robo("FRED",10, 24);
        Ambiente meuAmbiente = new Ambiente(40, 40);
        
        imprimirLimites(meuRobo, meuAmbiente);

        meuRobo.mover(20, 40);

        imprimirLimites(meuRobo, meuAmbiente);

        meuRobo.exibirPosicao();
    }

    private static void imprimirLimites(Robo r1, Ambiente amb1){

        if(amb1.dentroDosLimites(r1)){
            System.out.println("dentro dos limites do ambiente!");
        }
        else{
            System.out.println("fora dos limites do ambiente!");
        }
    }
}