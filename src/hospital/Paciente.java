package hospital;

/**
 * Clase que representa un paciente en el sistema de emergencias
 * Implementamos Comparable para definir el orden de prioridad
 */
public class Paciente implements Comparable<Paciente> {
    private final String nombre;
    private final String sintoma;
    private final char codigoEmergencia;

    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = Character.toUpperCase(codigoEmergencia);
    }

    /**
     * Compara este paciente con otro basado en el código de emergencia
     * @param otro el otro paciente a comparar
     * @return valor negativo si este paciente tiene mayor prioridad, 
     *         positivo si tiene menor, 0 si igual
     */
    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.codigoEmergencia, otro.codigoEmergencia);
    }

    public String getNombre() { return nombre; }
    public String getSintoma() { return sintoma; }
    public char getCodigoEmergencia() { return codigoEmergencia; }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}