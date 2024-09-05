import java.util.Scanner;


public class Temperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ingresa la temperatura en grados Celsius:");
        try {
            double celsius = scanner.nextDouble();
            double fahrenheit = convertirCelsiusAFahrenheit(celsius);
            System.out.println("La temperatura en grados Fahrenheit es: " + fahrenheit);
        } catch (Exception e) {
            System.out.print("Acaba de ocurrir un error:" );
            System.out.println( e.getMessage());
        }

        
    }

    public static double convertirCelsiusAFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }
}

