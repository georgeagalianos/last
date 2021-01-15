import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Player pl1 = new Player();
        Player pl2 = new Player();

        String nm1;
        String nm2;
        int rRounds;
        int qQuests;

        a a = new a();
        String[] stats = new String[5];
        stats = a.a2();

        while(a.flag == false) {
            TimeUnit.SECONDS.sleep(1);
        }

        pl1.setName(stats[0]);
        pl2.setName(stats[1]);
        rRounds = Integer.parseInt(stats[2]);
        qQuests = Integer.parseInt(stats[3]);

        Random r = new Random();
        int random = 0;
        random = r.nextInt(4);

        switch (random) {
            case 0:
                new CorrectAnswer(pl1 , pl2 , rRounds , qQuests);
                break;
            case 1:
                new BET(pl1 , pl2 , rRounds , qQuests);
                break;
            case 2:
                new Quick_Reply(pl1 , pl2 , rRounds , qQuests);
                break;
            case 3:
                new Thermometer(pl1 , pl2 , rRounds , qQuests);
                break;
            case 4:
                new Stopwatch(pl1 , pl2 , rRounds , qQuests);
                break;
        }







    }
}
