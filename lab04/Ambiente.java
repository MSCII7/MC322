
import java.util.ArrayList;
public class Ambiente
{
    private final int largura;
    private final int comprimento;
    private final int altura;
    private final ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;

    private char representacaoSelecionado = 's'; //representacao do robo selecionado no mapa 

    public Ambiente(int l, int c, int a)
    {
        largura = l;
        comprimento = c;
        altura = a;
        entidades = new ArrayList<>();
        inicializarmapa();
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
            //essa linha verifica que o obstaculo, se gerado perto da borda X, eh "truncado"
            int fimX = Math.min(((Obstaculo) e).getPosicaoX2(), largura);
            int distX = Math.min(((Obstaculo) e).getPosicaoX2() - iniX, largura - iniX);
            int iniY = ((Obstaculo) e).getY();
            int fimY = Math.min(((Obstaculo) e).getPosicaoY2(), comprimento);
            //essa linha verifica que o obstaculo, se gerado perto da borda Y, eh "truncado"
            int distY = Math.min(((Obstaculo) e).getPosicaoY2() - iniY, comprimento - iniY);

            int z = ((Obstaculo) e).getZ();
            //Verificar se a posição em que o obstáculo será colocado é válida
            for (int i = iniX; i < fimX; i++) {
                for (int j = iniY; j < fimY; j++) {
                    for (int k = 0;k < z;k++) {
                        if(this.mapa[i][j][k] != TipoEntidade.VAZIO)
                            throw new EntidadeInvalidaException();
                   }
               } 
            }
            //Caso passe da verificação, modifique o mapa
            for (int i = iniX; i < fimX; i++) {
               for (int j = iniY; j < fimY; j++) {
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
            //essa linha verifica que o obstaculo, se gerado perto da borda X, eh "truncado"
            int distX = Math.min(((Obstaculo) e).getPosicaoX2() - iniX, largura - iniX);
            int iniY = ((Obstaculo) e).getY();
            //essa linha verifica que o obstaculo, se gerado perto da borda Y, eh "truncado"
            int distY = Math.min(((Obstaculo) e).getPosicaoY2() - iniY, comprimento - iniY);

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
        return (0 <= x && x < this.largura && 0 <=y && y < this.comprimento && 0 <= z && z < this.altura);
    }
    public boolean estaOcupado (int x, int y, int z){
        return (this.mapa[x][y][z] != TipoEntidade.VAZIO);
    }
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ){
        try{
            if (e.getX() != novoX || e.getY() != novoY || e.getZ() != novoZ){
                verificarColisoes(novoX, novoY, novoZ);
                
                this.mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
                e.moverPara(novoX, novoY, novoZ);       
                this.mapa[e.getX()][e.getY()][e.getZ()]  = e.getTipo();
            }
        } 
        catch (ColisaoException colException){
            System.err.println("ColisaoException, entidade não pode se mover para ("+novoX+ ", "+novoY+ ", "+novoZ+ ")");
        }
        catch (NaoAereoException naoAereo){
            System.err.println("Erro: tentativa de mover verticalmente robo nao aereo");
        }
        catch(RoboDesligadoException robException){
            System.err.println(robException.getMessage());
        }

    }
     
    public void executarSensores(Sensoreavel roboSensoreavel){
        try{
            roboSensoreavel.acionarSensores(this);
        }
        catch(RoboDesligadoException roboDesligado){
            System.err.println(roboDesligado.getMessage());
        }
        
    }

    public void verificarColisoes(int x, int y, int z) throws ColisaoException{ 
        if (!dentroDosLimites(x, y, z) || estaOcupado(x, y, z)) {
            throw new ColisaoException();
        }
    }

    public ArrayList<Robo> getRobos(){
        ArrayList<Robo> robos = new ArrayList<>();
        for(Entidade e : entidades){
            if(e instanceof Robo robo){
                robos.add(robo);
            }
        }
        return robos;
    }

    public ArrayList<Obstaculo> getObstaculos(){
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        for(Entidade e : entidades){
            if(e instanceof Obstaculo obstaculo){
                obstaculos.add(obstaculo);
            }
        }
        return obstaculos;
    }
    

    public void visualizarAmbiente(Robo roboSelecionado){
        //imprime a legenda do que cada caracter representa no mapa
        System.err.println("\nLegenda: ");
    
        System.out.println(TipoEntidade.DESCONHECIDO.getRepresentacao() + ": representa entidades de tipo desconhecido");

        System.out.println(TipoEntidade.OBSTACULO.getRepresentacao() + ": representa Obstaculos");
    
        System.out.println(TipoEntidade.ROBO.getRepresentacao() + ": representa RobosTerrestres");

        System.out.println(TipoEntidade.ROBOAEREO.getRepresentacao() + ": representa RobosAereos");
    
        System.out.println(TipoEntidade.VAZIO.getRepresentacao() + ": representa espacos vazios");

        System.out.println(representacaoSelecionado + ": representa robo selecionado (se houver)");
    
        //imprime o mapa em si
        char[][] mapa_aux = new char[this.largura][this.comprimento];
        for (int i = 0; i < this.largura; i++) 
            for (int j = 0; j < this.comprimento; j++){
                if(roboSelecionado != null && roboSelecionado.posicaoX == i && roboSelecionado.posicaoY == j)
                    mapa_aux[i][j] = representacaoSelecionado;
                else
                    mapa_aux[i][j] = this.mapa[i][j][0].getRepresentacao();
            }
        //Sobrepor os robos aereos no mapa, para considerar uma visualização superior do mapa
        for (Entidade ent: entidades){ 
            if (ent instanceof RoboAereo ra){
                if(ra == roboSelecionado)
                    mapa_aux[ra.getX()][ra.getY()] = representacaoSelecionado;
                else
                    mapa_aux[ra.getX()][ra.getY()] = ent.getRepresentacao(); 
            }
        }
        for (int j = this.comprimento-1; j >= 0; j--) {   
            for (int i = 0; i < this.largura; i++) {  
                System.out.print(mapa_aux[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
