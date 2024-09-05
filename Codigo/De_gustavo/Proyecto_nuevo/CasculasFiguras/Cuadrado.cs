namespace CasculasFiguras;

public class Cuadrado : Poligono
{
    public  Cuadrado(int[] lado)
    {
        lados = lado;
    }
    public override int CalcularArea()
    {
        return lados[0] * lados[0];
    }
    public override int CalcularPerimetro()
    {
        return lados[0]*4;
    }
}
