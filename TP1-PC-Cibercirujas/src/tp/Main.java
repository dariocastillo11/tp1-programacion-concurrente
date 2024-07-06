package tp;

public class Main {
    public static volatile boolean stopThread = false;
    public static void main(String[] args) {

        System.out.printf("Inicio\n");
        Avion avion = new Avion();
        Listas lista = new Listas();
        long startTime = System.currentTimeMillis();
        int tiempoTotal = 26;   //seg
        int tiempoPrint = 200;   //ms
        LOG loggerThread = new LOG(lista, avion, startTime, tiempoTotal, tiempoPrint);


        loggerThread.start();

        for (int i = 1; i <=3; i++){
            Proceso1Reserva proceso1Reserva = new Proceso1Reserva(avion,lista);
            Thread thread = new Thread (proceso1Reserva);
            System.out.println("Thread proceso1Reserva= " + i);
            thread.start();
        }

        for (int i = 1; i <=2; i++){
            Proceso2Pago proceso2Pago = new Proceso2Pago(avion,lista);
            Thread thread = new Thread (proceso2Pago);
            System.out.println("Thread proceso2Pago= " + i);
            thread.start();
        }

        for (int i = 1; i <=3; i++){
            Proceso3CancValid proceso3CancValid = new Proceso3CancValid(avion,lista);
            Thread thread = new Thread (proceso3CancValid);
            System.out.println("Thread proceso3CancValid= " + i);
            thread.start();
        }

        for (int i = 1; i <=2; i++) {
            Proceso4Verif proceso4Verif = new Proceso4Verif(avion, lista);
            Thread thread = new Thread(proceso4Verif);
            System.out.println("Thread proceso4Verif= " + i);
            thread.start();
        }
    }
}