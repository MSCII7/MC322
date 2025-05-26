
import java.util.ArrayList;
public class Ambiente
{
    private final int largura;
    private final int comprimento;
    private final int altura;
    private final ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;

    public Ambiente(int l, int c, int a)
    {
        largura = l;
        comprimento = c;
        altura = a;
        entidades = new ArrayList<>();
    }

   public void imprimirDimensoes(){
        System.out.println("O ambiente tem largura " + largura + ", comprimento " + comprimento + " e altura " + altura);
    }

    public int getAltura(){
        return this.altura;
    }

    public void inicializarmapa(){
        //Alocação dinâmica para a matrix do mapa
        mapa = new TipoEntidade[this.largura][this.comprimento][this.altura];

       //preenche com vazio 
        for(int x = 0; x < this.largura; x++){
            for(int y = 0; y < this.comprimento; y++){
                for(int z = 0; z < this.altura; z++){
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    public void adicionarEntidade(Entidade e) throws EntidadeInvalidaException{
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            int iniX = ((Obstaculo) e).getX();
            int distX = ((Obstaculo) e).getPosicaoX2() - iniX;
            int iniY = ((Obstaculo) e).getY();
            int distY = ((Obstaculo) e).getPosicaoY2() - iniY;
            int z = ((Obstaculo) e).getZ();
            //Verificar se a posição em que o obstáculo será colocado é válida
            for (int i = iniX; i < distX; i++) {
                for (int j = iniY; j < distY; j++) {
                    for (int k = 0;k < z;k++) {
                        if(this.mapa[i][j][k] != TipoEntidade.VAZIO)
                            throw new EntidadeInvalidaException();
                   }
               } 
            }
            //Caso passe da verificação, modifique o mapa
            for (int i = iniX; i < distX; i++) {
               for (int j = iniY; j < distY; j++) {
                   for (int k = 0;k < z;k++) {
                        this.mapa[i][j][k] = TipoEntidade.OBSTACULO; 
                   }
               } 
            }
        } else{
            if (estaOcupado(e.getX(), e.getY(), e.getZ()))
                throw new EntidadeInvalidaException();
            else
                this.mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        }
        this.entidades.add(e);
    }

    public void removerEntidade(Entidade e){
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            int iniX = ((Obstaculo) e).getX();
            int distX = ((Obstaculo) e).getPosicaoX2() - iniX;
            int iniY = ((Obstaculo) e).getY();
            int distY = ((Obstaculo) e).getPosicaoY2() - iniY;
            int z = ((Obstaculo) e).getZ();
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

    public ArrayList<Entidade> getEntidades(){
        return this.entidades;
    }

    public boolean dentroDosLimites(int x, int y, int z){
        return (x <= this.largura && y <= this.comprimento && z <= this.altura); //IMPLEMENTAR CONLISION ECXEPTION
    }
    public boolean estaOcupado (int x, int y, int z){
      ////Percorrer a lista de entidades e verificar se suas dimensões contém a coordenada desejada
      //for (Entidade ent : entidades) {
      //    if (ent instanceof Obstaculo obs){
      //        if ((obs.getX() < x) && (x < obs.getPosicaoX2()) && (obs.getY() < y) && (z < obs.getZ())){
      //            return true;
      //        }
      //    } else {
      //        if ((ent.getX() == x) && (ent.getY() == y) && (ent.getZ() == z))
      //            return true;
      //        
      //    }
      //}
      //return false;
        return (this.mapa[x][y][z] != TipoEntidade.VAZIO);
    }
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ){
        try{
            verificarColisoes(novoX, novoY, novoZ);
            this.mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
            e.moverPara(novoX, novoY, novoZ);       
            this.mapa[e.getX()][e.getY()][e.getZ()]  = e.getTipo();
        } 
        catch (ColisaoException colException){
            System.err.println("ColisaoException, entidade não pode se mover para"+novoX+novoY+novoZ);
        }
        catch (NaoAereoException naoAereo){
            System.err.println("Erro: tentativa de mover verticalmente robo nao aereo");
        }
        catch(RoboDesligadoException robException){
            System.err.println(robException.getMessage());
        }

    }
      //if (e isnstanceof Robo r){
      //    if (dentroDosLimites(novoX, novoY, novoZ)){
      //        if (!estaOcupado(novoX, novoY, novoZ)){
      //            this.mapa[e.getX()][e.getY()][e.getZ()] = null;
      //            this.mapa[novoX][novoY][novoZ] = e.getTipo();
      //        }
      //    }
      //}
    public void executarSensores(Sensoreavel roboSensoreavel){
        try{
            roboSensoreavel.acionarSensores(this);
        }
        catch(RoboDesligadoException roboDesligado){
            System.err.println(roboDesligado.getMessage());
        }
        
    }

    public void verificarColisoes(int x, int y, int z) throws ColisaoException{ 
        if (estaOcupado(x, y, z)){
            throw new ColisaoException();
        }
    }
    
  //public void verificarColisoes_1(int x, int y, int z) throws ColisaoException{ 
  //    for (Entidade ent : this.entidades){
  //        if (ent.getTipo() == TipoEntidade.OBSTACULO){
  //            if (((Obstaculo) ent).getX() < x && x < ((Obstaculo) ent).getPosicaoX2()
  //                && ((Obstaculo) ent).getY() < y && y < ((Obstaculo) ent).getPosicaoY2()
  //                && z < ((Obstaculo) ent).getZ()){
  //                    throw new ColisaoException();
  //                }
  //        else if (ent.getTipo() == TipoEntidade.ROBO){
  //            if (ent instanceof RoboAereo ra){
  //                if (x == ra.getX() && y == ra.getY() && ra.getZ() == z)
  //                    throw new ColisaoException();
  //            } else if (z == 0 && x == ((Robo) ent).getX() && y == ((Robo) ent).getY())
  //                throw new ColisaoException();
  //        }
  //        }
  //    }   
  //}
    public void visualizarAmbiente(){
        char[][] mapa_aux = new char[this.comprimento][this.largura];
        for (int i = 0; i < this.comprimento; i++) 
            for (int j = 0; j < this.largura; j++) 
                mapa_aux[i][j] = this.mapa[i][j][0].getRepresentacao();
        //Sobrepor os robos aereos no mapa, para considerar uma visualização superior do mapa
        for (Entidade ent: entidades){ 
            if (ent instanceof RoboAereo ra){
               mapa_aux[ra.getX()][ra.getY()] = ent.getRepresentacao(); 
            }
        }
        for (int i=0; i< this.comprimento; i++)
            for (int j = 0; j < this.largura; j++)
                System.out.print(mapa_aux[i][j]);
    }

}
