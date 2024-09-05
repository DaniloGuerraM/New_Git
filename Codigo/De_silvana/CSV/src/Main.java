import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorEmpleados gestor = new GestorEmpleados();
        Scanner scanner = new Scanner(System.in);

        try {
            gestor.cargarDesdeCSV("empleados.csv");

            System.out.println("Seleccione una opción:");
            System.out.println("1. Ordenar por Salario (ascendente)");
            System.out.println("2. Ordenar por Salario (descendente)");
            System.out.println("3. Ordenar por Edad (ascendente)");
            System.out.println("4. Ordenar por Edad (descendente)");
            System.out.println("5. Filtrar por Salario");
            System.out.println("6. Filtrar por Edad");
            int opcion = scanner.nextInt();

            List<Empleado> resultado = null;

            switch (opcion) {
                case 1:
                    gestor.ordenarPorSalario(true);
                    resultado = gestor.getEmpleados();
                    break;
                case 2:
                    gestor.ordenarPorSalario(false);
                    resultado = gestor.getEmpleados();
                    break;
                case 3:
                    gestor.ordenarPorEdad(true);
                    resultado = gestor.getEmpleados();
                    break;
                case 4:
                    gestor.ordenarPorEdad(false);
                    resultado = gestor.getEmpleados();
                    break;
                case 5:
                    System.out.println("Ingrese el salario mínimo:");
                    double minSalario = scanner.nextDouble();
                    System.out.println("Ingrese el salario máximo:");
                    double maxSalario = scanner.nextDouble();
                    resultado = gestor.filtrarPorSalario(minSalario, maxSalario);
                    break;
                case 6:
                    System.out.println("Ingrese la edad mínima:");
                    int minEdad = scanner.nextInt();
                    System.out.println("Ingrese la edad máxima:");
                    int maxEdad = scanner.nextInt();
                    resultado = gestor.filtrarPorEdad(minEdad, maxEdad);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            if (resultado != null) {
                for (Empleado e : resultado) {
                    System.out.println(e);
                }

                System.out.println("¿Desea guardar los resultados en un archivo CSV? (s/n)");
                String guardar = scanner.next();
                if (guardar.equalsIgnoreCase("s")) {
                    System.out.println("Ingrese el nombre del archivo de salida:");
                    String archivoSalida = scanner.next();
                    gestor.escribirCSV(archivoSalida, resultado);
                    System.out.println("Resultados guardados en " + archivoSalida);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
}
