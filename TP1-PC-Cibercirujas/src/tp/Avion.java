package tp;

import java.util.ArrayList;
import java.util.Random;

public class Avion {
    private final ArrayList<Asiento> asientos;
    private final Random random;

    public Avion() {
        random = new Random();
        asientos = new ArrayList<>();
        for (int i = 0; i < 186; i++) {
            Asiento asiento = new Asiento(i);
            asientos.add(asiento);
        }
    }


    public void setearAsiento(Asiento asiento, String estado) {
        synchronized (this) {
            if (asiento != null) {
                asiento.setEstado(estado);
                asientos.set(asiento.getNumero(), asiento);
            }
        }
    }
    //obtiene una copia de un asiento random
    public Asiento getUnAsiento() {
        synchronized (this) {
            return asientos.get(random.nextInt(asientos.size()));
        }
    }

}