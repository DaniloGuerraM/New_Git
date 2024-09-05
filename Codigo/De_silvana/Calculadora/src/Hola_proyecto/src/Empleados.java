package Hola_proyecto.src;

public class Empleados {

    private String nombre;
    private int edad;
    private double salario;

    public Empleados(String nombre2, int edad2, double salario2) {
        this.nombre = nombre2;
        this.edad = edad2;
        this.salario = salario2;
    }

    public void Empleado(String nombre, int edad, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Salario: " + salario);
    }
}
