public class RoboTerrestreEletrico extends RoboTerrestre{
    private int nivel_bateria;
    public RoboTerrestreEletrico(String nomeIn, int posXIn, int posYIn, int vMax){
        super(nomeIn, posXIn, posYIn, vMax);
        nivel_bateria = 100;
    }
    
    @Override public void mover(int deltaX, int deltaY){
        int moduloQuadrado = deltaX*deltaX + deltaY*deltaY;
        //Adicionar o fator do nível da bateria como um limitante do deslocamento
        if (this.nivel_bateria >= (deltaX + deltaY)){
            if(moduloQuadrado < velocidadeMaxima*velocidadeMaxima){
                this.posicaoX += deltaX;
                this.posicaoY += deltaY;
                this.nivel_bateria -= (deltaX + deltaY);
            }
        } else
            System.out.println(this.nome+" está sem bateria, carregue-o para que ele possa se mover");
    }
    public void carregar(){
       //String text = "Carregando...........\n";
       //for(int i=0; i<text.length();i++){
        
       //System.out.print(text.charAt(i));
       //try {
       //    Thread.sleep(100); 
       //    } catch (Exception e) {
       //    e.printStackTrace();
       //    }
       //}
        System.out.println(this.nome+" foi carregado!");
        this.nivel_bateria = 100;
    }
    public void getNivel_bateria(){
        System.out.println(this.nivel_bateria+"%");
    }
}

    

