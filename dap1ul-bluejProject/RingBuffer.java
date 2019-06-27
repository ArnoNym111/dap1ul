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
        rb.push(11);
        System.out.println(rb.toString());
        rb.push(12);
        System.out.println(rb.toString());
        rb.push(13);
        System.out.println(rb.toString());
    }

    private O[] buffer;
    private int index; // naechster zu blegender
    
    public RingBuffer(O[] buffer) {
        this.buffer = buffer;
    }
    
    private int incrementIndex() {
        int i = index + 1;
        if (i >= size()) {
            i = 0;
        }
        return i;
    }
    
    private int decrementIndex() {
        int i = index - 1;
        if (i < 0) {
            i = size() - 1;
        }
        return i;
    }
    
    public O pop() {
        O ret = peek();
        index = decrementIndex();
        buffer[index] = null;
        return ret;
    }
    
    public O peek() {
        return buffer[decrementIndex()];
    }
    
    public void push(O s) {
        buffer[index] = s;
        index = incrementIndex();
    }
        
    public int size() {
        return buffer.length;
    }
    
    public int countElements() {
        int count = 0;
        for (O o : buffer) {
            if (o != null) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public String toString() {
        String s = "filled: "+countElements()+" / "+size()+"\n"+"Content: ";
        for (O o : buffer) {
            s += o + ", ";
        }
        s += "\n";
        s += "index: "+index;
        s += "\n";
        return s;
    }
}
