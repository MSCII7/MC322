
import java.util.ArrayList;
public class Ambiente
{
    private final int largura;
    private final int comprimento;
    private final int altura;
    //private final ArrayList<Robo> robos;
    //#private final ArrayList<Obstaculo> obstaculos;
    private final ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;

    public Ambiente(int l, int c, int a)
    {
        largura = l;
        comprimento = c;
        altura = a;
        //robos = new ArrayList<>();
        //obstaculos = new ArrayList<>();
        entidades = new ArrayList<>();
    }

//  public boolean dentroDosLimites(Robo r)
//  {
//      if(r != null){
//          if (r instanceof RoboAereo ra) 
//              return (ra.getPosX() <= this.largura && ra.getPosY() <= this.comprimento && ra.getPosZ() <= this.altura);
//          
//          return (r.getPosX() <= this.largura && r.getPosY() <= this.comprimento);
//      }
//      else
//          return false;
//  }

    //robos
/// public void adicionarRobo(Robo r) {
///     this.robos.add(r);
/// }
/// public void removerRobo(Robo r){
///     this.robos.remove(r);
/// }
/// public ArrayList<Robo> getRobos(){
///     return this.robos;
/// }

/// //obstaculos
/// public void adicionarObstaculo(Obstaculo ob){
///     this.obstaculos.add(ob);
/// }
/// public void removerObstaculo(Obstaculo ob){
///     this.obstaculos.remove(ob); /// } /// public ArrayList<Obstaculo> getObstaculos(){
///     return this.obstaculos;
/// }

    public void imprimirDimensoes(){
        System.out.println("O ambiente tem largura " + largura + ", comprimento " + comprimento + " e altura " + altura);
    }

    public int getAltura(){
        return this.altura;
    }
    public void inicializarmapa(){
        //Alocação dinâmica para a matrix do mapa
        mapa = new TipoEntidade[this.largura][this.comprimento][this.altura];
    }
    public void adicionarEntidade(Entidade e){

    }
    public void removerEntidade(Entidade e){

    }
    public boolean dentroDosLimites(int x, int y, int z){
        return (x <= this.largura && y <= this.comprimento && z <= this.altura); //IMPLEMENTAR CONLISION ECXEPTION
    }
    public boolean estaOcupado (int x, int y, int z){
        //Percorrer a lista de entidades e verificar se suas dimensões contém a coordenada desejada
        for (Entidade ent : entidades) {
            if (ent instanceof Obstaculo obs){
                if ((obs.getPosicaoX1() < x) && (x < obs.getPosicaoX2()) && (obs.getPosicaoY1() < y) && (z < obs.getAltura())){
                    return true;
                }
            } else {
                if ((ent.getX() == x) && (ent.getY() == y) && (ent.getZ() == z))
                    return true;
                
            }
        }
        return false;
    }
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ){
        e.mover(novoX, novoY, novoZ);        
    }
    public void executarSensores(){
        for (Entidade ent : entidades){
            if (ent instanceof Robo r){
                r.usarSensores();
            }
        }
    }
    public void verificarColisoes(){

    }
    public void visualizarAmbiente(){
        for (Entidade ent: entidades){ 

        }
    }
}