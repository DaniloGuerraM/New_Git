
namespace Prueba_I;

public class Nueva8
{
    public void Ejemplo()
    {
        Console.WriteLine("ingrese dos numeros");
        
        int nums = Convert.ToInt32(Console.ReadLine());
        int index = Convert.ToInt32(Console.ReadLine());
        try
        {
            int value = nums * index;
        }
        catch (Exception  ex)
        {
            Console.WriteLine($"Se produjo un error: {ex.Message}");
        }
            finally
        {
            Console.WriteLine("Siempre pasa");
        }
    }
}

