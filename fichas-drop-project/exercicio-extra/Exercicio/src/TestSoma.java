import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestSoma {

    @Test
    public void testSomaPositivo() {
        int soma = Main.soma(new int[]{5,4,1});

        assertEquals(10,soma);
    }

    @Test
    public void testSomaNegativo() {
        int soma = Main.soma(new int[]{-2,-9,-1,-1,-5});

        assertEquals(-18,soma);
    }

    @Test
    public void testZeros() {
        int soma = Main.soma(new int[]{0,0,0});

        assertEquals(0,soma);
    }

    @Test
    public void testNulls() {
        int soma = Main.soma(null);

        assertEquals(0,soma);
    }

    @Test
    public void testVazio() {
        int soma = Main.soma(new int[]{});

        assertEquals(0,soma);
    }

    @Test
    public void testUmElemento() {
        int soma = Main.soma(new int[]{4});

        assertEquals(4,soma);
    }

    @Test
    public void testArrayGrande() {

    }
}
