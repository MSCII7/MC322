public abstract class Sensor {
    protected double raio;
    protected  Ambiente amb;
        
    public Sensor(double raio, Ambiente amb){
        this.raio = raio;
        this.amb = amb;
    }
    //Como o Sensor sem ser uma classe herdada dele, ele não será chamado isoladamente, então o "monitorar" poderá ser mantido vazio e dando Overide
    public abstract void monitorar(Ambiente amb, int posX, int posY, int posZ);
    public double getRaio(){
        return this.raio;
    }
}

