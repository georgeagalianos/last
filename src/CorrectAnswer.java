import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class CorrectAnswer {
    Player pl1;
    Player pl2;
    private final int number_rounds;      //from player
    private final int number_questRound;  //from player

    private boolean flag1;
    private boolean flag2;
    private int key1;
    private int key2;

    private Question currentQuestion;

    private ArrayList<String> answers;

    private JFrame frame;

    private JLabel photo;
    private BufferedImage img;
    private ImageIcon icon;

    private JLabel row_question;
    private JLabel CorrectA;
    private JLabel ans1;
    private JLabel ans2;
    private JLabel ans3;
    private JLabel ans4;

    private JLabel round;
    private JLabel question;
    private JLabel points;

    public CorrectAnswer(Player player1 , Player player2 , int number_rounds, int number_questRound) throws IOException, InterruptedException {
        this.pl1 = player1;
        this.pl2 = player2;
        this.number_rounds = number_rounds;
        this.number_questRound = number_questRound;
        Call();
        GameMulti();
    }
    public CorrectAnswer(Player player1 , int number_rounds, int number_questRound) throws IOException, InterruptedException {
        this.pl1 = player1;
        this.number_rounds = number_rounds;
        this.number_questRound = number_questRound;
        Call();
        GameSignle();
    }

    private void Call() throws IOException, InterruptedException {
        icon = new ImageIcon(ImageIO.read(new File("images/bg2.jpg")));
        photo = new JLabel(icon);
        photo.setSize(800 , 500);


        frame = new JFrame("Game mode: Correct Answer");
        frame.add(photo);
        frame.setSize(800 , 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);



    }

    private void GameSignle() throws InterruptedException, IOException {
        answers = new ArrayList<>();

        round = new JLabel();
        question = new JLabel();
        points = new JLabel();
        row_question = new JLabel();
        CorrectA = new JLabel();

        ans1 = new JLabel();
        ans2 = new JLabel();
        ans3 = new JLabel();
        ans4 = new JLabel();


        frame.setVisible(true);
        Questions questions = new Questions();
        ArrayList<Question> roundQuestions;
//        Question currentQuestion;
//        currentQuestion = new Question();

        //from program
        int roundCounter = 1;
        for(int i=0 ; i<number_rounds ; i++) {
            roundQuestions = questions.getQuestions(number_questRound);
            round.setText("Round: " + roundCounter);
            round.setBounds(360 , 20 , 200 , 30);
            round.setForeground(Color.WHITE);
            round.setFont(new Font("ROUND" , Font.PLAIN , 25));
            photo.add(round);
            photo.updateUI();
            TimeUnit.SECONDS.sleep(1);
            //photo.remove(round);
            photo.updateUI();
            roundCounter++;

            //from program
            int questCounter = 1;
            for(int j=0 ; j<number_questRound ; j++) {
                flag1 = false;
                key1 = 0;

                question.setText("Question: " + questCounter);
                question.setBounds(350 , 50 , 200 , 30);
                question.setForeground(Color.WHITE);
                question.setFont(new Font("QUEST" , Font.PLAIN , 25));
                photo.add(question);
                photo.updateUI();
                //TimeUnit.SECONDS.sleep(3);
                //photo.remove(question);
                photo.updateUI();
                questCounter++;

                //points edw

                //######################

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
                photo.updateUI();


//                Question finalCurrentQuestion = currentQuestion;
//                Question finalCurrentQuestion1 = currentQuestion;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int k = 0;
                        k = e.getKeyCode();
                        if(k - 48 <= 4) {
                            key1 = k - 48;
//                            if(k - 48 == currentQuestion.getCorrectAnswer()) {
//                                flag1 = true;
//                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                while (key1 == 0) {
                    TimeUnit.SECONDS.sleep(1);
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


                TimeUnit.SECONDS.sleep(2);
                photo.removeAll();

            }
        }
        frame.setVisible(true);
        frame.dispose();
    }

    private void GameMulti() throws InterruptedException {
        //##########################
        answers = new ArrayList<>();

        round = new JLabel();
        question = new JLabel();
        points = new JLabel();
        row_question = new JLabel();
        CorrectA = new JLabel();

        ans1 = new JLabel();
        ans2 = new JLabel();
        ans3 = new JLabel();
        ans4 = new JLabel();


        frame.setVisible(true);
        Questions questions = new Questions();
        ArrayList<Question> roundQuestions;
//        Question currentQuestion;
//        currentQuestion = new Question();

        //from program
        int roundCounter = 1;
        System.out.println("round");
        for(int i=0 ; i<number_rounds ; i++) {
            roundQuestions = questions.getQuestions(number_questRound);
            round.setText("Round: " + roundCounter);
            round.setBounds(360 , 20 , 200 , 30);
            round.setForeground(Color.WHITE);
            round.setFont(new Font("ROUND" , Font.PLAIN , 25));
            photo.add(round);
            photo.updateUI();
            TimeUnit.SECONDS.sleep(1);
            //photo.remove(round);
            photo.updateUI();
            roundCounter++;

            //from program
            int questCounter = 1;
            for(int j=0 ; j<number_questRound ; j++) {
                flag1 = false;
                flag2 = false;
                key1 = 0;
                key2 = 0;

                question.setText("Question: " + questCounter);
                question.setBounds(350 , 50 , 200 , 30);
                question.setForeground(Color.WHITE);
                question.setFont(new Font("QUEST" , Font.PLAIN , 25));
                photo.add(question);
                photo.updateUI();
                //TimeUnit.SECONDS.sleep(3);
                //photo.remove(question);
                photo.updateUI();
                questCounter++;

                //points edw

                //######################

                currentQuestion = roundQuestions.get(j);
                String currentQ = currentQuestion.printQuestion();     //pairnw erwthsh
                answers = currentQuestion.printAnswers();       //pairnw apanthseis

                row_question.setText(currentQuestion.printQuestion());
                row_question.setBounds(390 - currentQ.length()*6 , 100 , 800 , 30);
                row_question.setForeground(Color.WHITE);
                row_question.setFont(new Font("QUEST" , Font.PLAIN , 25));
                photo.add(row_question);


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
                photo.updateUI();


//                Question finalCurrentQuestion = currentQuestion;
//                Question finalCurrentQuestion1 = currentQuestion;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int k = 0;
                        k = e.getKeyCode();
                        if(k - 48 <= 4) {
                            key1 = k - 48;
                            if(k - 48 == currentQuestion.getCorrectAnswer()) {
                                flag1 = true;
                            }
                        }
                        if(k-48 >= 6) {
                            key2 = k - 53;
                            if(k - 53 == currentQuestion.getCorrectAnswer()) {
                                flag2 = true;
                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                while (key1 == 0 || key2 == 0) {
                    TimeUnit.SECONDS.sleep(1);
                }

                if(key1 == currentQuestion.getCorrectAnswer()) {
                    pl1.addPoints(1000);
                }
                else if(key1 != currentQuestion.getCorrectAnswer()) {
                    pl1.addPoints(-1000);
                }

                if(key2 == currentQuestion.getCorrectAnswer()) {
                    pl2.addPoints(1000);
                }
                else if(key2 != currentQuestion.getCorrectAnswer()) {
                    pl2.addPoints(-1000);
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
        }
//        System.out.println(pl1.getPoints());
//        System.out.println(pl2.getPoints());
        frame.setVisible(true);
    }
}