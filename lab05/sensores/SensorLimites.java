package sensores;

import ambiente.Ambiente;

public class SensorLimites extends Sensor{
    boolean dentro;
    public SensorLimites(double raio){
        super(raio);
    }
    @Override
    public void monitorar(int posX, int posY, int posZ, Ambiente amb) {
        
    }
    public boolean estaDentroDosLimites(int x, int y, int z, Ambiente amb) {
        if (Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2)) <= raio){
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
}
