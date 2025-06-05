package robos;

import exceptions.*;
import interfacesRobos.*;


public class RoboTerrestreTeletransporte extends RoboTerrestre implements Carregavel{
    private int barra_teletransporte;
    public RoboTerrestreTeletransporte(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn, vMax);
        barra_teletransporte = 0;
        tipo = "Teletransporte";

        descricaoTarefa = " carrega a barra de teleporte do robo ate 100, permitindo que ele ultrapasse vMax " +
                            "quando for se mover novamente (Carregavel)";
        comandoTarefa = "ctp";
    }
    //Adiciona o aumento da barra_teletransporte ao andar
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        
        if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){ 
            int deslocamento = 0;
            if(posicaoX + deltaX >= 0){ //para nao ir para negativo
                this.posicaoX += deltaX;
                deslocamento += Math.abs(deltaX);
            }
            if(posicaoY + deltaY >= 0){ //para nao ir para negativo
                this.posicaoY += deltaY;
                deslocamento += Math.abs(deltaY);
            }
            //Ate 100, a barra aumente de acordo com a distancia andada
            if (this.barra_teletransporte < 100){
                if (this.barra_teletransporte + deslocamento < 100)
                    this.barra_teletransporte += deslocamento;
                else
                    this.barra_teletransporte = 100;
            } 
        } else if (this.barra_teletransporte==100){
            teletransportar(posicaoX + deltaX, posicaoY + deltaY);
        } else
            System.out.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido. Por favor, carregue o robo com: "+this.comandoTarefa);
        
        
    }
    //Função que joga o robo para uma posição determinada
    public void teletransportar(int posX, int posY){
        if (this.barra_teletransporte == 100){
            this.posicaoX = posX;
            this.posicaoY = posY;
            this.barra_teletransporte = 0; 
        } else{
            mover(posX - posicaoX, posY - posicaoY);
        }
    }

    @Override
    public void moverPara(int novoX, int novoY, int novoZ) throws NaoAereoException, RoboDesligadoException{
        if(ligado){
            if(novoZ != 0){
                throw new NaoAereoException();
            }
            else{
                teletransportar(novoX, novoY);
            }
        }
        else{
            throw new RoboDesligadoException();
        }
    }

    
    @Override public void executarTarefa() throws RoboDesligadoException{
        if(this.ligado){
            System.out.println("Nivel de bateria atual: " + getNivel()+"%");
            if (getNivel()==100){
                System.err.println("Barra ja esta cheia, nao precisa carregar!");
            }else{ 
                carregar();
            }
        }
        else{
            throw new RoboDesligadoException();
        }
    }
   
    @Override
    public void carregar(){
        System.out.println("Barra de teletransporte cheia. Agora, "+this.nome+" pode se teletransportar!");
        this.barra_teletransporte = 100;
    }
    //Imprime a barra em porcentagem
    @Override
    public int getNivel(){
        return this.barra_teletransporte;
    }
    @Override
    public String toString() {
        return super.toString() + ". Barra_tp = "+ getNivel() + "%";
    }
}
