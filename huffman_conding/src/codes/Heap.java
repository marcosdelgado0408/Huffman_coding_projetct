package codes;

import java.util.Arrays;

public class Heap {

    private Frequencia[] nodes;
    private int size;//quantos elementos tem
    private int capacity; //quantos elementos pode ter


    /*
    * Obs: tive que mudar os "<" e ">" de alguns casos do heapifydown e
    * heapifyip para a prioridade ser do menor para o maior
    */



    public Heap(int capacity) {
        nodes = new Frequencia[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public Frequencia[] getNodes() { return nodes; }

    public Heap() { this(10);}

    public void addNode(char letter, int count) {
        addNode(new Frequencia(letter, count));
    }

    public void addNode(Frequencia node) {
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

        Frequencia node = nodes[index];
        Frequencia pai  = nodes[parentIndex];

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

    public Frequencia peek() {
        if (getSize() == 0)  {
            return null;
        }

        return nodes[0];
    }

    public void remove() {
        nodes[0] = nodes[getSize() - 1];
        nodes[getSize() - 1] = null;
        size--;
        //manutencaoPrioriade(0);
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
            Frequencia tmp          = nodes[index];
            nodes[index]      = nodes[childIndex];
            nodes[childIndex] = tmp;
            heapifyDown(childIndex);
        }
    }




}
