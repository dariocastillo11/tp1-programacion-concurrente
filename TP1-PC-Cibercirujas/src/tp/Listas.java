package tp;

import java.util.ArrayList;
import java.util.Random;

public class Listas {
    private final ArrayList<Asiento> pendientes;
    private final ArrayList<Asiento> confirmadas;
    private final ArrayList<Asiento> canceladas;
    private final ArrayList<Asiento> verificadas;
    private final Random random;

    public Listas() {
        pendientes = new ArrayList<>();
        confirmadas = new ArrayList<>();
        canceladas = new ArrayList<>();
        verificadas = new ArrayList<>();
        random = new Random();
    }

    /**
     * Con este método se accede al array correspondiente
     */
    public ArrayList<Asiento> getRegPendientes() {
            return pendientes;
    }

    public ArrayList<Asiento> getRegConfirmadas() {
            return confirmadas;
    }

    public ArrayList<Asiento> getRegCanceladas() {
         return canceladas;
    }

    public ArrayList<Asiento> getRegVerificadas() {
            return verificadas;
    }

    /**
     * Estos métodos agregan un asiento al array correspondiente
     */
    public void registrarReserva(Asiento asiento) {
        synchronized (this) {
            if (!pendientes.contains(asiento)) {
                pendientes.add(asiento);
                System.out.println(Thread.currentThread().getName() + " reservó el " + asiento.getNumero());
            }
        }
    }

    public void registrarPago(Asiento asiento) {
        synchronized (this) {
            if (!confirmadas.contains(asiento)) {
                confirmadas.add(asiento);
                System.out.println(Thread.currentThread().getName() + " pagó el " + asiento.getNumero());
            }
        }
    }

    public void registrarCancelacion(Asiento asiento) {
        synchronized (this) {
            if (!canceladas.contains(asiento) && !verificadas.contains(asiento)) {
                canceladas.add(asiento);
                System.out.println(Thread.currentThread().getName() + " canceló el " + asiento.getNumero());
            }
        }
    }

    public void registrarVerificacion(Asiento asiento) {
        synchronized (this) {
            if (!verificadas.contains(asiento) && !canceladas.contains(asiento)) {
                verificadas.add(asiento);
                System.out.println(Thread.currentThread().getName() + " verificó el " + asiento.getNumero());
            }
        }
    }


    /**
     * Este método elimina una asiento del array correspondiente
     */
    public void eliminarPendiente(Asiento asiento) {
            synchronized (this){pendientes.remove(asiento);}
    }

    public void eliminarConfirmada(Asiento asiento) {
            synchronized (this){confirmadas.remove(asiento);}
    }

    /**
     * Este método se utiliza para obtener una de las reservas de cualquiera
     * de las listas que necesite. Es decir de la lista que pase por parámetro
     */

    public Asiento getUnAsientoCualquiera(ArrayList<Asiento> registro) {
        synchronized (this) {
            if (!registro.isEmpty()) {
                return registro.get(random.nextInt(registro.size()));
            }
            return null;
        }
    }

    public String info() {
            int nCancelados = 0;
            for (Asiento asiento : canceladas) {
                nCancelados++;
            }
            int nVerificados = 0;
            for (Asiento asiento : verificadas) {
                nVerificados++;
            }
            return "Verificados: " + nVerificados + "\nCancelados: " + nCancelados;
    }

    public String printMatriz() {
            int totalOcupados = 0;
            for (Asiento asiento : verificadas) {
                totalOcupados++;
            }
            return " Ocupación final: " + totalOcupados + " asientos";
    }
}
