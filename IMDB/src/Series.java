import java.util.Scanner;
public class Series extends Show {
    private int episodesNr;
    private int episodeDuration; //in minutes

    public Series() {
    }

    public void print(){
        super.print();
        System.out.println("Number of episodes: " + this.episodesNr);
        System.out.println("Episode duration: " + this.episodeDuration);
    }
    @Override
    public void read(Scanner in) {
        super.read(in);
        System.out.println("Number of episodes: ");
        String nr = in.nextLine();
        this.episodesNr = Integer.parseInt(nr);
        System.out.println("Duration: ");
        String dur = in.nextLine();
        this.episodeDuration = Integer.parseInt(dur);
    }

    @Override
    public String toString() {
        return "Series{" +
                super.toString() +
                "episodesNr=" + episodesNr +
                ", episodeDuration=" + episodeDuration +
                '}';
    }
}
