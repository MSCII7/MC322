
import java.util.ArrayList;

public class RoboTerrestreMorador extends RoboTerrestre implements Abrigavel, Comunicavel, Sensoreavel{
    private SensorObstaculos radar_moradia;
    private ArrayList<Obstaculo> moradias;
    public RoboTerrestreMorador(String nome, int posX, int posY, int vMax) {
        super(nome, posX,posY, vMax);
        radar_moradia = new SensorObstaculos(100);
        moradias=new ArrayList<>();
    }
    
    @Override
    public void acharCasas(Ambiente amb) {
        
    }

    @Override
    public void acharPredios(Ambiente amb) {
    }

    @Override
    public void imprimirCasas() {
    }

    @Override
    public void imprimirPredio() {
    }

    @Override
    public void imprimirMoradias() {
    }

    @Override
    //Como o intuito eh apenas receber pelo correio
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
    }

    @Override
    public void receberMensagem() {
    }

    

    @Override
    public void acionarSensores(Ambiente amb) throws RoboDesligadoException {
    }

    @Override
    public void executarTarefa() {
        voltar_p_casa();
    }
    
    @Override
    public void achar_moradia(Ambiente amb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'achar_moradia'");
    }
    
}
