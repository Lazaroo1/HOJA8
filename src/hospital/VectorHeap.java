package hospital;

import java.util.Vector;

/**
 * Implementación de una cola de prioridad usando un Heap basado en Vector
 * @param <E> tipo de elementos en el heap, debe de ser Comparable
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    protected Vector<E> data;


    public VectorHeap() {
        data = new Vector<>();
    }

    /**
     * Constructor que crea un heap a partir de un vector existente
     * @param v vector con elementos iniciales
     */
    public VectorHeap(Vector<E> v) {
        data = new Vector<>(v.size());
        for (E item : v) {
            data.add(item);
        }
        heapify();
    }

    /**
     * Retorna el índice del padre de un nodo
     * @param i índice del nodo hijo
     * @return índice del nodo padre
     */
    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Retorna el índice del hijo izquierdo
     * @param i índice del nodo padre
     * @return índice del hijo izquierdo
     */
    protected static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Retorna el índice del hijo derecho
     * @param i índice del nodo padre
     * @return índice del hijo derecho
     */
    protected static int right(int i) {
        return 2 * (i + 1);
    }

    /**
     * Mueve un nodo hacia arriba en el heap para mantener la propiedad de heap
     * @param leaf índice del nodo a mover hacia arriba
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 && (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * Mueve un nodo hacia abajo en el heap para mantener la propiedad de heap
     * @param root índice del nodo raíz a mover hacia abajo
     */
    protected void pushDown(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        
        while (root < heapSize) {
            int childPos = left(root);
            if (childPos < heapSize) {
                if (right(root) < heapSize && 
                    data.get(childPos + 1).compareTo(data.get(childPos)) < 0) {
                    childPos++;
                }
                if (data.get(childPos).compareTo(value) < 0) {
                    data.set(root, data.get(childPos));
                    root = childPos;
                } else {
                    data.set(root, value);
                    return;
                }
            } else {
                data.set(root, value);
                return;
            }
        }
    }

    /**
     * Convierte el vector en un heap válido
     */
    protected void heapify() {
        for (int i = data.size() / 2 - 1; i >= 0; i--) {
            pushDown(i);
        }
    }

    /**
     * Remueve y retorna el elemento con mayor prioridad
     * @return elemento con mayor prioridad, o null si está vacío
     */
    @Override
    public E remove() {
        if (data.isEmpty()) return null;
        E minVal = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (!data.isEmpty()) pushDown(0);
        return minVal;
    }

    @Override
    public boolean isEmpty() { return data.isEmpty(); }

    @Override
    public int size() { return data.size(); }

    @Override
    public void clear() { data.clear(); }

    @Override
    public E getFirst() { return data.isEmpty() ? null : data.get(0); }
}