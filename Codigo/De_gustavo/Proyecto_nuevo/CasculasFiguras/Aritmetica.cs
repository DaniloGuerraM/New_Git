namespace CasculasFiguras;

public class Aritmetica
{
    public Poligono CrearPoligono(int[] tipo)
    {
        if (tipo.Length == 3)
        {
            return new Triangulo(tipo);
        }
        else if (tipo.Length == 1)
        {
            return new Cuadrado(tipo);

        }
        else if (tipo.Length == 2)
        {
            return new Rectangulo(tipo);
        }
        throw new InvalidProgramException("Los dados son incorrectos");
    }
}
