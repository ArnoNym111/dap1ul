public class Sudoku {
    private int[][] values;
    private boolean[][] writable;
    
    public Sudoku(int[][] values) {
        this.values = values;
    }
    
    public void setValue(int i, int j, int value) {
        values[i][j] = value;
    }
    
    public int getValue(int i, int j) {
        return values[i][j];
    }
    
    public boolean isWritable(int i, int j) {
        return writable[i][j];
    }
}
