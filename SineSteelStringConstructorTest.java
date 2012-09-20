
import static org.junit.Assert.*;
import org.junit.Test;
/** Test class for array-parameter constructors. */
public class SineSteelStringConstructorTest {
    InstrumentString is;
    @Test
    public void sineConstructorTicTest() {
        double[] d = { 0.10, 0.20, 0.30, 0.40 };
        try {
            is = new SineString(d);
        } catch (Exception e) {
            fail("Array parameter SineString constructor failed.");
        }
        assertEquals(0.10, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.20, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.30, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.40, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.099, is.sample(), 0.0001);
    }
    @Test
    public void steelConstructorTicTest() {
        double[] d = { 0.10, 0.20, 0.30, 0.40 };
        try {
            is = new SteelString(d);
        } catch (Exception e) {
            fail("Array parameter SteelString constructor failed.");
        }
        assertEquals(0.10, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.20, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.30, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.40, is.sample(), 0.0001);
        is.tic();
        assertEquals(0.1494, is.sample(), 0.0001);
    }
}