
import java.util.ArrayList;
public class Ambiente
{
    private int largura;
    private int comprimento;
    private int altura;
    private ArrayList<Robo> robos;
    public Ambiente(int l, int c, int a)
    {
        largura = l;
        comprimento = c;
        altura = a;
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

}