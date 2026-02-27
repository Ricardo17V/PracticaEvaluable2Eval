package drones;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DronMontanaTest {

    private DronMontana dron;

    private double distancia;
    private double factorAltitud;

    // Constructor para el test parametrizado
    public DronMontanaTest(double distancia, double factorAltitud) {
        this.distancia = distancia;
        this.factorAltitud = factorAltitud;
    }

    // Valores que pide el enunciado
    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][] {
            {10, 1.5},
            {7, 1.05},
            {5, 0.75}
        });
    }

    @Before
    public void setUp() {
        // Crear el dron con los valores obligatorios
        dron = new DronMontana(1, 120, 10, true, factorAltitud);
    }

    @After
    public void tearDown() {
        // Desactivar el dron al final (creándolo inoperativo)
        dron = new DronMontana(1, 120, 10, false, factorAltitud);
    }

    // ✅ Caso correcto
    @Test
    public void testCalculoTiempoCorrecto() throws Exception {
        double esperado = (distancia / 10.0) * factorAltitud;
        double resultado = dron.calcularTiempoRespuesta(distancia);

        assertEquals(esperado, resultado, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDistanciaNegativa() throws Exception {
        dron.calcularTiempoRespuesta(-5);
    }

    @Test(expected = Exception.class)
    public void testDronNoOperativo() throws Exception {
        DronMontana dronNoOperativo =
                new DronMontana(1, 120, 10, false, factorAltitud);

        dronNoOperativo.calcularTiempoRespuesta(10);
    }

    @Test(expected = Exception.class)
    public void testAutonomiaInsuficiente() throws Exception {
        DronMontana dronPocaAutonomia =
                new DronMontana(1, 1, 10, true, factorAltitud);

        dronPocaAutonomia.calcularTiempoRespuesta(50);
    }
}
