
// Aufgabe 16
public class RingBuffer {
    private Object[] buffer;
    private int lastEmpty, lastFull = 0;
    
    public RingBuffer(int n) {
        this.buffer = new Object[n];
    }
    
    public Object pop() {
        Object ret = peek();
        buffer[lastFull] = null;
        return ret;
    }
    
    public Object peek() {
        return buffer[lastFull];
    }
    
    public void push(Object s) {
        if (lastEmpty == lastFull) {
            throw new IllegalStateException();
        }
        lastFull++;
        buffer[lastFull] = s;
    }
        
    public int size() {
        return buffer.length;
    }
    
    public int countElements() {
        return Math.abs(lastEmpty - lastFull);
    }
}
