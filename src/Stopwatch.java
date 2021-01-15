import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Stopwatch {
    public Player pl1;
    public Player pl2;
    private final int number_rounds;      //from player
    private final int number_questRound;  //from player

    private int key1;
    private int key2;
    private int countDown;

    private Question currentQuestion;
    private ArrayList<String> answers;

    private JFrame frame;

    private JLabel ans1;
    private JLabel ans2;
    private JLabel ans3;
    private JLabel ans4;
    private JLabel row_question;
    private JLabel CorrectA;

    private JLabel time;
    private JLabel round;
    private JLabel question;

    private BufferedImage img;
    private JLabel photo;
    private ImageIcon icon;

//    private int currentAnswer;
//    private int roundCounter;
//    private int questionCounter;


    public Stopwatch(Player p1 , Player p2 , int numRounds , int questRounds) throws IOException, InterruptedException {
        this.pl1 = p1;
        this.pl2 = p2;
        this.number_rounds = numRounds;
        this.number_questRound = questRounds;
        Game();
    }


    private void Game () throws IOException, InterruptedException {
        frame = new JFrame("Game mode: Stopwatch");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800 , 500);
        frame.setLayout(null);

        icon = new ImageIcon(ImageIO.read(new File("images/bg2.jpg")));
        photo = new JLabel(icon);
        photo.setSize(800 , 500);
        frame.add(photo);


        if(pl2.getName() == "null") {
            GameSingle();
        }
        else {
            GameMulti();
        }

    }

    private void GameSingle() throws InterruptedException, IOException {
        answers = new ArrayList<>();

        round = new JLabel();
        question = new JLabel();
        row_question = new JLabel();
        CorrectA = new JLabel();

        ans1 = new JLabel();
        ans2 = new JLabel();
        ans3 = new JLabel();
        ans4 = new JLabel();

        time = new JLabel();
        time.setBounds(370 , 55 , 100 , 20);
        time.setForeground(Color.WHITE);
        time.setFont(new Font("timer" , Font.PLAIN , 20));
        Timer timer;


        frame.setVisible(true);
        Questions questions = new Questions();
        ArrayList<Question> roundQuestions;

        int roundCounter = 1;

        for(int i=0 ; i<number_rounds ; i++) {
            roundQuestions = questions.getQuestions(number_questRound);
            round.setText("Round: " + roundCounter);
            round.setBounds(360 , 5 , 200 , 30);
            round.setForeground(Color.WHITE);
            round.setFont(new Font("ROUND" , Font.PLAIN , 25));
            photo.add(round);
            photo.updateUI();
            TimeUnit.SECONDS.sleep(3);
            //photo.remove(round);
            photo.updateUI();
            roundCounter++;

            int questCounter = 1;
            for(int j=0 ; j<number_questRound ; j++) {
                key1 = 0;

                question.setText("Question: " + questCounter);
                question.setBounds(350 , 30 , 200 , 25);
                question.setForeground(Color.WHITE);
                question.setFont(new Font("QUEST" , Font.PLAIN , 25));
                photo.add(question);
                photo.updateUI();
                //TimeUnit.SECONDS.sleep(3);
                //photo.remove(question);
                photo.updateUI();
                questCounter++;

                currentQuestion = roundQuestions.get(j);
                String currentQ = currentQuestion.printQuestion();     //pairnw erwthsh
                answers = currentQuestion.printAnswers();       //pairnw apanthseis

                if(questions.takeCategory() == "Εικόνα") {
                    icon = new ImageIcon(ImageIO.read(new File(questions.takePath())));
                    photo.setIcon(icon);
                }
                else {
                    row_question.setText(currentQuestion.printQuestion());
                    row_question.setBounds(390 - currentQ.length()*6 , 100 , 800 , 30);
                    row_question.setForeground(Color.WHITE);
                    row_question.setFont(new Font("QUEST" , Font.PLAIN , 25));
                    photo.add(row_question);
                }

                ans1.setText("1: " + answers.get(0));
                ans1.setBounds(150 , 210 , 300 , 30);
                ans1.setForeground(Color.WHITE);
                ans1.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans1);
                //photo.updateUI();

                ans2.setText("2: " + answers.get(1));
                ans2.setBounds(500 , 210 , 300 , 30);
                ans2.setForeground(Color.WHITE);
                ans2.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans2);
                //photo.updateUI();

                ans3.setText("3: " + answers.get(2));
                ans3.setBounds(150 , 260 , 300 , 30);
                ans3.setForeground(Color.WHITE);
                ans3.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans3);
                //photo.updateUI();

                ans4.setText("4: " + answers.get(3));
                ans4.setBounds(500 , 260 , 300 , 30);
                ans4.setForeground(Color.WHITE);
                ans4.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans4);
                //photo.updateUI();

                countDown = 10;
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        countDown --;

                        if (countDown >= 0) {
                            time.setText("Time: " + countDown + "sec");
                        }
                        else {
                            ((Timer) (e.getSource())).stop();
                        }

                    }
                });

                photo.add(time);
                timer.setInitialDelay(0);
                timer.start();
                photo.updateUI();


//                Question finalCurrentQuestion = currentQuestion;
//                Question finalCurrentQuestion1 = currentQuestion;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int k= e.getKeyCode();
                        if(k - 48 <= 4) {
                            key1 = k - 48;
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
                while (countDown >= 0) {
                    TimeUnit.SECONDS.sleep(1);
                    if(key1 != 0) {
                        break;
                    }
                }

                if(key1 == currentQuestion.getCorrectAnswer()) {
                    pl1.addPoints(1000);
                }
                else if(key1 != currentQuestion.getCorrectAnswer()) {
                    pl1.addPoints(-1000);
                }

                photo.removeAll();
                photo.updateUI();

                CorrectA.setBounds(350 , 50 , 250 , 30);
                CorrectA.setFont(new Font("CORRECT" , Font.PLAIN , 25));
                CorrectA.setForeground(Color.WHITE);
                int a = currentQuestion.getCorrectAnswer();
                System.out.println(a);
                CorrectA.setText("" + answers.get(a-1));
                photo.add(CorrectA);
                photo.updateUI();


                TimeUnit.SECONDS.sleep(5);
                photo.removeAll();
            }

            System.out.println(pl1.getPoints());
            frame.setVisible(true);
        }
        frame.dispose();
    }

    private void GameMulti() {

    }
}