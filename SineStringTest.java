import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SineStringTest.
 *
 * @author  Dr. Jody Paul
 * @version August 2012
 */
public class SineStringTest {
    @Test
    public void energyDecayTest() {
        assertEquals(0.990, SineString.ENERGY_DECAY_FACTOR, 0.0001);
    }
    
    @Test
    public void ticTest() {
        SineString ss440 = new SineString(440.0);
        assertEquals(0, ss440.time());
        ss440.tic();
        assertEquals(1, ss440.time());
        for (int i = 0; i < 100; i++) ss440.tic();
        assertEquals(101, ss440.time());
    }

    @Test
    public void constructorTest() {
        SineString ss220 = new SineString(220.0);
        assertEquals(0.0, ss220.sample(), 0.0001);
        try {
            SineString ssBad = new SineString(-220.0);
            fail("SineString constructor accepted bad parameter (-220.0).");
        } catch (IllegalArgumentException e) {
            assertTrue(true); // Correct exception type thrown.
        } catch (Exception e) {
            fail("SineString constructor threw incorrect exception type: " + e);
        }
    }

    @Test
    public void pluckTest() {
        SineString ss440 = new SineString(440.0);
        assertEquals(0.0, ss440.sample(), 0.0001);
        ss440.pluck();
        double sample1 = ss440.sample();
        ss440.tic();
        double sample2 = ss440.sample();
        ss440.tic();
        double sample3 = ss440.sample();
        assertFalse(Math.abs(sample1-sample2)<0.000001);
        assertFalse(Math.abs(sample1-sample3)<0.000001);
        assertFalse(Math.abs(sample2-sample3)<0.000001);
    }
    
    @Test
    public void listen() {
        SineString ss440 = new SineString(440.0);
        ss440.pluck();
        for (int i = 0; i < 40000; i++) {
            StdAudio.play(ss440.sample());
            ss440.tic();
        }
    }
}
