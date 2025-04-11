package hospital;

/**
 * @param <E> Tipo de elementos almacenados en la cola
 */
interface ColaPrioridad<E extends Comparable<E>> {

    void agregar(E elemento);
    
    /**
     * Quita el elemento con mayor prioridad
     * @return Elemento con mayor prioridad
     */
    E quitar();
    
    boolean estaVacia();
 
    int tamaño();
    
    void limpiar();
}