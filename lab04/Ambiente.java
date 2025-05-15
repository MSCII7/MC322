
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
        this.entidades.add(e);
        this.mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            int iniX = ((Obstaculo) e).getPosicaoX1();
            int distX = ((Obstaculo) e).getPosicaoX2() - iniX;
            int iniY = ((Obstaculo) e).getPosicaoY1();
            int distY = ((Obstaculo) e).getPosicaoY2() - iniY;
            int z = ((Obstaculo) e).getAltura();
            for (int i = iniX; i < distX; i++) {
               for (int j = iniY; j < distY; j++) {
                   for (int k = 0;k < z;k++) {
                      this.mapa[i][j][k] = TipoEntidade.OBSTACULO; 
                   }
               } 
            }
        } else{
            this.mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        }
        this.entidades.add(e);
    }

    public void removerEntidade(Entidade e){
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            int iniX = ((Obstaculo) e).getPosicaoX1();
            int distX = ((Obstaculo) e).getPosicaoX2() - iniX;
            int iniY = ((Obstaculo) e).getPosicaoY1();
            int distY = ((Obstaculo) e).getPosicaoY2() - iniY;
            int z = ((Obstaculo) e).getAltura();
            for (int i = iniX; i < distX; i++) {
               for (int j = iniY; j < distY; j++) {
                   for (int k = 0;k < z;k++) {
                      this.mapa[i][j][k] = TipoEntidade.VAZIO; 
                   }
               } 
            }
        } else{
            this.mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
        }
        this.entidades.remove(e);
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
        //return (this.mapa[x][y][z] =! TipoEntidade.VAZIO);
    }
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ){
        try{
            verificarColisoes(novoX, novoY, novoZ);
            this.mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
            e.mover(novoX, novoY, novoZ);       
            this.mapa[e.getX()][e.getY()][e.getZ()]  = e.getTipo();
            adicionarEntidade(e);
        } catch (colisaoException exception){
            System.out.println("colisaoException, entidade não pode se mover para"+novoX+novoY+novoZ);
        }
      //if (e isnstanceof Robo r){
      //    if (dentroDosLimites(novoX, novoY, novoZ)){
      //        if (!estaOcupado(novoX, novoY, novoZ)){
      //            this.mapa[e.getX()][e.getY()][e.getZ()] = null;
      //            this.mapa[novoX][novoY][novoZ] = e.getTipo();
      //        }
      //    }
      //}
    }
    public void executarSensores(Robo r){
                r.usarSensores(this);
        
    }
    public void verificarColisoes(int x, int y, int z) throws colisaoException{ 
        if (estaOcupado(x, y, z)){
            throw new colisaoException();
        }
    }
    public void verificarColisoes_1(int x, int y, int z) throws colisaoException{ 
        for (Entidade ent : this.entidades){
            if (ent.getTipo() == TipoEntidade.OBSTACULO){
                if (((Obstaculo) ent).getPosicaoX1() < x && x < ((Obstaculo) ent).getPosicaoX2()
                    && ((Obstaculo) ent).getPosicaoY1() < y && y < ((Obstaculo) ent).getPosicaoY2()
                    && z < ((Obstaculo) ent).getAltura()){
                        throw new colisaoException();
                    }
            else if (ent.getTipo() == TipoEntidade.ROBO){
                if (ent instanceof RoboAereo ra){
                    if (x == ra.getX() && y == ra.getY() && ra.getZ() == z)
                        throw new colisaoException();
                } else if (z == 0 && x == ((Robo) ent).getX() && y == ((Robo) ent).getY())
                    throw new colisaoException();
            }
            }
        }   
    }
    public void visualizarAmbiente(){
        for (Entidade ent: entidades){ 
            if (ent instanceof Obstaculo obs){
                
            }
        }
    }

}
