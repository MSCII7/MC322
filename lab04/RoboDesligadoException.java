public class RoboDesligadoException extends Exception{
    public RoboDesligadoException(){
        super("Nao pode executar comando: Robo esta desligado");
    }
}
