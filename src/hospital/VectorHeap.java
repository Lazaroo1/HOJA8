package hospital;

/**
 * Implementación de ColaPrioridad usando un heap binario
 * @param <E> Tipo de elementos almacenados en el heap
 */
class VectorHeap<E extends Comparable<E>> implements ColaPrioridad<E> {
    private java.util.Vector<E> datos;
    
 
    public VectorHeap() {
        datos = new java.util.Vector<E>();
        // Añadimos elemento nulo en la posición 0 para simplificar la indexación
        datos.add(null);
    }
    
    /**
     * Obtiene el índice del padre de un nodo
     * @param i Índice del nodo actual
     * @return Índice del padre
     */
    protected int padre(int i) {
        return i / 2;
    }
    
    /**
     * Obtiene el índice del hijo izquierdo de un nodo
     * @param i Índice del nodo actual
     * @return Índice del hijo izquierdo
     */
    protected int izquierdo(int i) {
        return 2 * i;
    }
    
    /**
     * Obtiene el índice del hijo derecho de un nodo
     * @param i Índice del nodo actual
     * @return Índice del hijo derecho
     */
    protected int derecho(int i) {
        return (2 * i) + 1;
    }
    
    /**
     * Verifica si un nodo tiene hijo izquierdo
     * @param i Índice del nodo actual
     * @return true si tiene hijo izquierdo
     */
    protected boolean tieneIzquierdo(int i) {
        return izquierdo(i) < datos.size();
    }
    
    /**
     * Verifica si un nodo tiene hijo derecho
     * @param i Índice del nodo actual
     * @return true si tiene hijo derecho
     */
    protected boolean tieneDerecho(int i) {
        return derecho(i) < datos.size();
    }
    
    /**
     * Hace pasar un elemento hacia arriba hasta su posición correcta en el heap
     * @param hoja Índice del elemento a mover hacia arriba
     */
    protected void fluirHaciaArriba(int hoja) {
        int padre = padre(hoja);
        E valor = datos.get(hoja);
        
        // Mientras no estemos en la raíz y el valor del padre > valor
        while (hoja > 1 && datos.get(padre).compareTo(valor) > 0) {
            datos.set(hoja, datos.get(padre));  // Mover padre hacia abajo
            hoja = padre;                       // Subir
            padre = padre(hoja);
        }
        
        datos.set(hoja, valor);  
    }
    
    /**
     * Hace pasar un elemento hacia abajo hasta su posición correcta en el heap
     * @param raiz Índice del elemento a mover hacia abajo
     */
    protected void empujarRaizHaciaAbajo(int raiz) {
        int tamañoHeap = datos.size() - 1;
        if (raiz < 1 || raiz > tamañoHeap) return;
        
        E valor = datos.get(raiz);
        int posicion = raiz;         // Posición actual siendo empujada hacia abajo
        int hijo = izquierdo(posicion); // Intentar con hijo izquierdo primero
        
        while (hijo <= tamañoHeap) {
            // Elegimos el hijo menor
            if (hijo < tamañoHeap && 
                datos.get(hijo).compareTo(datos.get(hijo+1)) > 0) {
                hijo++;  // Hijo derecho es menor
            }
            
            if (valor.compareTo(datos.get(hijo)) <= 0) break;
            
            // Mover el hijo menor hacia arriba
            datos.set(posicion, datos.get(hijo));
            posicion = hijo;           // Mover hacia abajo
            hijo = izquierdo(posicion); // Mover al hijo izquierdo
        }
        
        datos.set(posicion, valor);  // Almacenar valor en la posición correcta
    }
    
    /**
     * Añade un elemento a la cola con prioridad
     * @param elemento Elemento a añadir
     */
    @Override
    public void agregar(E elemento) {
        datos.add(elemento);             // Añadir al final
        fluirHaciaArriba(datos.size()-1); // pasa hacia arriba
    }
    
    /**
     * Quita el elemento con mayor prioridad
     * @return Elemento con mayor prioridad
     */
    @Override
    public E quitar() {
        if (estaVacia()) return null;
        
        E valMin = datos.get(1);                // El valor mínimo está en la raíz
        datos.set(1, datos.get(datos.size()-1)); // Reemplazar con el último elemento
        datos.setSize(datos.size()-1);           
        
        if (datos.size() > 1) {
            empujarRaizHaciaAbajo(1);  
        }
        
        return valMin;
    }
    

    @Override
    public boolean estaVacia() {
        return datos.size() <= 1;  // Solo el elemento nulo en la posición 0 que definimos arriba
    }
    
 
    @Override
    public int tamaño() {
        return datos.size() - 1;  // No contar el elemento nulo en la posición 0 que definimos arriba
    }
    

    @Override
    public void limpiar() {
        datos.clear();
        datos.add(null);  // Añadir elemento nulo en la posición 0
    }
}