public class ObstaculoInvalidoException extends Exception{
    public ObstaculoInvalidoException(){
        super("Obstaculo criado em posicao onde ja ha obstaculo");
    }
}