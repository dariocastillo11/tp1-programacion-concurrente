package tp;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class LOG extends Thread {
    private Listas listas; //RegistroDeReserva registro;
    private Avion avion; //matrizAsiento
    long startTime;
    private int tiempoTotal;
    private int tiempoPrint;

    private static final Logger logger = Logger.getLogger(LOG.class.getName());
    private static final String LOG_FILE_NAME = "informacion.log";
    private FileHandler fileHandler;
    private SimpleFormatter formatter;

    public LOG(Listas listas, Avion avion, long startTime, int tiempoTotal, int tiempoPrint) {
        try {
            this.listas = listas;
            this.avion = avion;
            this.startTime = startTime;
            this.tiempoTotal = tiempoTotal;
            this.tiempoPrint= tiempoPrint;

            this.fileHandler = new FileHandler(LOG_FILE_NAME);
            this.formatter = new SimpleFormatter();
            this.fileHandler.setFormatter(this.formatter);
            logger.addHandler(this.fileHandler);
        } catch (IOException e) {
            logger.severe("Error initializing logger: " + e.getMessage());
        }
    }
    @Override
    public void run() {

        //imprimo en el Log cada 200 ms (tiempoPrint) hasta llegar al tiempoTotal
        for(int i=0; i<tiempoTotal*1000/tiempoPrint;i++){
            logger.info("Información actual: " +"\n"+ listas.info());
            try {
                Thread.sleep(tiempoPrint);
            } catch (InterruptedException e){
            }
        }

        //imprimir matriz de ocupacion
        logger.info("\n"+listas.printMatriz()+"\n");

        //imprimo una vez mas la informacion de los registros de canceladas y verificadas
        logger.info("Información final: " +"\n"+ listas.info());

        //imprimir el tiempo que demoro el programa
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.info("\n"+"El programa demoro: " + totalTime + " ms");
    }
}