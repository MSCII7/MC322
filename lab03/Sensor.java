public abstract class Sensor {
    protected double raio;
    protected  Ambiente amb;
        
    public Sensor(double raio){
        this.raio = raio;
    }
    //Como o Sensor sem ser uma classe herdada dele, ele não será chamado isoladamente, então o "monitorar" poderá ser mantido vazio e dando Overide
    public abstract void monitorar(int posX, int posY, int posZ, Ambiente amb);
    public double getRaio(){
        return this.raio;
    }
}

