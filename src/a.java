import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class a {
    public String[] all = new String[5];

    private JFrame f;
    private JPanel p;

    private Menu a;

    private JButton button1;    //singleplayer
    private JButton button2;    //multiplayer
    private JButton enter;      //button with action listener

    private JTextField name1;   //textfield for player1
    private JTextField name2;   //textfield for player2
    private JTextField text;    //textfield for rounds & questions

    public Player pl1;         //object player
    public Player pl2;         //object player

    private String pl_name1;    //name for player1
    private String pl_name2;    //name for player2
    private int rounds;         //rounds for the game
    private int quests;         //questions per round

    private BufferedImage img;  //background image
    private JLabel label;       //label to add image

    public int asd;
    public boolean flag = false;

    public a() {

    }

    public void b() throws IOException {
        f = new JFrame("Επιλογη παικτων");
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(800,500);

        p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
//------------------------------------------------------------------------------
        img = ImageIO.read(new File("images/bg2.jpg"));
        ImageIcon icon = new ImageIcon(img);
        label = new JLabel();
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        label.setIcon(icon);
        p.add(label);
        f.add(p);
//-------------------------------------------------------------------------------
        button1 = new JButton("singleplayer");
        label.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name1.setText("Give name for your player");
                asd = 1;
                single();
            }
        });

        button2 = new JButton("multiplayer");
        label.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name1.setText("Give name for player 1");
                name2.setText("Give name for player 2");
                asd = 2;
                multi();
            }
        });



        name1 = new JTextField("pl1 name");
        name2 = new JTextField("pl2 name");

        f.setVisible(true);
        p.setVisible(true);

//        JPanel p2 = new JPanel();
//        p2.setLayout(new GridLayout(0,1));
//        p2.setBackground(Color.WHITE);
//        f.add(p2 , BorderLayout.CENTER);
//

    }

    public void single() {
        button1.setVisible(false);
        button2.setVisible(false);

        enter = new JButton("submit");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name1.setVisible(false);
                name2.setVisible(false);
                enter.setVisible(false);
                //pl_name2 = "null";
                pl2 = new Player();
                pl2.setName("null");

                pl1 = new Player();
                pl1.setName(name1.getText());

                //pl_name2 = name2.getText();
                //p.setVisible(false);
                System.out.println(pl1.getName());
                System.out.println(pl2.getName());


                rounds();
            }
        });

        GridBagLayout l = new GridBagLayout();

        label.setLayout(l);
        GridBagConstraints l2 = new GridBagConstraints();
        l2.fill = GridBagConstraints.HORIZONTAL;

        l2.gridx = 0;
        l2.gridy = 1;
        l2.gridwidth = 2;
        label.add(name1 , l2);

        l2.gridx = 0;
        l2.gridy = 2;
        l2.gridwidth = 2;
        label.add(enter , l2);

    }

    public void multi() {
        button1.setVisible(false);
        button2.setVisible(false);

        enter = new JButton("submit");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name1.setVisible(false);
                name2.setVisible(false);
                enter.setVisible(false);

                pl1 = new Player();
                pl1.setName(name1.getText());

                pl2 = new Player();
                pl2.setName(name2.getText());
                //p.setVisible(false);
                System.out.println(pl1.getName());
                System.out.println(pl2.getName());
                //System.out.println(pl_name2);


                rounds();
            }
        });

        GridBagLayout l = new GridBagLayout();
        label.setLayout(l);
        GridBagConstraints l2 = new GridBagConstraints();
        l2.fill = GridBagConstraints.HORIZONTAL;
        l2.gridx = 0;
        l2.gridy = 0;
        label.add(name1 , l2);

        l2.gridx = 1;
        l2.gridy = 0;
        label.add(name2 , l2);

        l2.gridx = 0;
        l2.gridy = 2;
        l2.gridwidth = 2;
        label.add(enter , l2);
    }

    public void rounds() {
        text = new JTextField("How many rounds do you want;");
        enter = new JButton("submit");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rounds = Integer.parseInt(text.getText());
                text.setVisible(false);
                enter.setVisible(false);
                System.out.println(rounds);

                questionsR();
            }
        });

        GridBagLayout l = new GridBagLayout();
        GridBagConstraints l2 = new GridBagConstraints();

        l2.fill = GridBagConstraints.HORIZONTAL;
        l2.gridx = 0;
        l2.gridy = 1;
        l2.gridwidth = 2;
        label.add(text , l2);

        l2.gridx = 0;
        l2.gridy = 2;
        l2.gridwidth = 2;
        label.add(enter , l2);
    }

    public void questionsR() {
        text = new JTextField("how many questions per round;");
        enter = new JButton("submit");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests = Integer.parseInt(text.getText());
                System.out.println(quests);

                //String[] all= new String[5];
                all[0] = pl1.getName();
                all[1] = pl2.getName();
                all[2] = Integer.toString(rounds);
                all[3] = Integer.toString(quests);

                flag = true;
                f.dispose();
            }
        });

        GridBagLayout l = new GridBagLayout();
        GridBagConstraints l2 = new GridBagConstraints();

        l2.fill = GridBagConstraints.HORIZONTAL;
        l2.gridx = 0;
        l2.gridy = 1;
        l2.gridwidth = 2;
        label.add(text , l2);

        l2.gridx = 0;
        l2.gridy = 2;
        l2.gridwidth = 2;
        label.add(enter , l2);
    }

    public String[] a2() throws IOException {
        b();

        return all;
    }

    public Player pl1() {
        return this.pl1;
    }

    public Player pl2() {
        return this.pl2;
    }

    public int roundsReturn() {
        return this.rounds;
    }
    public int questionsReturn() {
        return this.quests;
    }

    public JFrame frameReturn() {
        return this.f;
    }
}