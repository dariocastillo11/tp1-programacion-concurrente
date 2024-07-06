package tp;

public class Asiento {

    private String estado = "disponible";
    private final Integer numero;

    /**
     * Contructor de la clase Asiento.
     *
     * @param numero El numero de asiento que se asigna
     */
    public Asiento(Integer numero) {
        this.numero = numero;
    }

    /**
     * Devuelve el numero de asiento.
     *
     * @return numero de asiento.
     */
    public synchronized int getNumero() {
        return numero;
    }

    /**
     * Devuelve el estado del asient
     */
    public synchronized String getEstado() {
        return estado;
    }

    /**
     * Cambia el estado del asiento a ocupado
     */
    public synchronized void setEstado(String estado) {
        if (!this.estado.equals("descartado")) {
            this.estado = estado;
        }
        if (!this.estado.equals("checked")) {
            this.estado = estado;
        }
    }
}