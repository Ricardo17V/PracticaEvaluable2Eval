package drones;

public class DronMontana extends Base {

    private double factorAltitud; // > 0

    public DronMontana(int id, int autonomiaMax, double velocidadMax,
        boolean operativo, double factorAltitud) {
        super(id, autonomiaMax, velocidadMax, operativo);

        if (factorAltitud <= 0) {
            throw new IllegalArgumentException("El factor de altitud debe ser mayor que 0");
        }

        this.factorAltitud = factorAltitud;
    }

    @Override
    public double calcularTiempoRespuesta(double distancia) throws Exception {

        // 1️ Comprobar estado (obligatorio según el enunciado)
        comprobarEstado();

        // 5️ Si la distancia es negativa → excepción
        if (distancia < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa.");
        }

        // 2️⃣ Distancia / velocidad máxima
        double tiempoBase = distancia / getVelocidadMax();

        // 3️⃣ Multiplicar por factor de altitud
        double tiempoFinal = tiempoBase * factorAltitud;

        // 4️⃣ Comprobar que no exceda la autonomía
        if (tiempoFinal > getAutonomiaMax()) {
            throw new Exception("La autonomía no es suficiente para esta misión.");
        }

        return tiempoFinal;
    }

    public double getFactorAltitud() {
        return factorAltitud;
    }
}