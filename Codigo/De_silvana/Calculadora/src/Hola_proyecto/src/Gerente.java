package Hola_proyecto.src;

public class Gerente extends Empleados {
    private String departamento;
    private double bonificacion;

    public Gerente(String nombre, int edad, double salario, String departamento) {
        super(nombre, edad, salario);
        this.departamento = departamento;
        this.bonificacion = 0;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void calcularBonificacion() {
        // Supongamos que la bonificación es el 10% del salario
        this.bonificacion = getSalario() * 0.10;
        setSalario(getSalario() + bonificacion);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Departamento: " + departamento);
        System.out.println("Bonificación: " + bonificacion);
    }
}
