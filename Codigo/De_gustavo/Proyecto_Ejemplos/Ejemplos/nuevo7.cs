using System;

public class Excepciones
{
    public Static void Main(String[] args)
    {
        try
        {
            int[] nums = {10, 120, 12}
            int index = 5;
            int value = nums[index];
        }
        catch (ArgumentOutOfRangeExcepcion  ex)
        {
            Console.WriteLine($"Se produjo un error: {ex.Message}");
        }
    }

}



