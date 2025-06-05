package robos;

import ambiente.*;
import exceptions.*;
import interfacesRobos.*;

public class RoboTerrestreAmbientalista extends RoboTerrestre implements Construtor{
    private int construcaoX, construcaoY;
    public RoboTerrestreAmbientalista(String nomeIn, int posXIn, int posYIn, int vMax) {
            super(nomeIn, posXIn, posYIn, vMax);

            this.tipo = "Ambientalista";
            this.comandoTarefa = "pa";
            this.descricaoTarefa = " escolhe uma posicao para plantar uma arvore (Construtor)";
        }
    
    @Override
    public void construir(int x, int y, Ambiente amb) throws EntidadeInvalidaException{
        Obstaculo arvore = new Obstaculo(x, y, TipoObstaculo.ARVORE);
        try {
            amb.adicionarEntidade(arvore);
            System.out.println("Plantou arvore em " + x + ", " + y + "!");
        } catch (EntidadeInvalidaException e) {
            throw e;
        }
    }

    @Override
    public void executarTarefa(){
    }
    
    public void executarTarefa(int x, int y, Ambiente amb) throws EntidadeInvalidaException{
        try {
            construir(x, y, amb);
        } catch (EntidadeInvalidaException e) {
            throw e;
        }
    }
    @Override
    public void setConstrucaoX(int x){
        this.construcaoX = x;
    }
    @Override
    public void setConstrucaoY(int y){
        this.construcaoY = y;
    }
}
