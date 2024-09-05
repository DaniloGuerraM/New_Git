int numero;
int I = 0;
int N = 1;
string A = "*";
Console.WriteLine("instrodusca un numero menor a 100");
numero = Convert.ToInt32(Console.ReadLine());
do
{
    Console.WriteLine (N+"_ "+A);
    string B = A + "*";
    A = B ;
    int Y = I + 1;
    I = Y ;
    int M = N + 1;
    N = M;
} while (I<numero);










Convert.ToInt32(DateTime.Now)