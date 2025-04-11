package hospital;

import org.junit.Test;
import static org.junit.Assert.*;


public class VectorHeapTest {
    
    @Test
    public void testAgregar() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        assertTrue(cola.estaVacia());
        
        cola.agregar(new Paciente("Juan", "Fractura", 'C'));
        assertFalse(cola.estaVacia());
        assertEquals(1, cola.tamaño());
    }
    
    @Test
    public void testQuitar() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        
        Paciente p1 = new Paciente("Maria", "Apendicitis", 'A');
        Paciente p2 = new Paciente("Juan", "Fractura", 'C');
        Paciente p3 = new Paciente("Carmen", "Parto", 'B');
        
        cola.agregar(p2);  
        cola.agregar(p1);  
        cola.agregar(p3);  
        
        assertEquals(3, cola.tamaño());
        
        // Quitar debería obtener pacientes en orden de prioridad (A, B, C)
        Paciente quitado = cola.quitar();
        assertEquals('A', quitado.getCodigoEmergencia());
        assertEquals("Maria", quitado.getNombre());
        
        quitado = cola.quitar();
        assertEquals('B', quitado.getCodigoEmergencia());
        assertEquals("Carmen", quitado.getNombre());
        
        quitado = cola.quitar();
        assertEquals('C', quitado.getCodigoEmergencia());
        assertEquals("Juan", quitado.getNombre());
        
        assertTrue(cola.estaVacia());
    }
    
    @Test
    public void testLimpiar() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        
        cola.agregar(new Paciente("Juan", "Fractura", 'C'));
        cola.agregar(new Paciente("Maria", "Apendicitis", 'A'));
        
        assertFalse(cola.estaVacia());
        assertEquals(2, cola.tamaño());
        
        cola.limpiar();
        
        assertTrue(cola.estaVacia());
        assertEquals(0, cola.tamaño());
    }
    
    @Test
    public void testQuitarVacio() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        assertNull(cola.quitar());
    }
}