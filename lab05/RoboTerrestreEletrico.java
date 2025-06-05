public class RoboTerrestreEletrico extends RoboTerrestre implements Carregavel{
    private int nivel_bateria; //determina a bateria restante do robo
    public RoboTerrestreEletrico(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn, vMax);
        nivel_bateria = 100;
        
        tipo = "Eletrico";
        descricaoTarefa = " imprime o nivel de bateria anterior e carrega o robo ate 100 (Carregavel)";
        comandoTarefa = "cr";
    }
    //Alterar o mover() para que o robo nao se mmova quando novel_bateria = 0    
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        
        //Adicionar o fator do nível da bateria como um limitante do deslocamento
        if (this.nivel_bateria >= (deltaX + deltaY)){
            if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
                if(posicaoX + deltaX >= 0){ //para nao ir para negativo
                    this.posicaoX += deltaX;
                    this.nivel_bateria -= Math.abs(deltaX);
                }
                if(posicaoY + deltaY >= 0){ //para nao ir para negativo
                    this.posicaoY += deltaY;
                    this.nivel_bateria -= Math.abs(deltaY);
                }
            } else
                System.err.println("Velocidade total do robo " + nome + " excedeu maximo: movimento impedido");
        } else
            System.err.println("Sem bateria, recarregue o robo com o comando \"ce\"");
    }

    //Carregar o robo, imprimindo o seu status e colocar seu nivel para 100
    @Override
    public void carregar(){
        System.out.println(this.nome+" foi carregado!");
        this.nivel_bateria = 100;
    }
    //Imprimir a bateria por "%"
    @Override
    public int getNivel(){
        return this.nivel_bateria;
    }

    @Override public void executarTarefa() throws RoboDesligadoException{
        if(this.ligado){
            System.out.println("Nivel de bateria atual: " + getNivel());
            carregar();
        }
        else{
            throw new RoboDesligadoException();
        }
    }
    @Override
    public String toString() {
        return super.toString() + ". Bateria = "+ getNivel() + "%";
    }
}
