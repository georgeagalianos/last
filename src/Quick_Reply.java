import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.util.concurrent.TimeUnit;

public class Quick_Reply{
    Scanner console = new Scanner(System.in);
    private Player player;
    private final int number_rounds,number_questions,points=0;
    private Player player1,player2;
    private int bider,currentAnswer,roundCounter,questCounter,questionCounter,keyfirst,keysecond,pointpl1, pointpl2,k1,k2;

    private String currentQ;

    private Question currentQuestion;
    private ArrayList<String> answers;


    private JFrame frame;

    private JLabel photo,round,question,ans1,ans2,ans3,ans4,CorrectA,row_question,control1,control2,score1,score2,label1,label2;

    private ImageIcon img;

    public Quick_Reply (Player player1,Player player2,int numRounds, int numQuestions)throws IOException,InterruptedException{
        this.player1 = player1;
        this.player2 = player2;
        this.number_rounds = numRounds;
        this.number_questions = numQuestions;
//        single();
//        multi();
        Call();
    }
    /**
     * Ολα τα γραφικα εχουν υλοποιηθει με ενα frame και αρκετα label χωρις να χρησιμοποιηεθ κανενα panel
     * @throws InterruptedException
     */
    private void Call() throws IOException,InterruptedException {
        frame = new JFrame("Quick Reply");
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 500);

        img = new ImageIcon(ImageIO.read(new File("images/bg2.jpg")));
        photo = new JLabel(img);
        photo.setSize(800 , 500);
        frame.add(photo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        round = new JLabel();
        question = new JLabel();
        ans1=new JLabel();
        ans2=new JLabel();
        ans3=new JLabel();
        ans4=new JLabel();
        score1=new JLabel();
        score2=new JLabel();
        control1=new JLabel();
        control2=new JLabel();
        label1=new JLabel();
        label2=new JLabel();

        CorrectA=new JLabel();

        frame.setVisible(false);
        answers = new ArrayList<>();
        row_question = new JLabel();
        Questions questions = new Questions();
        ArrayList<Question> roundQuestions;

        Game();
        //Gamesingl();
        //Gamemulti();
    }

    private void Game()throws IOException,InterruptedException {
        Questions questions = new Questions();
        ArrayList<Question> roundQuestions;
        roundCounter = 1;
        /**
         * Εμφανιση των πληκτρων του καθε παιχτη
         */
        //#####################CONTROLER######################
        frame.setVisible(true);
        label1.setText("Quick Reply");
        label1.setBounds(350, 190, 200, 30);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("CONTROL", Font.PLAIN, 25));
        photo.add(label1);
        photo.updateUI();
        TimeUnit.SECONDS.sleep(2);
        photo.remove(label1);
        photo.updateUI();

        control1.setText("Controls for player1: 1 , 2 , 3 , 4");
        control1.setBounds(200, 100, 500, 30);
        control1.setForeground(Color.WHITE);
        control1.setFont(new Font("CONTROL", Font.PLAIN, 30));
        photo.add(control1);
        photo.updateUI();
        control2.setText("Controls for player2: 6 , 7 , 8 , 9");
        control2.setBounds(200, 200, 500, 30);
        control2.setForeground(Color.WHITE);
        control2.setFont(new Font("CONTROL", Font.PLAIN, 30));
        photo.add(control2);

        TimeUnit.SECONDS.sleep(3);
        photo.updateUI();
        photo.remove(control1);
        photo.remove(control2);
        photo.updateUI();
        //####################################################

        pointpl1 = 0;
        pointpl2 = 0;
        for (int i = 0; i < number_rounds; i++) {
            roundQuestions = questions.getQuestions(number_questions);
            frame.setVisible(true);
            /**
             * Εμφανιση του γυρου που βρισκομαστε,ο οποιος συνεχιζει να  παραμενει πανω για καλητερη κατανοησει μεχρι
             *να τελειωσει το παιχνιδι.
             */
            //###############EMFANISEI ROUND##########################
            round.setText("Round: " + roundCounter);
            round.setBounds(360, 20, 200, 30);
            round.setForeground(Color.WHITE);
            round.setFont(new Font("ROUND", Font.PLAIN, 25));
            photo.add(round);
            photo.updateUI();
            TimeUnit.SECONDS.sleep(3);
            roundCounter++;
            //###############################################
            questCounter = 1;
            for (int j = 0; j < number_questions; j++) {
                /**
                 * Εμφανιση του αριθμου της ερωτησεις η οποια παραμενει πανω μεχρι να τελειωσει το παιχνιδι ,για
                 *καλυτερη κατανοησει του παιχνιδιου
                 */
                //###############EMFANIzEI ARITHMO ERVTHSEIS##########################
                question.setText("Question: " + questCounter);
                question.setBounds(350, 50, 200, 30);
                question.setForeground(Color.WHITE);
                question.setFont(new Font("QUEST", Font.PLAIN, 25));
                photo.add(question);
                photo.updateUI();
                TimeUnit.SECONDS.sleep(2);
                //photo.remove(question);
                //photo.updateUI();
                questCounter++;
                //###############################################
                currentQuestion = roundQuestions.get(j);
                String currentQ = currentQuestion.printQuestion();
                answers = currentQuestion.printAnswers();

                /**
                 * Εμφανισει την ερωτησεις και των απαντησεων που εχουν επιλεχθει για καυε ερωωτηση
                 */
                if(questions.takeCategory() == "Εικόνα") {
                    img = new ImageIcon(ImageIO.read(new File(questions.takePath())));
                    photo.setIcon(img);
                }
                else {
                    row_question.setText(currentQuestion.printQuestion());
                    row_question.setBounds(390 - currentQ.length() * 6, 100, 800, 30);
                    row_question.setForeground(Color.WHITE);
                    row_question.setFont(new Font("QUEST", Font.PLAIN, 25));
                    photo.add(row_question);
                }

                ans1.setText("" + answers.get(0));
                ans1.setBounds(150, 210, 300, 30);
                ans1.setForeground(Color.WHITE);
                ans1.setFont(new Font("ANS", Font.PLAIN, 20));
                photo.add(ans1);

                ans2.setText("" + answers.get(1));
                ans2.setBounds(500, 210, 300, 30);
                ans2.setForeground(Color.WHITE);
                ans2.setFont(new Font("ANS", Font.PLAIN, 20));
                photo.add(ans2);

                ans3.setText("" + answers.get(2));
                ans3.setBounds(150, 260, 300, 30);
                ans3.setForeground(Color.WHITE);
                ans3.setFont(new Font("ANS", Font.PLAIN, 20));
                photo.add(ans3);

                ans4.setText("" + answers.get(3));
                ans4.setBounds(500, 260, 300, 30);
                ans4.setForeground(Color.WHITE);
                ans4.setFont(new Font("ANS", Font.PLAIN, 20));
                photo.add(ans4);
                photo.updateUI();
                /**
                 * Με δυο KEYLISTENER παρνω τις απαντησεις απο του παικτες ελεχωντας με while ωστε να εχουν πληκτρολογηθει
                 */
                keyfirst = 0;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int k = 0;
                        k = e.getKeyCode();
                        if(k-48 < 4) {
                            keyfirst = k;
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
                while (keyfirst == 0) {
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println(keyfirst);
                k1=keyfirst-48;
                keysecond = 0;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int k = 0;
                        k = e.getKeyCode();
                        if(k-48 >= 6) {
                            keysecond = k;
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
                while (keysecond == 0) {
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println(keysecond);
                k2=keysecond-53;
                photo.remove(ans1);
                photo.remove(ans2);
                photo.remove(ans3);
                photo.remove(ans4);
                photo.remove(row_question);
                //################################KEYFIRST################################
                /**
                 *Η εντολη ελενχου IF χρησιμοποιειται για να:
                 *Οι εξωτερικες εντολες IF ελενχουν σε ποιον παιχτη ανηκει η πιο γρηγορη απαντηση
                 *Η εμφωλευμενη IF ελενχει αν η απαντηση που δοθηκε απο τον χρηστη αν ειναι η σωστη ωστε να
                 * προστεθουν οι καταλληλοι ποντοι
                 */
                if (k1==1 || k1==2 || k1==3 || k1==4){
                    if (k1== currentQuestion.getCorrectAnswer()) {
                        player1.addPoints(1000);
                    }
                }
                if (k1==6 || k1==7 || k1==8 || k1==9) {
                    if (k1-5 == currentQuestion.getCorrectAnswer()) {
                        player2.addPoints(1000);
                    }
                }
                //#######################################################################
                //##############################KEYSECOND#################################
                /**
                 * Η εντολη ελενχου IF χρησιμοποιειται για να:
                 * Οι εξωτερικες εντολες IF ελενχουν απο ποιον παιχτη δοθηκε η δευτερη απαντησει
                 *Η εμφωλευμενη IF ελενχει αν η απαντηση που δοθηκε απο τον χρηστη αν ειναι η σωστη ωστε να
                 * προστεθουν οι καταλληλοι ποντοι
                 */
                if (k2==1|| k2==2 || k2==3 || k2==4) {
                    if (k2==currentQuestion.getCorrectAnswer()) {
                        player1.addPoints(500);
                    }
                }
                if (k2==6 || k2==7 || k2==8 || k2==9) {
                    if (k2-5 == currentQuestion.getCorrectAnswer()) {
                        player2.addPoints(500);
                    }
                }
                //################################################################################
            }
            photo.remove(question);
            photo.updateUI();
        }
        photo.removeAll();
        photo.updateUI();
        /**
         * Εμφανιση των ποντων των παιχτων
         */
        label1.setText(player1.getName() + " scor:" + player1.getPoints());
        label1.setBounds(350, 200, 200, 30);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("SCORE", Font.PLAIN, 20));
        photo.add(label1);
        photo.updateUI();
        TimeUnit.SECONDS.sleep(5);
        photo.remove(label1);
        photo.updateUI();

        label2.setText(player2.getName() + " scor:" + player2.getPoints());
        label2.setBounds(350, 200, 200, 30);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("SCORE", Font.PLAIN, 20));
        photo.add(label2);
        photo.updateUI();
        TimeUnit.SECONDS.sleep(5);
        photo.remove(label2);
        photo.updateUI();
    }
}