package hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaEmergencias {
    private final VectorHeap<Paciente> colaPacientes;

    public SistemaEmergencias() {
        colaPacientes = new VectorHeap<>();
    }

    /**
     * Carga pacientes desde un archivo de texto
     * @param archivo ruta del archivo con datos de pacientes
     * @throws IOException si hay error leyendo el archivo
     */
    public void cargarPacientes(String archivo) throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(", ");
                if (datos.length == 3) {
                    Paciente paciente = new Paciente(
                        datos[0].trim(), 
                        datos[1].trim(), 
                        datos[2].trim().charAt(0)
                    );
                    pacientes.add(paciente);
                }
            }
        }
        
        for (Paciente p : pacientes) {
            colaPacientes.add(p);
        }
    }

    public Paciente siguientePaciente() {
        return colaPacientes.remove();
    }

    public void mostrarPacientes() {
        System.out.println("Pacientes en orden de atención:");
        VectorHeap<Paciente> tempHeap = new VectorHeap<>();
        
        while (!colaPacientes.isEmpty()) {
            Paciente p = colaPacientes.remove();
            System.out.println(p);
            tempHeap.add(p);
        }
        
        while (!tempHeap.isEmpty()) {
            colaPacientes.add(tempHeap.remove());
        }
    }

    public static void main(String[] args) {
        SistemaEmergencias sistema = new SistemaEmergencias();
        try {
            sistema.cargarPacientes("pacientes.txt");
            sistema.mostrarPacientes();
            
            System.out.println("\nAtendiendo pacientes:");
            Paciente p;
            while ((p = sistema.siguientePaciente()) != null) {
                System.out.println("Atendiendo a: " + p);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}