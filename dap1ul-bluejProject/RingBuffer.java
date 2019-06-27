// Aufgabe 16

public class RingBuffer<O> {
    public static void test() {
        RingBuffer<Integer> rb = new RingBuffer<>(new Integer[4]);
        System.out.println(rb.toString());
        rb.push(4);
        System.out.println(rb.toString());
        rb.push(5);
        System.out.println(rb.toString());
        rb.pop();
        System.out.println(rb.toString());
        rb.push(6);
        System.out.println(rb.toString());
        rb.push(7);
        System.out.println(rb.toString());
        rb.push(8);
        System.out.println(rb.toString());
        rb.push(9);
        System.out.println(rb.toString());
        rb.push(10);
        System.out.println(rb.toString());
        rb.pop();
        System.out.println(rb.toString());
        rb.pop();
        System.out.println(rb.toString());
        rb.pop();
        System.out.println(rb.toString());
        rb.pop();
        System.out.println(rb.toString());
        rb.push(11);
        System.out.println(rb.toString());
        rb.push(12);
        System.out.println(rb.toString());
        rb.push(13);
        System.out.println(rb.toString());
    }

    private O[] buffer;
    private int index = 0; // naechster zu belegender Index
    private int oldest = -1; // Index mit dem aeltesten Wert, -1 == es gibt keinen ältesten Wert
    
    public RingBuffer(O[] buffer) {
        this.buffer = buffer;
    }
    
    private int increment(int index) {
        int i = index + 1;
        if (i >= size()) {
            i = 0;
        }
        return i;
    }
    
    private int decrement(int index) {
        int i = index - 1;
        if (i < 0) {
            i = size() - 1;
        }
        return i;
    }
    
    public O pop() {
        if (oldest == -1) {
            throw new IllegalStateException();
        }
        
        O ret = peek();
        buffer[oldest] = null;
        
        oldest = increment(oldest);
        if (oldest == index) {
            oldest = -1;
        }
        
        return ret;
    }
    
    public O peek() {
        if (oldest == -1) {
            throw new IllegalStateException();
        }
        return buffer[oldest];
    }
    
    public void push(O s) {
        if (oldest == -1) {
            oldest = index;
        } else if (oldest == index) {
            oldest = increment(oldest);
        }
        buffer[index] = s;
        index = increment(index);
    }
    
    public int size() {
        return buffer.length;
    }
    
    public int countElements() {
        if (oldest == -1) {
            return 0;
        } else if (oldest == index) {
            return size();
        }
        int i = oldest;
        int count = 0;
        while (i != index) {
            count++;
            i = increment(i);
        }
        return count;
    }
    
    // Aufgabe 19
    public boolean isEmpty() {
        return countElements() == 0;
    }
    
    @Override
    public String toString() {
        String s = "filled: "+countElements()+" / "+size()+"\n"+"Content: ";
        for (O o : buffer) {
            s += o + ", ";
        }
        s += "\n";
        s += "oldest: "+oldest+", index: "+index;
        s += "\n";
        return s;
    }
}
