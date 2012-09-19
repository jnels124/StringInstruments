import java.util.*;
/**
 * This class implements the InstrumentString interface and creates a Sine wave.
 * 
 * @author (Jesse Nelson) 
 * @version (Windows 7 (x64) Java 1.7 09/01/2012)
 */
public class SineString implements InstrumentString {

    final static double ENERGY_DECAY_FACTOR = 0.990;        // Decay factor for SineString
    private int ticCTR;                                     // Counter to track # of times tic() is called
    private ringBuffer rb;                                  // Holds values to create sine wave
    private double Pi2Freq;                                 // Frequecny of SineString * 2 * PI
    
    /**
     * Creates a ringBuffer object, it's parameter being the size of the ringBuffer being created.
     * All values in the buffer are initialized to 0 to imitate a SineString at rest.
     * 
     * An IllegalArgumentException is thrown if the pre-conditons are not met.
     * 
     * Pre-Conditions- The frequency is a positive integer.
     *                 The bufferSize is > 2 
     */
    public SineString(double frequency) throws IllegalArgumentException {
        this.rb = new ringBuffer(Math.round((float)(StdAudio.SAMPLE_RATE/ frequency)));
        if(frequency < 0 || this.rb.bufferSize < 2)
            throw new IllegalArgumentException("Please enter a higher frequency");
        this.ticCTR = 0;
        this.Pi2Freq = Math.PI * 2 * frequency;
        for(int i = 0; i < rb.bufferSize; i++) {
            this.rb.buffer.add(0.0);
        }
    }
    
    /**
     * This constructer creates a ringBuffer and initializes it's values
     * to the values in the array. These values will be used to create a sine wave.
     *
     * This constucter will only be used for testing purposes.
     * 
     * An IllegalArgumentException is thrown if the precondions are not met. 
     * 
     * Pre-Conditions- The array contains more than 2 values. 
     *                 All values in the array have to be between -.5 and .5 
     */
    public SineString(double [] init) throws IllegalArgumentException {
        boolean isValid = true;
        for(int i = 0; i < init.length; i++) {
            if(init[i] > .5 || init[i] < -.5) {
                isValid = false; break;
            }
        }    
        if(isValid == false || init.length < 2) 
            throw new IllegalArgumentException("You have entered invalid arguments");
        ticCTR = 0;
        this.rb = new ringBuffer(init.length);
        for(int i = 0; i <init.length; i++) {
            rb.buffer.add(init[i]);
        }
    }
    
    /**
     * Fills the ringBuffer with the values needed to create a single Sine wave.
     */
    public void pluck() {
        for(int i = 0; i< this.rb.bufferSize; i++) {
            this.rb.buffer.set(i, .5 * Math.sin(i * this.Pi2Freq / StdAudio.SAMPLE_RATE));
        }  
    }
    
    /**
     * Removes the sample at the front of the list, applies the ENERGY_DECAY_FACTOR, and
     * the sample is added to the end of the list. 
     */
    public void tic() {
        this.ticCTR++;
        double newSample = rb.buffer.remove(0);
        newSample *= ENERGY_DECAY_FACTOR;
        this.rb.buffer.add(newSample);
    }
    
    /**
     * Returns the first sample in the buffer 
     */
    public double sample() {
        return this.rb.buffer.get(0);            
    }
    
    /**
     * Returns the number of times tic() has been called
     */
    public int time() {
        return this.ticCTR;
    }
}
