import java.util.Scanner;

public class juego {
    public static void main(String[] args) {
        int numeroAleatorio = (int)(Math.random() * 100) + 1;
        int intentos = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ingresa un número entre 1 y 100:");
        
        while (true) {
            int numeroIngresado = scanner.nextInt();
            intentos++;
            if (numeroIngresado < 1 || numeroIngresado > 100) {
                System.out.println("El número no está dentro del rango especificado");
            } else if (numeroIngresado < numeroAleatorio) {
                System.out.println("mas grande");
            } else if (numeroIngresado > numeroAleatorio) {
                System.out.println("mas chico");
            } else {
                System.out.println(" Has adivinado el número correctamente en " + intentos + " intentos.");
                break;
            }
        }
    }
}


