using System;
class Padre
{

    public string ColorOjo {get; set;}
	public void Ojos(string o){
		Console.WriteLine("tenes lo ojos tu padre " + o );
	}
}
class Hijo : Padre
{
    public int AlturaHijo {get; set;}
	public void Altura(int a)
	{
		Console.WriteLine("La altura es de "+ a + " cm");
	}
}
class Programa{
    static void Main(){
    Hijo p = new Hijo();
    p.Altura(170);
    p.Ojos("marron");
    }

}
