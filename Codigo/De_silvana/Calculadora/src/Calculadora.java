import java.util.Scanner;

public class Calculadora
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Comensemos con la calculadora");

        System.out.println("ingese el primer numero");
        int num1 = scanner.nextInt();
        
        System.out.println("ingese el segundo numero");
        int num2 = scanner.nextInt();

        System.out.println("Ingrece la operación:");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                Sumar(num1, num2);
                
                break;
            case 2:
                Restar(num1, num2);
                break;
            case 3:
                Multiplicar(num1, num2);
                break;
            case 4:
                Dividir(num1, num2);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        scanner.close();
    }
    public static void Sumar(int a, int b)
    {
        int res = a + b ;
        System.out.println("El resultado de la suma es " + res);
    }
    public static void Restar(int a, int b)
    {
        if (a > b)
        {
            int res = a - b ;
            System.out.println("El resultado de la suma es " + res);
        }
        else
        {
            int res = b - a ;
            System.out.println("El resultado de la suma es " + res);
        }
        
    }
    public static void Multiplicar(int a, int b)
    {
        int res = a * b ;
        System.out.println("El resultado de la suma es " + res);
    }
    public static void Dividir(int a, int b)
    {
        int sero = b;
        if (sero == 0)
        {
            System.out.println("no se puede Dividir entre 0");
        }
        else
        {
            int res = a / b ;
            System.out.println("El resultado de la suma es " + res);
        }
    }
}