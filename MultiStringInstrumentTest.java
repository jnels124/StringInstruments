
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/** The MultiStringInstrumentTest class. */
public class MultiStringInstrumentTest {
    static String validKeys = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    static String invalidKeys = "1360ash";
    static String octaveKeys = "qi ";
    static MultiStringInstrument msi;

    /** Called before every test case method. */
    @Before
    public void setUp() {
        msi = new MultiStringInstrument();
    }

    @Test
    public void hasStringTest() {
        for (int i = 0; i < validKeys.length(); i++) {
            assertTrue(msi.hasString(validKeys.charAt(i)));
        }
        for (int i = 0; i < invalidKeys.length(); i++) {
            assertFalse(msi.hasString(invalidKeys.charAt(i)));
        }
    }

    @Test
    public void listen() {
        char key;
        String allKeys = octaveKeys + validKeys;
        for (int i = 0; i < allKeys.length(); i++) {
            key = allKeys.charAt(i);
            if (msi.hasString(key)) {
                msi.pluck(key);
            } else {
                System.err.println("unsupported key: " + key);
            }
            for (int j = 0; j < StdAudio.SAMPLE_RATE/3.0; j++) {
                msi.play();
                msi.tic();
            }
        }
    }
}
