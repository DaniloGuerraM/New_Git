import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {

    public List<Empleado> leerEmpleadosDesdeCSV(String archivo) {
        List<Empleado> empleados = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(archivo), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : parser) {
                String nombre = record.get("Nombre");
                int edad = Integer.parseInt(record.get("Edad"));
                double salario = Double.parseDouble(record.get("Salario"));
                empleados.add(new Empleado(nombre, edad, salario));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }
	 public List<Empleado> ordenarPorSalario(List<Empleado> empleados, boolean ascendente) {
        return empleados.stream()
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed().reversed())
                .collect(Collectors.toList());
    }

    public List<Empleado> ordenarPorEdad(List<Empleado> empleados, boolean ascendente) {
        return empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .collect(Collectors.toList());
    }

    public List<Empleado> filtrarPorSalario(List<Empleado> empleados, double min, double max) {
        return empleados.stream()
                .filter(e -> e.getSalario() >= min && e.getSalario() <= max)
                .collect(Collectors.toList());
    }

    public List<Empleado> filtrarPorEdad(List<Empleado> empleados, int min, int max) {
        return empleados.stream()
                .filter(e -> e.getEdad() >= min && e.getEdad() <= max)
                .collect(Collectors.toList());
    }
	 public void escribirEmpleadosEnCSV(String archivo, List<Empleado> empleados) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(archivo), CSVFormat.DEFAULT.withHeader("Nombre", "Edad", "Salario"))) {
            for (Empleado empleado : empleados) {
                printer.printRecord(empleado.getNombre(), empleado.getEdad(), empleado.getSalario());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
