package hospital;

/**
 * Interfaz que define las operaciones básicas de una cola de prioridad
 * @param <E> el tipo de elementos en esta cola, debe ser Comparable
 */
public interface PriorityQueue<E extends Comparable<E>> {
    void add(E value);
    E remove();
    boolean isEmpty();
    int size();
    void clear();
    E getFirst();
}