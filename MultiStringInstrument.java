import java.util.*; 
/**
 * This class implements the instrument interface and creates a Instrument with 
 * 37 different strings. (More can be added by adding additional keys to KEYBOARD)
 * 
 * @author (Jesse Nelson) 
 * @version (September 4, 2012 : Windows 7(x64) Java 1.7)
 */
public class MultiStringInstrument implements Instrument {
    /**
     * This is the Keyboard to map the keys pressed by the user to the correct location in array
     */
    static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
    /**
     * The frequency of the first key 'q'
     */
    static final double initFreq = 110.0;
    /**
     * Array to keep track of InstrumentString objects
     */
    private InstrumentString [] instrObjects;
    
    /**
     * Initializes the elements in the array with the correct frequency for it's place in the 
     * array.
     */
    public MultiStringInstrument() {
        instrObjects = new InstrumentString[KEYBOARD.length()];
        for(int i = 0; i < KEYBOARD.length(); i++) {
            instrObjects[i] = new SteelString(initFreq * (Math.pow(2, i/12.0)));
        }
    }
    
    /**
     * Checks the parameter to determine if it is a valid string for the instrument
     */
    public boolean hasString(char string) {
        for(int i = 0; i < KEYBOARD.length(); i++) {
            if(string == KEYBOARD.charAt(i)) return true;
        }
        return false;
    }
    
    /**
     * Takes the parameter and plucks the corresponding string or throws an IllegalArgumentException
     * if the string doesn't exist. 
     */
    public void pluck(char string) throws IllegalArgumentException {
        if(!hasString(string))
            throw new IllegalArgumentException();
        int plucked = KEYBOARD.indexOf(string);     // Locates location of correct string
        this.instrObjects[plucked].pluck();
    }
    
    /**
     * Takes the current sample from all strings,adds them, and sends them to
     * the sourceLine in StdAudio to play the sample
     */
    public void play() {
        double sample = 0;
        for(int i = 0; i < KEYBOARD.length(); i++) {
            sample += this.instrObjects[i].sample();
        }
        StdAudio.play(sample);
    }
    
    /**
     * Calls the corresponding tic() method for each InstrumentString object
     */
    public void tic () {
        for(int i = 0; i < instrObjects.length; i++)
            this.instrObjects[i].tic();
    }
}
