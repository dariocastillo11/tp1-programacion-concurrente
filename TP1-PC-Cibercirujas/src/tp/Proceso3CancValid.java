package tp;

import java.util.Random;
import java.util.ArrayList;
public class Proceso3CancValid implements Runnable {
    private Avion avion;
    private Listas listas;
    private Random nroAleatorio;
    public Proceso3CancValid(Avion avion, Listas listas) {
        this.avion = avion;
        this.listas = listas;
        this.nroAleatorio = new Random();
    }

    @Override
    public void run() {
        while (Main.stopThread == false) {
            if (!listas.getRegConfirmadas().isEmpty()) {
                Asiento asiento = listas.getUnAsientoCualquiera(listas.getRegConfirmadas());
                if (asiento != null) {
                    if (cancelarReserva() == true) {
                        listas.eliminarConfirmada(asiento);
                        //asiento.setEstado("descartado");
                        listas.registrarCancelacion(asiento);
                        avion.setearAsiento(asiento, "descartado");//setea el estado del asiento en el array del avión
                    } else {
                        avion.setearAsiento(asiento, "checked");
                    }

                }
            }
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Un hilo del proceso 3 finalizó su ejecución");
    }

    /**
     * Método que cancela la reserva con una probabilidad del 10% de éxito
     */
    private Boolean cancelarReserva(){
        return nroAleatorio.nextDouble() < 0.1;
    }
}