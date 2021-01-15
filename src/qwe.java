import javax.swing.plaf.FileChooserUI;
import java.io.*;

public class qwe {
    private Player name1,name2;
    private int i=0;


    private String []information;
    public qwe(Player p1 , Player p2) throws IOException, InterruptedException {
        this.name1 = p1;
        this.name2 = p2;
        //System.setProperty("file.txt.encoding", "UTF-8");

        boolean flag1=false,flag2=false;
        int namethesi1=-1,namethesi2=-1;


        String filename;
        filename="file.txt";
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            /**
             * Διαβασμα αρχειου γραμμη γραμμη
             */
            String l;
            information = new String[100000];
            while ((l = in.readLine()) != null){
                information[i] =l;
                i++;
            }
            delete(in);
            /**
             * Ελενχως αν υπαρχει το ονομα του πρωτου παιχτη ειδη στο αρχειο
             */
            int j=0;
            while (j<i && !flag1){
                if (information[j].equals(name1.getName()))
                {
                    namethesi1=j;
                    flag1=true;
                }
                j+=3;
            }
            /**
             * Ελενχως αν υπαρχει δευτερος παιχτη
             * Αν υπαρχει βρησκει αν εχει αποθηκευτη και αυτος στο αρχειο
             */
            if (!(name2.getName().equals("null"))){
                j=0;
                while (j<i && !flag2){
                    if (information[j].equals(name2.getName()))
                    {
                        namethesi2=j;
                        flag2=true;
                    }
                    j+=3;
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File was not found");
        }

        try(BufferedWriter out =new BufferedWriter(new FileWriter(filename)))
        {
            /**
             * Ελενχως για το ποιος παιχτης υπαρχει στο αρχειο αν δεν υπαχει κανενας καλλειται η πρωτη if
             * Αν υπαρχει ο δευτερος τοτε καλειται η δευτερη
             * Αν υπαχει ο πρβτος τοτε  κελειται  η τριτη
             * Με μια δομη επαναληψης FOR βαχω στο αρχειο τους ιδιους παιχτες και προσθετω στο τελος του δυο καινουργιους
             * Ενω με την βοηθεια new Integer(int).toString(); μετατρεπω τους ποντους και της νικες σε STRING για ευκολοτερη
             * αποθηκευση
             */
            if (namethesi1==-1 && namethesi2==-1) {
                for (int j = 0; j < i; j++) {
                    out.write(information[j]);
                    out.write("\n");
                }
                out.write(name1.getName());
                out.write("\n");
                String str1 = new Integer(name1.getWins()).toString();
                out.write(str1);
                System.out.println(name1.getWins());
                out.write("\n");
                String str2 = new Integer(name1.getPoints()).toString();
                out.write(str2);
                out.write("\n");
                out.write(name2.getName());
                out.write("\n");
                String str3 = new Integer(name2.getWins()).toString();
                out.write(str3);
                out.write("\n");
                String str4 = new Integer(name2.getPoints()).toString();
                out.write(str4);
                out.write("\n");


            }
            else if (namethesi1==-1 && namethesi2!=-1) {
                for (int j = 0; j < i; j++) {
                    out.write(information[j]);
                    out.write("\n");
                }
                out.write(name1.getName());
                out.write("\n");
                String str1 = new Integer(name1.getWins()).toString();
                out.write(str1);
                System.out.println(name1.getWins());
                out.write("\n");
                String str2 = new Integer(name1.getPoints()).toString();
                out.write(str2);
                out.write("\n");
            }
            else if (namethesi1!=-1 && namethesi2==-1) {
                for (int j = 0; j < i; j++) {
                    out.write(information[j]);
                    out.write("\n");
                }
                out.write(name2.getName());
                out.write("\n");
                String str1 = new Integer(name2.getWins()).toString();
                out.write(str1);
                out.write("\n");
                String str2 = new Integer(name2.getPoints()).toString();
                out.write(str2);
                out.write("\n");
            }
        }catch (FileNotFoundException e){
            System.out.println("File was not found");
        }

    }

    private void delete(BufferedReader in) {
    }
}
