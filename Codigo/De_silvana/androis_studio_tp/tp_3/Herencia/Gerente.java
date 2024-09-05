public class Gerente extends Empleado {
    private String departamento;
    private double bonificacion;

    public Gerente(String nombre, int edad, double salario, String departamento) {
        super(nombre, edad, salario);
        this.departamento = departamento;
        this.bonificacion = 0.0;
    }

    public void calcularBonificacion() {
        // Criterios para calcular la bonificación, por ejemplo, un 20% del salario
        this.bonificacion = getSalario() * 0.20;
        setSalario(getSalario() + this.bonificacion);
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Edad: " + getEdad());
        System.out.println("Salario: " + getSalario());
        System.out.println("Departamento: " + this.departamento);
        System.out.println("Bonificación: " + this.bonificacion);
    }
}
