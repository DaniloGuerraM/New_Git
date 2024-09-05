import java.util.Scanner;

public class ContadordeCaracteres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, ingresa una cadena de texto:");
        String cadena = scanner.nextLine();

        int numCaracter = contarCaracteres(cadena);
        System.out.println("La cadena (" + cadena + ") tiene " + numCaracter + " caracteres");
    }

    public static int contarCaracteres(String texto) {
        int numCaracter = 0;
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (Character.isDigit(caracter)) {
                numCaracter++;
            }
            else if (Character.isLetter(caracter))
            {
                numCaracter++;
            }
        }
        return numCaracter;
    }
}