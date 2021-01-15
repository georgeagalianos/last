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

public class BET  {
    Scanner console = new Scanner(System.in);
    private Player player1,player2;
    private final int number_rounds,number_questions,points=0;
    private int bider,currentAnswer,roundCounter,questCounter,questionCounter,keybet,keysingl,keymulti,key1,key2,keybet1,keybet2 , bid1 , bid2;

    private String currentQ;

    private Question currentQuestion;
    private ArrayList<String> answers;

    private boolean flag1,flag2;

    private JFrame frame;

    private JLabel photo,round,question,lbet,bet1,bet2,bet3,bet4,ans1,ans2,ans3,ans4,CorrectA,row_question,control1,control2,score1,score2;

    private ImageIcon img;

    public BET(Player player1,Player player2, int numRounds, int numQuestions)throws IOException,InterruptedException{
        this.player1 = player1;
        this.player2 = player2;
        this.number_rounds = numRounds;
        this.number_questions = numQuestions;
        Call();
    }

    private void Call() throws IOException,InterruptedException {
        frame = new JFrame("BET");
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
        lbet = new JLabel();
        bet1=new JLabel();
        bet2=new JLabel();
        bet3=new JLabel();
        bet4=new JLabel();
        ans1=new JLabel();
        ans2=new JLabel();
        ans3=new JLabel();
        ans4=new JLabel();
        score1=new JLabel();
        score2=new JLabel();
        control1=new JLabel();
        control2=new JLabel();

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

    /**
     * Ολα τα γραφικα εχουν υλοποιηθει με ενα frame και αρκετα label χωρις να χρησιμοποιηεθ κανενα panel
     * @throws InterruptedException
     */

    private void Game() throws InterruptedException, IOException {
        Questions questions = new Questions();
        ArrayList<Question> roundQuestions;
        roundCounter = 1;
        /**
         * Εμφανιση των πληκτρων του καθε παιχτη αναλογως αν το παιχνιδι ειναι ατομικο (1 παιχτης) ή ομαδικο(2 παιχτες)
         */
        //#####################CONTROLER######################
        control1.setText("Controls for player1: 1 , 2 , 3 , 4");
        control1.setBounds(200, 100, 500, 30);
        control1.setForeground(Color.WHITE);
        control1.setFont(new Font("CONTROL", Font.PLAIN, 30));
        photo.add(control1);
        photo.updateUI();
        if (player2 != null){
            control2.setText("Controls for player2: 6 , 7 , 8 , 9");
            control2.setBounds(200, 200, 500, 30);
            control2.setForeground(Color.WHITE);
            control2.setFont(new Font("CONTROL", Font.PLAIN, 30));
            photo.add(control2);
        }
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(3);
        photo.updateUI();
        photo.remove(control1);
        photo.remove(control2);
        photo.updateUI();
        //####################################################
        for(int i=0 ; i<number_rounds ; i++) {
            roundQuestions = questions.getQuestions(number_questions);
            /**
             * Εμφανιση του γυρου που βρισκομαστε,ο οποιος συνεχιζει να  παραμενει πανω για καλητερη κατανοησει μεχρι
             *να τελειωσει το παιχνιδι.
             */
            frame.setVisible(true);
            //###############EMFANISEI ROUND##########################
            round.setText("Round: " + roundCounter);
            round.setBounds(360 , 20 , 200 , 30);
            round.setForeground(Color.WHITE);
            round.setFont(new Font("ROUND" , Font.PLAIN , 25));
            photo.add(round);
            //TimeUnit.SECONDS.sleep(3);
            photo.updateUI();
            roundCounter ++;
            //###############################################
            questCounter = 1;
            for(int j=0 ; j<number_questions ; j++) {

                //#####################BET#################################################
                //###############EMFANIzEI ARITHMO ERVTHSEIS##########################
                /**
                 * Εμφανιση του αριθμου της ερωτησεις η οποια παραμενει πανω μεχρι να τελειωσει το παιχνιδι ,για
                 *καλυτερη κατανοησει του παιχνιδιου
                 */
                question.setText("Question: " + questCounter);
                question.setBounds(350 , 50 , 200 , 30);
                question.setForeground(Color.WHITE);
                question.setFont(new Font("QUEST" , Font.PLAIN , 25));
                photo.add(question);
                photo.updateUI();
                //photo.remove(question);
                //photo.updateUI();
                questCounter ++;
                //###############################################
                //###################DIEYKRINISEIS################
                lbet.setText("How much do you want to bid player 1;");
                lbet.setBounds(150,100,800,50);
                lbet.setFont(new Font("" , Font.PLAIN , 25));
                lbet.setForeground(Color.WHITE);
                photo.add(lbet);
                photo.updateUI();

                bet1.setText("1=250" );
                bet1.setBounds(150 , 210 , 300 , 30);
                bet1.setForeground(Color.WHITE);
                bet1.setFont(new Font("BID" , Font.PLAIN , 25));
                photo.add(bet1);

                bet2.setText("2=500 ");
                bet2.setBounds(500 , 210 , 300 , 30);
                bet2.setForeground(Color.WHITE);
                bet2.setFont(new Font("BID" , Font.PLAIN , 25));
                photo.add(bet2);

                bet3.setText("3=750 ");
                bet3.setBounds(150 , 260 , 300 , 30);
                bet3.setForeground(Color.WHITE);
                bet3.setFont(new Font("BID" , Font.PLAIN , 25));
                photo.add(bet3);

                bet4.setText("4=1000 ");
                bet4.setBounds(500 , 260 , 300 , 30);
                bet4.setForeground(Color.WHITE);
                bet4.setFont(new Font("BID" , Font.PLAIN , 25));
                photo.add(bet4);
                photo.updateUI();
//                photo.remove(bet1);
//                photo.remove(bet2);
//                photo.remove(bet3);
//                photo.remove(bet4);
                //               photo.remove(lbet);
                //               photo.updateUI();
                //####################################################
                //##################BET###############################
                //while (flag1==false){
                //do{
                /**
                 * Με Keylistener οριζεται απο τον χρηστη το ποσο  που θελει να πονταρει.
                 * Με την δομη επαναληψης WHILE  ελεχνω αν εχει πληκτρολογιθει καποιο ποσο ωστε να συνεχισει.
                 * Η while περιμενει μεχρι να πληκρολογηθει το ποσο
                 */
                keybet1=0;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    public void keyPressed(KeyEvent e) {
                        int k = 0;
                        k = e.getKeyCode();
                        if(k - 48 <= 4) {
                            keybet1 = k;
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
                while (keybet1 == 0 ) {
                    TimeUnit.SECONDS.sleep(1);
                }
                photo.remove(bet1);
                photo.remove(bet2);
                photo.remove(bet3);
                photo.remove(bet4);
                photo.remove(lbet);
                photo.updateUI();
                if (keybet1==49) {
                    player1.setBid(250);
                }
                else if (keybet1==50) {
                    player1.setBid(500);
                }
                else if (keybet1==51) {
                    player1.setBid(750);
                }
                else
                if(keybet1==52) {
                    player1.setBid(1000);
                }
                //}//while (key2!=0);
                //############################################
                //####################PLAYER2########################################
                /**
                 * Με μια εντολη if ελενχω την υπαρξει δευτερου παιχτη.
                 * Με Keylistener οριζεται απο τον χρηστη το ποσο  που θελει να πονταρει.
                 * Με την δομη επαναληψης WHILE  ελεχνω αν εχει πληκτρολογιθει καποιο ποσο ωστε να συνεχισει.
                 * Η while περιμενει μεχρι να πληκρολογηθει το ποσο
                 */
                if (player2!=null){
                    lbet.setText("How much do you want to bid player2;");
                    lbet.setBounds(150,100,800,50);
                    lbet.setFont(new Font("" , Font.PLAIN , 25));
                    lbet.setForeground(Color.WHITE);
                    photo.add(lbet);
                    photo.updateUI();

                    bet1.setText("6=250");
                    bet1.setBounds(150 , 210 , 300 , 30);
                    bet1.setForeground(Color.WHITE);
                    bet1.setFont(new Font("BID" , Font.PLAIN , 25));
                    photo.add(bet1);

                    bet2.setText("7=500 ");
                    bet2.setBounds(500 , 210 , 300 , 30);
                    bet2.setForeground(Color.WHITE);
                    bet2.setFont(new Font("BID" , Font.PLAIN , 25));
                    photo.add(bet2);

                    bet3.setText("8=750 ");
                    bet3.setBounds(150 , 260 , 300 , 30);
                    bet3.setForeground(Color.WHITE);
                    bet3.setFont(new Font("BID" , Font.PLAIN , 25));
                    photo.add(bet3);

                    bet4.setText("9=1000 ");
                    bet4.setBounds(500 , 260 , 300 , 30);
                    bet4.setForeground(Color.WHITE);
                    bet4.setFont(new Font("BID" , Font.PLAIN , 25));
                    photo.add(bet4);
                    photo.updateUI();
                    //while (flag1==false){
                    //do{
                    keybet2=0;
                    frame.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {}
                        public void keyPressed(KeyEvent e) {
                            int k = 0;
                            k = e.getKeyCode();
                            if(k-48 >= 6) {
                                keybet2 = k;
                            }
                        }
                        @Override
                        public void keyReleased(KeyEvent e) {}
                    });
                    while (keybet2 == 0 ) {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    photo.remove(bet1);
                    photo.remove(bet2);
                    photo.remove(bet3);
                    photo.remove(bet4);
                    photo.remove(lbet);
                    photo.updateUI();
                    if (keybet2==54) {
                        player2.setBid(250);
                    }
                    else if (keybet2==55) {
                        player2.setBid(500);
                    }
                    else if (keybet2==56) {
                        player2.setBid(750);
                    }
                    else
                    if(keybet2==57) {
                        player2.setBid(1000);
                    }
                }
                //###################################################################
                //######################################################################

                //###################ERVTHSHS KAI APANTHSEIS####################
                currentQuestion = roundQuestions.get(j);
                String currentQ = currentQuestion.printQuestion();
                answers = currentQuestion.printAnswers();
                /**
                 * Εμφανιση των ερωτησεων και των πιθανων απαντησεων που εχουν επιλεχθει ωστε να επιλεχθει η σωστη
                 */

                if(questions.takeCategory() == "Εικόνα") {
                    img = new ImageIcon(ImageIO.read(new File(questions.takePath())));
                    photo.setIcon(img);
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

                ans2.setText("2: " + answers.get(1));
                ans2.setBounds(500 , 210 , 300 , 30);
                ans2.setForeground(Color.WHITE);
                ans2.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans2);

                ans3.setText("3: " + answers.get(2));
                ans3.setBounds(150 , 260 , 300 , 30);
                ans3.setForeground(Color.WHITE);
                ans3.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans3);

                ans4.setText("4: " + answers.get(3));
                ans4.setBounds(500 , 260 , 300 , 30);
                ans4.setForeground(Color.WHITE);
                ans4.setFont(new Font("ANS" , Font.PLAIN , 20));
                photo.add(ans4);
                photo.updateUI();
//                photo.remove(ans1);
//                photo.remove(ans2);
//                photo.remove(ans3);
//                photo.remove(ans4);
//                photo.remove(row_question);
//                photo.updateUI();
                /**
                 * Με εναν KEYLISTENER περιμενω την απαντηση του παιχτη
                 * Με την δομη επαναληψης WHILE  ελεχνω αν εχει πληκτρολογιθει καποιο ποσο ωστε να συνεχισει.
                 * Η while περιμενει μεχρι να πληκρολογηθει το ποσο
                 * Επειτα με μια εντολη if ελενχεται αν η απαντηση.
                 * Αν ειναι σωστη  προστιθονται οι ποντοι που ειχαν επιλεχθει παραπανω.Αν η απαντηση ειναι λαθος οι
                 * ποντοι αφαιρουνται
                 */
                keysingl=0;
                key1=0;
                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int k = 0;
                        k = e.getKeyCode();
                        if(k-48 < 4) {
                            key1 = k;
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
                while (keysingl == 0 ) {
                    TimeUnit.SECONDS.sleep(1);
                }

                key1=keysingl-48;
                System.out.println(key2);
                if(key1 == currentQuestion.getCorrectAnswer()) {
                    player1.addPoints(player1.getBid());
                }
                else if(key1 != currentQuestion.getCorrectAnswer()) {
                    player1.addPoints(-player1.getBid());
                }
                //#################################PLAYER2###########################################
                /**
                 * Με μια εντολη if ελενχω την υπαρξει δευτερου παιχτη.
                 * Με εναν KEYLISTENER περιμενω την απαντηση του παιχτη
                 * Με την δομη επαναληψης WHILE  ελεχνω αν εχει πληκτρολογιθει καποιο ποσο ωστε να συνεχισει.
                 * Η while περιμενει μεχρι να πληκρολογηθει το ποσο
                 * Επειτα με μια εντολη if ελενχεται αν η απαντηση.
                 * Αν ειναι σωστη  προστιθονται οι ποντοι που ειχαν επιλεχθει παραπανω.Αν η απαντηση ειναι λαθος η
                 * ποντοι αφαιρουνται
                 */
                if (player2!=null){
//                    ans1.setText("6 :" + answers.get(0));
//                    ans1.setBounds(150 , 210 , 300 , 30);
//                    ans1.setForeground(Color.WHITE);
//                    ans1.setFont(new Font("ANS" , Font.PLAIN , 20));
//                    photo.add(ans1);
//
//                    ans2.setText("7 : " + answers.get(1));
//                    ans2.setBounds(500 , 210 , 300 , 30);
//                    ans2.setForeground(Color.WHITE);
//                    ans2.setFont(new Font("ANS" , Font.PLAIN , 20));
//                    photo.add(ans2);
//
//                    ans3.setText("8 : " + answers.get(2));
//                    ans3.setBounds(150 , 260 , 300 , 30);
//                    ans3.setForeground(Color.WHITE);
//                    ans3.setFont(new Font("ANS" , Font.PLAIN , 20));
//                    photo.add(ans3);
//
//                    ans4.setText("9 : " + answers.get(3));
//                    ans4.setBounds(500 , 260 , 300 , 30);
//                    ans4.setForeground(Color.WHITE);
//                    ans4.setFont(new Font("ANS" , Font.PLAIN , 20));
//                    photo.add(ans4);
//                    photo.updateUI();
////                photo.remove(ans1);
////                photo.remove(ans2);
////                photo.remove(ans3);
////                photo.remove(ans4);
////                photo.remove(row_question);
////                photo.updateUI();
                    keymulti=0;
                    key2=0;
                    frame.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {}
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int k = 0;
                            k = e.getKeyCode();
                            if(k-48 >= 6) {
                                key2 = k;
                            }
                        }
                        @Override
                        public void keyReleased(KeyEvent e) {}
                    });
                    while (key2 == 0 ) {
                        TimeUnit.SECONDS.sleep(1);
                    }
//                    photo.remove(ans1);
//                    photo.remove(ans2);
//                    photo.remove(ans3);
//                    photo.remove(ans4);
//                    photo.updateUI();
                    key2=key2-53;
                    System.out.println(key2);
                    if(key2 == currentQuestion.getCorrectAnswer()) {
                        player2.addPoints(player2.getBid());
                    }

                    // if(currentAnswer == currentQuestion.getCorrectAnswer()) {
                    //    player2.addPoints(player2.getBid());
                    //}
                    else if(key2 != currentQuestion.getCorrectAnswer()) {
                        player2.addPoints(-player2.getBid());
                    }
                }
                photo.remove(row_question);
                //###################################################################################
                //##################################################################################
                photo.remove(question);
                photo.remove(ans1);
                photo.remove(ans2);
                photo.remove(ans3);
                photo.remove(ans4);
                //photo.remove(row_question);
                photo.updateUI();
                photo.updateUI();

            }
            photo.removeAll();
            photo.updateUI();

        }
        /**
         * Με μια εντολη if ελενχω την υπαρξει δευτερου παιχτη και εμφανιζωνατ τα καταλληλα score
         */
        score1.setText(player1.getName()+" scor:" + player1.getPoints());
        score1.setBounds(350 , 200 , 200 , 30);
        score1.setForeground(Color.black);
        score1.setFont(new Font("SCORE" , Font.PLAIN , 20));
        photo.add(score1);
        photo.updateUI();
        TimeUnit.SECONDS.sleep(5);
        photo.remove(score1);
        photo.updateUI();
        if (player2!=null){
            score2.setText(player2.getName()+" scor:" + player2.getPoints());
            score2.setBounds(350 , 200 , 200 , 30);
            score2.setForeground(Color.black);
            score2.setFont(new Font("SCORE" , Font.PLAIN , 20));
            photo.add(score2);
            photo.updateUI();
            TimeUnit.SECONDS.sleep(5);
            photo.remove(score2);
            photo.updateUI();
        }
        frame.dispose();
    }
}