package Hola_proyecto.src;

public class Principal {
    public static void main(String[] args) {
        // Crear instancia de Gerente
        Gerente gerente = new Gerente("Ana Gómez", 45, 75000, "Ventas");
        gerente.calcularBonificacion();
        gerente.mostrarInformacion();

        // Crear instancia de Desarrollador
        Desarrollador desarrollador = new Desarrollador("Carlos Pérez", 30, 55000, "Java", "Proyecto Alpha");
        desarrollador.calcularBonificacion();
        desarrollador.mostrarInformacion();
    }
}
