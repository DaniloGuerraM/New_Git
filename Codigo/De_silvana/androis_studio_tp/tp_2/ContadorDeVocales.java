import java.util.Scanner;

public class ContadorDeVocales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ingresa una cadena de texto:");
        String texto = scanner.nextLine();

        if (texto.isEmpty()) {
            System.out.println("La cadena de texto ingresada está vacía");
        } else {
            int conteo = contarVocales(texto);
            System.out.println("El número de vocales en la cadena de texto ingresada es: " + conteo);
        }
    }

    public static int contarVocales(String texto) {
        int conteo = 0;
        String textoEnMinusculas = texto.toLowerCase();
        for (int i = 0; i < textoEnMinusculas.length(); i++) {
            char caracter = textoEnMinusculas.charAt(i);
            if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u') {
                conteo++;
            }
        }
        return conteo;
    }
}
