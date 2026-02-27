package drones;

/**
 * Clase que representa un dron especializado para montaña.
 * Extiende de la clase base {@link Base}.
 * 
 * Añade un factor de altitud que afecta al cálculo del tiempo de respuesta.
 * 
 * @author Antonio Toret Cárdenas
 * @version 1.0
 */
public class DronMontana extends Base {

    /**
     * Factor de altitud aplicado en el cálculo del tiempo de respuesta.
     * Debe ser un valor mayor que 0.
     */
    private double factorAltitud;

    /**
     * Constructor de la clase DronMontana.
     * 
     * @param id Identificador único del dron.
     * @param autonomiaMax Autonomía máxima disponible del dron (en minutos).
     * @param velocidadMax Velocidad máxima del dron (en unidades de distancia por unidad de tiempo).
     * @param operativo Indica si el dron está operativo (true) o no (false).
     * @param factorAltitud Factor que multiplica el tiempo según la altitud (>0).
     * @throws IllegalArgumentException si el factorAltitud es menor o igual a 0.
     */
    public DronMontana(int id, int autonomiaMax, double velocidadMax,
                       boolean operativo, double factorAltitud) {
        super(id, autonomiaMax, velocidadMax, operativo);

        if (factorAltitud <= 0) {
            throw new IllegalArgumentException("El factor de altitud debe ser mayor que 0");
        }

        this.factorAltitud = factorAltitud;
    }

    /**
     * Calcula el tiempo de respuesta del dron para una misión dada la distancia.
     * El cálculo considera el estado operativo del dron, la velocidad máxima, 
     * el factor de altitud y la autonomía disponible.
     * 
     * Pasos para el cálculo:
     * <ol>
     *   <li>Verifica que el dron está operativo.</li>
     *   <li>Comprueba que la distancia no es negativa.</li>
     *   <li>Calcula el tiempo base dividiendo la distancia entre la velocidad máxima.</li>
     *   <li>Multiplica el tiempo base por el factor de altitud.</li>
     *   <li>Comprueba que el tiempo calculado no exceda la autonomía disponible.</li>
     * </ol>
     * 
     * @param distancia La distancia de la misión (debe ser >= 0).
     * @return El tiempo de respuesta calculado.
     * @throws Exception si el dron no está operativo o la autonomía es insuficiente.
     * @throws IllegalArgumentException si la distancia es negativa.
     */
    @Override
    public double calcularTiempoRespuesta(double distancia) throws Exception {

        comprobarEstado();

        if (distancia < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa.");
        }

        double tiempoBase = distancia / getVelocidadMax();

        double tiempoFinal = tiempoBase * factorAltitud;

        if (tiempoFinal > getAutonomiaMax()) {
            throw new Exception("La autonomía no es suficiente para esta misión.");
        }

        return tiempoFinal;
    }

    /**
     * Obtiene el factor de altitud del dron.
     * 
     * @return El factor de altitud (>0).
     */
    public double getFactorAltitud() {
        return factorAltitud;
    }
}