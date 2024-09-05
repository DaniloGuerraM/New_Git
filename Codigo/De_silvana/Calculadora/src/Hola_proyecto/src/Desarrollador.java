package Hola_proyecto.src;

public class Desarrollador extends Empleados {
    private String lenguaje;
    private String proyectoActual;
    private double bonificacion;

    public Desarrollador(String nombre, int edad, double salario, String lenguaje, String proyectoActual) {
        super(nombre, edad, salario);
        this.lenguaje = lenguaje;
        this.proyectoActual = proyectoActual;
        this.bonificacion = 0;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public String getProyectoActual() {
        return proyectoActual;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void calcularBonificacion() {
        // Supongamos que la bonificación es el 5% del salario más un bono fijo de 500
        this.bonificacion = getSalario() * 0.05 + 500;
        setSalario(getSalario() + bonificacion);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Lenguaje: " + lenguaje);
        System.out.println("Proyecto Actual: " + proyectoActual);
        System.out.println("Bonificación: " + bonificacion);
    }

}
