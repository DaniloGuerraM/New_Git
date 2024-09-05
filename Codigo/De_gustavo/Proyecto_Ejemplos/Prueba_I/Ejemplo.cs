namespace Prueba_I;

public class Ejemplo
{
    public void Suma()
    {
        Console.WriteLine("Ingrese dos numeros");
        int n1 = Convert.ToInt32(Console.ReadLine());
        int n2 = Convert.ToInt32(Console.ReadLine());
        try 
        {
            int n3 = n1 + n2;
        }
        catch (Exception ex)
        {
            Console.WriteLine("hola");
        }
    }
}
