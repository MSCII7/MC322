package sensores;

import ambiente.Ambiente;
import robos.AgenteInteligente;

public class SensorLimites extends Sensor{
    boolean dentro;
    public SensorLimites(double raio){
        super(raio);
    }
    @Override
    public void monitorar(int posX, int posY, int posZ, Ambiente amb) {
        
    }
    public boolean estaDentroDosLimites(AgenteInteligente r, int x, int y, int z, Ambiente amb) {
        if (dist(x, r.getX(), y, r.getY(), z, r.getZ()) <= raio){
            // Get environment dimensions
            int largura = amb.getLargura();
            int comprimento = amb.getComprimento();
            int altura = amb.getAltura(); // Assuming 3D environment as per config 

            // Check each axis
            boolean xValido = (x >= 0 && x < largura);
            boolean yValido = (y >= 0 && y < comprimento);
            boolean zValido = (z >= 0 && z < altura);

            return xValido && yValido && zValido;
        } else
            return false;
    }
    private double dist(int x1,int x2,int y1,int y2,int z1,int z2){
        return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)+Math.pow(z1-z2, 2));
    }
}
