import robos;
import ambiente;

public class MissaoEncontrar implements Missao {
    TipoObstaculo tipo;
    public MissaoEncontrar(TipoObstaculo t){
        this.tipo = t;
    }
    @Override
    public void executar(Robo r, Ambiente a) {
        //Tentar criar uma lógica de exploração, ativando o sensor de Obstáculos para encontrar os obstáculos ao redor e ver se é do tipo desejado
        
    }
    @Override
    public void ControleMovimento() {
    }
    @Override
    public void GerenciadorSensores() {
    }
    @Override
    public void ModuloComunicacao() {
    }

}