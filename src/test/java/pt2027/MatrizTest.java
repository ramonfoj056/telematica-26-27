package pt2027;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;


/**
 * Pruebas de los constructores de Matriz.
 */
public class MatrizTest {

    /*
     * Comprobamos el caso más sencillo:
     * el constructor recibe una matriz rectangular.
     */
    @Test
    public void constructorMatrizRectangular() {

        double[][] datos = {
            { 1.0, 2.0, 3.0 },
            { 4.0, 5.0, 6.0 }
        };

        Matriz m = new Matriz(datos);

        assertArrayEquals(
                new int[] { 2, 3 },
                m.getDimension());

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(datos[i][j], m.getElemento(i, j), 1e-9);
            }
        }        
    }

    /*
     * Si las filas no tienen la misma longitud,
     * la matriz toma como número de columnas la longitud
     * de la fila más larga y completa con ceros.
     */
    @Test
    public void constructorRellenaConCeros() {

        double[][] datos = {
            { 1.0, 2.0, 3.0 },
            { 4.0, 5.0 },
            { 6.0 }
        };

        Matriz m = new Matriz(datos);

        assertArrayEquals(
                new int[] { 3, 3 },
                m.getDimension());

        assertEquals(4.0, m.getElemento(1, 0), 1e-9);
        assertEquals(5.0, m.getElemento(1, 1), 1e-9);
        assertEquals(0.0, m.getElemento(1, 2), 1e-9);

        assertEquals(6.0, m.getElemento(2, 0), 1e-9);
        assertEquals(0.0, m.getElemento(2, 1), 1e-9);
        assertEquals(0.0, m.getElemento(2, 2), 1e-9);
    }

    /*
     * El constructor debe copiar los datos recibidos.
     * Modificar posteriormente el array original no debe
     * modificar la matriz.
     */
    @Test
    public void constructorHaceUnaCopiaDeLosDatos() {

        double[][] datos = {
            { 1.0, 2.0 },
            { 3.0, 4.0 }
        };

        Matriz m = new Matriz(datos);

        datos[0][0] = 100.0;

        assertEquals(1.0, m.getElemento(0, 0), 1e-9);
    }

    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * un array null o vacío.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizNull() {
        new Matriz(null);
    }  
    
    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * un array vacío.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizVacia() {
        new Matriz(new double[0][0]);
    }

    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * todos los arrays con filas vacías.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizConFilasVacias() {
        double[][] datos = {
            { },
            { }
        };

        new Matriz(datos);
    }

    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * un array con filas null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizConFilasNull() {
        double[][] datos = {
            { 1.0, 2.0 },
            null
        };

        new Matriz(datos);
    }
    @Test
public void sumarMatricesDimensionesValidas() {
    double[][] datos1 = { { 1.0, 2.0 }, { 3.0, 4.0 } };
    double[][] datos2 = { { 5.0, 6.0 }, { 7.0, 8.0 } };
    Matriz m1 = new Matriz(datos1);
    Matriz m2 = new Matriz(datos2);

    Matriz res = m1.sumar(m2);

    assertNotNull(res);
    assertEquals(6.0, res.getElemento(0, 0), 1e-9);
    assertEquals(8.0, res.getElemento(0, 1), 1e-9);
    assertEquals(10.0, res.getElemento(1, 0), 1e-9);
    assertEquals(12.0, res.getElemento(1, 1), 1e-9);
}

@Test
public void sumarMatrizNullODimensionIncompatible() {
    double[][] datos1 = { { 1.0, 2.0, 3.0 } };
    double[][] datos2 = { { 1.0, 2.0 }, { 3.0, 4.0 } };
    Matriz m1 = new Matriz(datos1);
    Matriz m2 = new Matriz(datos2);

    // Sumar null debe devolver null
    assertNull(m1.sumar(null));
    
    // Sumar matrices de diferente dimensión debe devolver null
    assertNull(m1.sumar(m2));
}
@Test
public void traspuestaMatrizRectangular() {
    double[][] datos = {
        { 1.0, 2.0, 3.0 },
        { 4.0, 5.0, 6.0 }
    };
    Matriz m = new Matriz(datos);
    Matriz t = m.traspuesta();

    assertNotNull(t);
    // Una matriz de 2x3 pasa a ser de 3x2
    assertArrayEquals(new int[] { 3, 2 }, t.getDimension());

    // Verificación de los valores traspuestos
    assertEquals(1.0, t.getElemento(0, 0), 1e-9);
    assertEquals(4.0, t.getElemento(0, 1), 1e-9);
    assertEquals(2.0, t.getElemento(1, 0), 1e-9);
    assertEquals(5.0, t.getElemento(1, 1), 1e-9);
    assertEquals(3.0, t.getElemento(2, 0), 1e-9);
    assertEquals(6.0, t.getElemento(2, 1), 1e-9);
}

@Test
public void traspuestaEstaticaMatrizValidaYNull() {
    double[][] datos = { { 1.0, 2.0 }, { 3.0, 4.0 } };
    Matriz m = new Matriz(datos);

    // Versión estática con matriz válida
    Matriz t = Matriz.traspuesta(m);
    assertNotNull(t);
    assertEquals(3.0, t.getElemento(0, 1), 1e-9);

    // Versión estática con argumento null
    assertNull(Matriz.traspuesta(null));
}
}
