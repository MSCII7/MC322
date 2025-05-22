public class EntidadeInvalidaException extends Exception{
    public EntidadeInvalidaException(){
        super("Obstaculo criado em posicao onde ja ha obstaculo");
    }
}