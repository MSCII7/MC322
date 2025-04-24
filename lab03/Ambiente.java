
import java.util.ArrayList;
public class Ambiente
{
    private int largura;
    private int comprimento;
    private int altura;
    private ArrayList<Robo> robos;
    private ArrayList<Obstaculo> obstaculos;

    public Ambiente(int l, int c, int a)
    {
        largura = l;
        comprimento = c;
        altura = a;
        robos = new ArrayList<>();
        obstaculos = new ArrayList<>();
    }

    public boolean dentroDosLimites(Robo r)
    {
        if(r != null){
            if (r instanceof RoboAereo ra) 
                return (ra.getPosX() <= this.largura && ra.getPosY() <= this.comprimento && ra.getPosZ() <= this.altura);
            
            return (r.getPosX() <= this.largura && r.getPosY() <= this.comprimento);
        }
        else
            return false;
    }

    //robos
    public void adicionarRobo(Robo r) {
        this.robos.add(r);
    }
    public void removerRobo(Robo r){
        this.robos.remove(r);
    }
    public ArrayList<Robo> getRobos(){
        return this.robos;
    }

    //obstaculos
    public void adicionarObstaculo(Obstaculo ob){
        this.obstaculos.add(ob);
    }
    public void removerObstaculo(Obstaculo ob){
        this.obstaculos.remove(ob);
    }
    public ArrayList<Obstaculo> getObstaculos(){
        return this.obstaculos;
    }

    public void imprimirDimensoes(){
        System.out.println("O ambiente tem largura " + largura + ", comprimento " + comprimento + " e altura " + altura);
    }
}