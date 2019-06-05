public class CharacterSearchTree
{ 
    private HuffmanTriple content;
    private CharacterSearchTree leftChild, rightChild;

    public CharacterSearchTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    public HuffmanTriple getContent()
    {
        if ( !isEmpty() )
        {
            return content;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isEmpty() 
    {
        return content == null;
    }

    public boolean isLeaf() 
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    public void add( char t )
    {
        if ( isEmpty() ) 
        {
            content = new HuffmanTriple( t );
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
        }
        else
        {
            if ( content.getToken() > t )
            {
                leftChild.add( t );
            }
            else if ( content.getToken() < t )
            {
                rightChild.add( t );
            }
            else
            {
                content.incrementQuantity();
            }
        }
    }

    public void iterativeAdd( char t )
    {
        CharacterSearchTree current = this;
        while ( !current.isEmpty() && current.content.getToken() != t )
        {
            if ( current.content.getToken() > t )
            {
                current = current.leftChild;
            }
            else
            {
                current = current.rightChild;
            }
        }
        if ( current.isEmpty() ) 
        {
            current.content = new HuffmanTriple( t );
            current.leftChild = new CharacterSearchTree();
            current.rightChild = new CharacterSearchTree();
        }
        else
        {
            current.content.incrementQuantity();
        }
    }

    public String getCode( char t )
    {
        if ( !isEmpty() ) 
        {
            if ( content.getToken() > t )
            {
                return leftChild.getCode( t );
            }
            else if ( content.getToken() < t )
            {
                return rightChild.getCode( t );
            }
            else
            {
                return content.getCode();
            }
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    public int size() 
    {
        if ( isEmpty() ) 
        {
            return 0;
        }
        else
        {
            return 1 + leftChild.size() + rightChild.size();
        }       
    }

    public void show()
    {
        if ( !isEmpty() ) 
        {
            leftChild.show();
            System.out.println( content.toString() );
            rightChild.show();
        }
    }

    public HuffmanTriple[] toArray()
    {
        if ( !isEmpty() ) 
        {
            HuffmanTriple[] collector = new HuffmanTriple[size()];
            toArray( collector, 0 );
            return collector;
        }
        return new HuffmanTriple[0];
    }

    private int toArray( HuffmanTriple[] collector, int index ) 
    {
        if ( !isEmpty() )
        {
            index = leftChild.toArray( collector, index );
            collector[index] = content;
            index = rightChild.toArray( collector, index + 1 );
        }
        return index;
    }  
    
    // Uebung Aufgabe 17
    public int depth() {
        int lc = 0;
        if (leftChild != null) {
            lc = leftChild.depth();
        }
        int rc = 0;
        if (rightChild != null) {
            rc = rightChild.depth();
        }
        return 1 + Math.max(lc, rc);
    }
    
    // Uebung Aufgabe 18
    public int[] levelWidth() {
        if (isLeaf()) {
            return new int[]{1};
        }
        int[] ret = new int[(int) Math.pow(2, depth() - 1)]; 
        levelWidth(ret, 0);
        return ret;
    }
    private void levelWidth(int[] width, int level) {
        width[level]++;
        if (leftChild != null) {
            leftChild.levelWidth(width, ++level);
        }   
        if (rightChild != null) {
            rightChild.levelWidth(width, ++level);
        }
    }
    public int maxLevelWidth() {
        int[] width = levelWidth();
        int max = width[0];
        for (int v : width) {
            max = Math.max(max, v);
        }
        return max;
    }
}