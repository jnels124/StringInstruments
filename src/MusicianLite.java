package src;

/**
 * Simple driver program for Instrument.
 * Constructs an InstrumentLite object and allows
 * playing that Instrument from the keyboard.
 * @author Stuart Reges
 * @author Jody Paul
 * @version August 2012
 */
public class MusicianLite {
    /**
     * This main driver embodies an infinite loop that senses
     * keypresses and plucks the associated string if one exists.
     * @param args ignored
     */
    public static void main(String[] args) {
        Instrument instr = new VisualInstrument();
        // This infinite loop requires the user to quit the application.
        while (true) {
            // Check if the user has typed a key; if so, process it.   
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (instr.hasString(key)) {
                    instr.pluck(key);
                } else {
                    System.out.println("bad key: " + key);
                }
            }
            instr.play();
            instr.tic();
        }
    }
}
