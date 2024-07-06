package tp;

import java.util.ArrayList;
import java.lang.Thread;
import java.util.Random;

public class Proceso4Verif implements Runnable{
    private Random random;
    private Listas listas;
    private Avion avion;

    public static int contador = 0;

    public Proceso4Verif(Avion avion, Listas listas){
        this.listas= listas;
        this.avion = avion;
        random = new Random();
    }

    @Override
    public void run(){
        while(Main.stopThread == false){
            Asiento asiento = listas.getUnAsientoCualquiera(listas.getRegConfirmadas());
                if (asiento != null) {
                    if (asiento.getEstado().equals("checked")) {
                        listas.eliminarConfirmada(asiento);
                        listas.registrarVerificacion(asiento);
                    }
                }
                contador =listas.getRegPendientes().size() + listas.getRegVerificadas().size();
                if (listas.getRegCanceladas().size() + listas.getRegVerificadas().size() == 186){
                    Main.stopThread = true;
                }
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Un hilo del proceso 4 finalizó su ejecución");
    }
}