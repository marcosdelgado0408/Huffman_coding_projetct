package codes;

import java.util.Arrays;

public class HeapMix { // min heap -> misturada com uma ABB

    private Node[] nodes;
    private int size;
    private int capacity;


    /*
    * Obs: tive que mudar os "<" e ">" de alguns casos do heapifydown e
    * heapifyip para a prioridade ser do menor para o maior
    */

    public Node getRoot(){ return this.nodes[0]; } // quando a heap virar uma ABB -> o unico elemento da heap será o no raiz

    public HeapMix(int capacity) {
        nodes = new Node[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public Node[] getNodes() { return nodes; }

    public HeapMix() { this(10000);}

    public void addNode(int letter, int count) {
        addNode(new Node(letter, count));
    }

    public void addNode(Node node) {
        this.ensureCapacity();
        this.nodes[getSize()] = node;
        heapifyUp(getSize());
        size++;
    }

    private void heapifyUp(int index) {
        if (!hasParent(index)) {
            return;
        }
        int parentIndex = getParentIndex(index);

        Node node = nodes[index];
        Node pai  = nodes[parentIndex];

        if (node.getCount() < pai.getCount()) {
            nodes[index] = pai;
            nodes[parentIndex] = node;
            heapifyUp(parentIndex);
        }
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0 && getParentIndex(index) < size;
    }

    private int getParentIndex(int index) {
        return (int) Math.floor((index - 1) / 2);
    }

    private void ensureCapacity() {
        if (getSize() == capacity) {
            this.nodes = Arrays.copyOf(this.nodes, getSize() * 2);
            capacity   = getSize() * 2;
        }
    }

    public int getSize() {
        return size;
    }

    public Node peek() {
        if (getSize() == 0)  {
            return null;
        }

        return nodes[0];
    }

    public void remove() {
        nodes[0] = nodes[getSize() - 1];
        nodes[getSize() - 1] = null;
        size--;
        heapifyDown(0);
    }

    private void heapifyDown(int index) {
        int leftChild  = index * 2 + 1;
        int rightChild = index * 2 + 2;

        int childIndex = -1;

        if (leftChild < getSize()) {
            childIndex = leftChild;
        }

        if (childIndex < 0) {
            return;
        }

        if (rightChild < getSize()) {
            if (nodes[rightChild].getCount() < nodes[leftChild].getCount()) {
                childIndex = rightChild;
            }
        }

        if (nodes[index].getCount() > nodes[childIndex].getCount()) {
            Node tmp          = nodes[index];
            nodes[index]      = nodes[childIndex];
            nodes[childIndex] = tmp;
            heapifyDown(childIndex);
        }
    }





}
