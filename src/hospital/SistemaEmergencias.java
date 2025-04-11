package hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class SistemaEmergencias {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        System.out.println("Sistema de atencion de emergencias");
        System.out.println("1. Usar VectorHeap");
        System.out.println("2. Usar PriorityQueue de jcf");
        System.out.print("Seleccione una opcion: ");
        
        try {
            opcion = Integer.parseInt(scanner.nextLine());
            if (opcion == 1) {
                usarVectorHeap();
            } else if (opcion == 2) {
                usarJCFPriorityQueue();
            } else {
                System.out.println("Opcion no valida");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no valida");
        } finally {
            scanner.close();
        }
    }
 
    public static void usarVectorHeap() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        cargarPacientes(cola);
        atenderPacientes(cola);
    }
    
 
    public static void usarJCFPriorityQueue() {
        java.util.PriorityQueue<Paciente> cola = new java.util.PriorityQueue<>();
        cargarPacientesJCF(cola);
        atenderPacientesJCF(cola);
    }
    
    /**
     * Carga pacientes desde archivo a VectorHeap
     * @param cola Cola de prioridad donde cargar los pacientes
     */
    public static void cargarPacientes(VectorHeap<Paciente> cola) {
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 3);
                if (datos.length == 3) {
                    String nombre = datos[0].trim();
                    String sintoma = datos[1].trim();
                    char codigo = datos[2].trim().charAt(0);
                    cola.agregar(new Paciente(nombre, sintoma, codigo));
                }
            }
            System.out.println("Pacientes cargados correctamente");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Carga pacientes desde archivo a JCF PriorityQueue
     * @param cola Cola de prioridad donde cargar los pacientes
     */
    public static void cargarPacientesJCF(java.util.PriorityQueue<Paciente> cola) {
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", 3);
                if (datos.length == 3) {
                    String nombre = datos[0].trim();
                    String sintoma = datos[1].trim();
                    char codigo = datos[2].trim().charAt(0);
                    cola.offer(new Paciente(nombre, sintoma, codigo));
                }
            }
            System.out.println("Pacientes cargados correctamente");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Procesa pacientes en VectorHeap
     * @param cola VectorHeap con pacientes
     */
    public static void atenderPacientes(VectorHeap<Paciente> cola) {
        System.out.println("\nAtendiendo pacientes por orden de prioridad:");
        
        while (!cola.estaVacia()) {
            Paciente paciente = cola.quitar();
            System.out.println("Atendiendo a: " + paciente);
        }
        
        System.out.println("No hay mas pacientes en espera");
    }
    
    /**
     * Procesa pacientes en JCF PriorityQueue
     * @param cola PriorityQueue con pacientes
     */
    public static void atenderPacientesJCF(java.util.PriorityQueue<Paciente> cola) {
        System.out.println("\nAtendiendo pacientes por orden de prioridad:");
        
        while (!cola.isEmpty()) {
            Paciente paciente = cola.poll();
            System.out.println("Atendiendo a: " + paciente);
        }
        
        System.out.println("No hay mas pacientes en espera");
    }
}