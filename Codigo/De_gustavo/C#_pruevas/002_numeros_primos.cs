using System;
public class Program
{
	public static void Main()
	{
		bool isPriM = true;
		int num;
		Console.WriteLine("Ingrese un numero");
		num = int.Parse(Console.ReadLine());
		for (int i = 2; i <= Math.Sqrt(num); i++)
		{
			if ((num % i) == 0)
			{
				isPriM = false;
				break;
			}
		}
		if (isPriM)
		{
			Console.WriteLine("Es primo");
		}else{
			Console.WriteLine("No es primo");
		}
	}
}