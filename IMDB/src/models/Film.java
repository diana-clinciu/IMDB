package models;

import java.util.Scanner;

public class Film extends Show {
    private int duration;

    public Film() {
    }

    @Override
    public void read(Scanner in) {
        super.read(in);
        System.out.println("Duration: ");
        String dur = in.nextLine();
        this.duration = Integer.parseInt(dur);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Duration:" + this.duration);
    }

    @Override
    public String toString() {
        return "Film{" +
                super.toString() +
                "duration=" + duration +
                '}';
    }

    public int getDuration() {
        return duration;
    }
}
