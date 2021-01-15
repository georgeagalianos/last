import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Scanner read = new Scanner(System.in);
    private String name;
    private int points;
    private int bid;
    private int wins;


    public Player() {
        this.name = name;
        this.points = 0;
        this.wins = 0;
        //BuzzDok p = new BuzzDok();
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public int getPoints() { return this.points; }

    public int addPoints(int point) {
        return this.points += point;
    }

    public void setWins(int wins) { this.wins = wins; }

    public int getWins() { return this.wins; }

    public void setBid(int bid) { this.bid = bid; }

    public int getBid() { return this.bid; }
}