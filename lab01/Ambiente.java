public class Ambiente
{
    private int largura;
    private int altura;
    public Ambiente(int l, int a)
    {
        largura = l;
        altura = a;
    }
    public boolean dentroDosLimites(int x, int y)
    {
        return (x <= this.largura && y <= this.altura);
    }
    

}