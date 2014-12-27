package src;

import java.util.*;
/**
 * This class creates a ring buffer that will contain a linked list with the values needed to create
 * sound a wave. 
 * 
 * List interface is implemented and it's methods are used to imitate a "Ring Buffer"
 * 
 * @author (Jesse Nelson) 
 * @version (September 1, 2013 : Windows 7(x64) Java 1.7)
 */
public class ringBuffer {
    int bufferSize;         // Size of buffer
    List<Double> buffer;    // List to hold double values
    
    /**
     * Creates a ringBuffer with the size as it's parameter
     */
    public ringBuffer(int size)
    {
        this.bufferSize = size;
        this.buffer = new LinkedList<Double>();
    }
}
