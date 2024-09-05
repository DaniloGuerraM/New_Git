int PN, SN, RESTO;
Console.WriteLine("introdusca el primer numero ");
PN = Convert.ToInt32(Console.ReadLine());
Console.WriteLine("instrodusco el segundo numero");
SN = Convert.ToInt32(Console.ReadLine());
for (int i=PN;i<=SN;i++)
{
    RESTO = i % 2;
    int result=Math.Abs(RESTO);
    if (result==1)
    {
        Console.WriteLine(i);
    }
}