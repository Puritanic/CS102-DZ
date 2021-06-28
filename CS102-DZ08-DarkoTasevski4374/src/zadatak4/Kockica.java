package zadatak4;

import java.util.Random;

public class Kockica extends Thread {
    private int roll;
    private String ime;

    public Kockica() {
    }

    public Kockica(String ime) {
        this.ime = ime;
    }

    public void run() {
        Random random = new Random();
        this.roll = random.nextInt(6) + 1;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
