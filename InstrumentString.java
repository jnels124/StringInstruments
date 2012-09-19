/**
 * A string simulation used by a musical instrument.
 * @version August 2012
 */
public interface InstrumentString {
    /** Fill ring buffer with initial values. */
    void pluck();

    /** Advance string vibration simulation one step. */
    void tic();

    /**
     * Access the current sample.
     * @return the current sample value
     */
    double sample();

    /** 
     * Access the time state of the simulation.
     * @return the number of times tic() has been called
     */
    int time();
}
