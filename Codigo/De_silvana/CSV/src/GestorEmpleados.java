import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorEmpleados {
    private List<Empleado> empleados = new ArrayList<>();

    public void cargarDesdeCSV(String rutaArchivo) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(rutaArchivo));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("Nombre", "Edad", "Salario").withSkipHeaderRecord())) {

            for (CSVRecord csvRecord : csvParser) {
                String nombre = csvRecord.get("Nombre");
                int edad = Integer.parseInt(csvRecord.get("Edad"));
                double salario = Double.parseDouble(csvRecord.get("Salario"));

                empleados.add(new Empleado(nombre, edad, salario));
            }
        }
    }

    public void ordenarPorSalario(boolean ascendente) {
        empleados.sort(ascendente ? Comparator.comparingDouble(Empleado::getSalario) : Comparator.comparingDouble(Empleado::getSalario).reversed());
    }

    public void ordenarPorEdad(boolean ascendente) {
        empleados.sort(ascendente ? Comparator.comparingInt(Empleado::getEdad) : Comparator.comparingInt(Empleado::getEdad).reversed());
    }

    public List<Empleado> filtrarPorSalario(double minSalario, double maxSalario) {
        return empleados.stream()
                .filter(e -> e.getSalario() >= minSalario && e.getSalario() <= maxSalario)
                .collect(Collectors.toList());
    }

    public List<Empleado> filtrarPorEdad(int minEdad, int maxEdad) {
        return empleados.stream()
                .filter(e -> e.getEdad() >= minEdad && e.getEdad() <= maxEdad)
                .collect(Collectors.toList());
    }

    public void escribirCSV(String rutaArchivo, List<Empleado> listaEmpleados) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaArchivo));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Nombre", "Edad", "Salario"))) {

            for (Empleado empleado : listaEmpleados) {
                csvPrinter.printRecord(empleado.getNombre(), empleado.getEdad(), empleado.getSalario());
            }

            csvPrinter.flush();
        }
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}
