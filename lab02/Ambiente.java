
import java.util.ArrayList;
public class Ambiente
{
    private int largura;
    private int comprimento;
    private ArrayList<Robo> robos;
    public Ambiente(int l, int c)
    {
        largura = l;
        comprimento = c;
        robos = new ArrayList<>();
    }
    public boolean dentroDosLimites(Robo r)
    {
        if(r != null){
            if (r instanceof RoboAereo ra) 
                return (ra.getPosX() <= this.largura && ra.getPosY() <= this.comprimento && ra.getPosZ() <= ra.getAltitudeMaxima());
            
            return (r.getPosX() <= this.largura && r.getPosY() <= this.comprimento);
        }
        else
            return false;
    }
    public void adicionarRobo(Robo r)
    {
        this.robos.add(r);
    }
    public ArrayList<Robo> getRobos(){
        return this.robos;
    }

    public void imprimirDimensoes(){
        System.out.println("O ambiente tem largura " + largura + " e comprimento " + comprimento);
    }
}