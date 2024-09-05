public class Main {
    public static void main(String[] args) {
        Gerente gerente = new Gerente("Carlos Pérez", 45, 80000, "Ventas");
        Desarrollador desarrollador = new Desarrollador("Ana Gómez", 30, 60000, "Java", "Proyecto Alpha");

        // Calcular bonificaciones
        gerente.calcularBonificacion();
        desarrollador.calcularBonificacion();

        // Mostrar información detallada
        System.out.println("Información del Gerente:");
        gerente.mostrarInformacion();

        System.out.println("\nInformación del Desarrollador:");
        desarrollador.mostrarInformacion();
    }
}
