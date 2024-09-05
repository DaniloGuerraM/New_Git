import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorEmpleados gestor = new GestorEmpleados();
        List<Empleado> empleados = gestor.leerEmpleadosDesdeCSV("empleados.csv");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una opción:");
        System.out.println("1. Ordenar por Salario");
        System.out.println("2. Ordenar por Edad");
        System.out.println("3. Filtrar por Salario");
        System.out.println("4. Filtrar por Edad");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ordenar por Salario Ascendente? (true/false):");
                boolean ascSalario = scanner.nextBoolean();
                empleados = gestor.ordenarPorSalario(empleados, ascSalario);
                break;
            case 2:
                System.out.println("Ordenar por Edad Ascendente? (true/false):");
                boolean ascEdad = scanner.nextBoolean();
                empleados = gestor.ordenarPorEdad(empleados, ascEdad);
                break;
            case 3:
                System.out.println("Ingrese el rango de salario (min max):");
                double minSalario = scanner.nextDouble();
                double maxSalario = scanner.nextDouble();
                empleados = gestor.filtrarPorSalario(empleados, minSalario, maxSalario);
                break;
            case 4:
                System.out.println("Ingrese el rango de edad (min max):");
                int minEdad = scanner.nextInt();
                int maxEdad = scanner.nextInt();
                empleados = gestor.filtrarPorEdad(empleados, minEdad, maxEdad);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Mostrar resultados en la consola
        empleados.forEach(System.out::println);

        // Guardar resultados en un nuevo archivo CSV
        gestor.escribirEmpleadosEnCSV("resultados.csv", empleados);

        scanner.close();
    }
}
