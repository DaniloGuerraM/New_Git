public class Desarrollador extends Empleado {
    private String lenguaje;
    private String proyectoActual;
    private double bonificacion;

    public Desarrollador(String nombre, int edad, double salario, String lenguaje, String proyectoActual) {
        super(nombre, edad, salario);
        this.lenguaje = lenguaje;
        this.proyectoActual = proyectoActual;
        this.bonificacion = 0.0;
    }

    public void calcularBonificacion() {
        // Criterios para calcular la bonificación, por ejemplo, un 15% del salario
        this.bonificacion = getSalario() * 0.15;
        setSalario(getSalario() + this.bonificacion);
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Edad: " + getEdad());
        System.out.println("Salario: " + getSalario());
        System.out.println("Lenguaje: " + this.lenguaje);
        System.out.println("Proyecto Actual: " + this.proyectoActual);
        System.out.println("Bonificación: " + this.bonificacion);
    }
}
