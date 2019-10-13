package codes;

public class Node { // letter precisa ser inteiro para poder criar o Nó EOF

    private int letter;
    private int count;
    private Node right; // filho direito
    private Node left; // filho esquerdo


    public Node(int letter, int count){
        this.letter = letter;
        this.count = count;
        this.right = null;
        this.left = null;
    }

    public Node(int count, Node left, Node right){
        this.count = count;
        this.left = left;
        this.right = right;
    }


    public int getLetter() { return letter; }

    public int getCount() { return count; }

    public void setLetter(int letter) { this.letter = letter; }

    public void setCount(int count) { this.count = count; }

    public Node getLeft() { return left; }

    public Node getRight() { return right; }

    public void setLeft(Node left) { this.left = left; }

    public void setRight(Node right) { this.right = right; }



    @Override
    public String toString(){
        return this.letter + "->" + this.count;
    }


}
