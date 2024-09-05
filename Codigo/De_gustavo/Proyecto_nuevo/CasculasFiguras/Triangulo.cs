namespace CasculasFiguras;

public class Triangulo : Poligono
{
    public Triangulo(int[] lado)
    {
        lados = lado;
    }
    public override int CalcularArea()
    {
        int baset = lados[0] / 2;
        double basel = Convert.ToDouble(lados[0]);
        double altura = Math.Sqrt((lados[1]*lados[1])-(baset*baset));
        int resultado = Convert.ToInt32(basel*altura/2);
        return resultado;
    }
    public override int CalcularPerimetro()
    {
        int resultado = lados[0] + lados[1]+ lados[2];
        return resultado;
    }
}
