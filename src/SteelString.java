package src;

import java.util.*;
/**
 * This class implements the InstrumentString interface to create a SteelString to be used in 
 * an instrument.
 * 
 * @author (Jesse Nelson) 
 * @version (September 1, 2012 : Windows 7(X64) Java 1.7) 
 */
public class SteelString implements InstrumentString {
    final static double ENERGY_DECAY_FACTOR = 0.996;        // Decay factor for a steel string
    private int ticCTR;                                     // Counter to track # of times tic() is called
    private ringBuffer rb;                                 

    /**
     * Creates a ringBuffer object, it's parameter being the size of the ringBuffer being created.
     * All values in the buffer are initialized to 0 to imitate a SteelString at rest.
     * 
     * An IllegalArgumentException is thrown if the pre-conditons are not met.
     * 
     * Pre-Conditions- The frequency is a positive integer.
     *                 The bufferSize is > 2 
     */
    public SteelString(double frequency) throws IllegalArgumentException {
       this.rb = new ringBuffer(Math.round((float)(StdAudio.SAMPLE_RATE/frequency)));
       if(frequency < 0 || this.rb.bufferSize < 2) 
          throw new IllegalArgumentException("Enter a higher frequency");
       this.ticCTR = 0;
       for(int i = 0; i < this.rb.bufferSize; i++) 
            this.rb.buffer.add(0.0);
    }
    
    /**
     * This constructer creates a ringBuffer and initializes it's values
     * to the values in the array. These values will be used to create a SteelString.
     *
     * This constucter will only be used for testing purposes.
     * 
     * An IllegalArgumentException is thrown if the precondions are not met. 
     * 
     * Pre-Conditions- The array contains more than 2 values. 
     *                 All values in the array have to be between -.5 and .5 
     */
    public SteelString(double [] init) {
        if(init.length < 2) 
          throw new IllegalArgumentException("You have not provided enough values");
        this.ticCTR = 0;
        this.rb = new ringBuffer(init.length);
        for(int i = 0; i < init.length; i++) {
            this.rb.buffer.add(i, init[i]);            
        }
    }
    
    /**
     * Fills the ringBuffer with random values between -.5 and .5.
     */
    public void pluck() {
        for(int i = 0; i < this.rb.bufferSize; i++) {
            double randValue = Math.random() - .5;
            this.rb.buffer.set(i, randValue);
        }
    }
    
    /**
     * Removes the sample at the front of the ringBuffer, applies the Karplus-String Algorithim,
     * and adds the sample to the end of the list.
     */
    public void tic() {
        this.ticCTR++;
        double newSample = this.rb.buffer.remove(0);
        newSample = ((newSample + rb.buffer.get(0))/2) * ENERGY_DECAY_FACTOR;
        this.rb.buffer.add(newSample);
    }
    
    /**
     * Returns the first sample in the buffer
     */
    public double sample() {
        return this.rb.buffer.get(0);
    }
    
    /**
     * Returns the number of times tic has been called
     */
    public int time() {
        return this.ticCTR;
    }
}
