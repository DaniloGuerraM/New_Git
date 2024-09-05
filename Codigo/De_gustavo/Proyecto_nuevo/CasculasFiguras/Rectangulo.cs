namespace CasculasFiguras;

public class Rectangulo : Poligono
{
    public Rectangulo(int[] lado)
    {
        lados = lado;
    }
    public override int CalcularArea()
    {
        return lados[0]* lados[1];
    }
    public override int CalcularPerimetro()
    {
        return lados[0]*2 + lados[1]*2;
    }
}
