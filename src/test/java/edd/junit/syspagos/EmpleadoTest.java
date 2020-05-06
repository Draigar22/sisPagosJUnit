package edd.junit.syspagos;

import edd.junit.syspagos.excepciones.CargoException;
import edd.junit.syspagos.excepciones.MesesTrabajoException;
import edd.junit.syspagos.excepciones.NombreEmpleadoException;
import edd.junit.syspagos.excepciones.NumeroEmpleadoException;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class EmpleadoTest {

    @Test @DisplayName("Establecer numero rango valido")
    public void establecerNumero_empleado_Correcto() {

        Empleado e = new Empleado();
        try {
            e.establecerNumero_empleado("4");
        } catch (NumeroEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(4,e.getNumero_empleado());
    }

    @Test @DisplayName("Establecer numero limite 000")
    public void establecerNumero_empleado_000() {

        Empleado e = new Empleado();
        Exception exception = Assertions.assertThrows(NumeroEmpleadoException.class, () -> {
            e.establecerNumero_empleado("000");
        });
    }

    @Test
    public void establecerNumero_empleado_999() {

        Empleado e = new Empleado();
        Exception exception = Assertions.assertThrows(NumeroEmpleadoException.class, () -> {
            e.establecerNumero_empleado("999");
        });
    }


    @Test @DisplayName("Establecer nombre correcto")
    public void estableceNombre_empleado_correcto() {

        Empleado e = new Empleado();
        String nombre = "Pedro";

        try {
            e.estableceNombre_empleado(nombre);
        } catch (NombreEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(nombre, e.getNombre_empleado());
    }

    // Debe dar error, no queremos que el nombre tenga números
    @Test @DisplayName("Establecer nombre con numero")
    public void estableceNombre_empleado_contieneNumero() {

        Empleado e = new Empleado();
        String nombre = "Pedro3";

        try {
            e.estableceNombre_empleado(nombre);
        } catch (NombreEmpleadoException ex) {
            Assertions.fail("El nombre no puede contener números");
        }
    }

    @Test @DisplayName("Establecer nombre vacio")
    public void estableceNombre_empleado_vacio() {

        Empleado e = new Empleado();
        String nombre = "";

        try {
            e.estableceNombre_empleado(nombre);
        } catch (NombreEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertNotEquals("", nombre, "El nombre no puede estar vacío");
    }

    @Test @DisplayName("Estrablecer meses 000")
    public void estableceMeses_Trabajo_000() {

        Empleado e = new Empleado();
        try {
            e.estableceMeses_Trabajo("0");
        } catch (MesesTrabajoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(0,e.getNumero_empleado());

    }

    @Test @DisplayName("Estrablecer meses número negativo")
    public void estableceMeses_Trabajo_numNegativo() {

        Empleado e = new Empleado();
        try {
            e.estableceMeses_Trabajo("-3");
        } catch (MesesTrabajoException ex) {
            Assertions.fail("El número no puede ser negativo");
        }
    }

    @Test @DisplayName("Establecer meses no número")
    public void estableceMeses_Trabajo_noNum() {

        Empleado e = new Empleado();
        try {
            e.estableceMeses_Trabajo("Dos");
        } catch (MesesTrabajoException ex) {
            Assertions.fail("Mes tiene que ser un número");
        }
    }

    @Test @DisplayName("Establecer directivo correcto")
    public void establecerSerDirectivo_correcto() {

        Empleado e = new Empleado();
        Empleado e2 = new Empleado();

        try {
            e.establecerSerDirectivo("+");
            e2.establecerSerDirectivo("-");
        } catch (CargoException ex) {
            Assertions.fail();
        }
        Assertions.assertTrue(e.getDirectivo());
        Assertions.assertFalse(e2.getDirectivo());
    }

    @Test @DisplayName("Establecer directivo otro")
    public void establecerSerDirectivo_otro() {

        Empleado e = new Empleado();
        try {
            e.establecerSerDirectivo("/");
        } catch (CargoException ex) {
            Assertions.fail("Ha de introducirse '+' o '-'");
        }
    }

    @Test @DisplayName("Establecer prima P1 correcto")
    public void calcularPrima_correcto_p1() {

        Empleado e = new Empleado();
        try {
            e.calcularPrima("625", "Jorge", "035", "+" );
        } catch (CargoException | NombreEmpleadoException | MesesTrabajoException | NumeroEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(Prima.P1, e.getPrima(), "La prima ha de ser P1");
    }

    @Test @DisplayName("Establecer prima P2 correcto")
    public void calcularPrima_correcto_p2() {

        Empleado e = new Empleado();
        try {
            e.calcularPrima("321", "Adolfo", "020", "-" );
        } catch (CargoException | NombreEmpleadoException | MesesTrabajoException | NumeroEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(Prima.P2, e.getPrima(), "La prima ha de ser P2");
    }

    @Test @DisplayName("Establecer prima P3 correcto")
    public void calcularPrima_correcto_p3() {

        Empleado e = new Empleado();
        try {
            e.calcularPrima("923", "Daniel", "010", "+" );
        } catch (CargoException | NombreEmpleadoException | MesesTrabajoException | NumeroEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(Prima.P3, e.getPrima(), "La prima ha de ser P3");
    }

    @Test @DisplayName("Establecer prima P4 correcto")
    public void calcularPrima_correcto_p4() {

        Empleado e = new Empleado();
        try {
            e.calcularPrima("329", "Leinad", "001", "-" );
        } catch (CargoException | NombreEmpleadoException | MesesTrabajoException | NumeroEmpleadoException ex) {
            Assertions.fail();
        }
        Assertions.assertEquals(Prima.P4, e.getPrima(), "La prima ha de ser P4.");
    }

    @Test @DisplayName("Establecer prima (No directivo y 12 meses)")
    public void calcularPrima_correcto_otro() {

        Empleado e = new Empleado();
        try {
            e.calcularPrima("329", "Leinad", "12", "-" );
        } catch (CargoException | NombreEmpleadoException | MesesTrabajoException | NumeroEmpleadoException | RuntimeException ex) {
            Assertions.fail("Toma RuntimeException");
        }
    }

}
