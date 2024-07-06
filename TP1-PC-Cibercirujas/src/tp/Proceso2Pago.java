package tp;

import java.util.Random;
public class Proceso2Pago implements Runnable{
    private Avion avion;
    private Listas listas;
    private Random nroAleatorio;

    public Proceso2Pago(Avion avion, Listas listas){
        this.avion = avion;
        this.listas = listas;
        this.nroAleatorio = new Random();
    }

    @Override
    public void run() {
        while(Main.stopThread == false){
            Asiento asiento = listas.getUnAsientoCualquiera(listas.getRegPendientes()); //se accede a la lista de pendientes
            if(asiento != null) {
                if (verificarPago()) { //prueba si la probabilidad está dentro del 90%. Si pasa verificar pago devuelve true
                    listas.eliminarPendiente(asiento); //saca el asiento de registroPendientes
                    listas.registrarPago(asiento); //lo suma al array de registroConfirmadas

                } else {
                    avion.setearAsiento(asiento, "descartado");//setea el estado del asiento como DESCARTADO
                    listas.eliminarPendiente(asiento); //saca el asiento de registroPendientes
                    listas.registrarCancelacion(asiento); //lo suma al array de registroCanceladas
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Un hilo del proceso 2 finalizó su ejecución");
    }
    /**
     * simula la verificacion de pago con una probabilidad del 90%
     */
    private boolean verificarPago() {
        // Simula la verificación de pago con una probabilidad del 90% de aprobación
        return nroAleatorio.nextDouble() < 0.9;
    }
}