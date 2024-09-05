namespace cuit;

public class validacion
{
    private readonly int[] ValorDigito = new int[] { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2};

    public bool EsCUITValido(string cuitString)
    {      
        int digitoVerificador = (int) Char.GetNumericValue(cuitString[cuitString.Length-1]);        
        Console.WriteLine(digitoVerificador);    

        int sumatoria = 0;                  
        for (int i = 0; i < cuitString.Length-1; i++)
        {
           
            int numero = (int) Char.GetNumericValue(cuitString[i]);        
           
            int multiplicacion = (numero * ValorDigito[i]);
            sumatoria = sumatoria + multiplicacion;
        }

        int resto = sumatoria % 11;

   
        if (resto == 0)
        {
            return resto == digitoVerificador;
        }
        else if (resto == 1)
        {
            return (digitoVerificador == 9) || (digitoVerificador == 4);
        }

        return (11 - resto) == digitoVerificador;
    }
}
}
