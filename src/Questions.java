import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Questions {
    ArrayList<Question> questions;
    ArrayList<Question> usedQuestions;
    String path;
    String category;

    //Questions questions = new Questions()
    //questions.getQuestions(5) // 5 == questions per round

    public Questions() {
        questions = new ArrayList<>();
        usedQuestions = new ArrayList<>();
        questionsMaker();
    }

    public void questionsMaker() {

        String filename = "questions.txt";
        try {
            File txt_file = new File(filename);
            Scanner qScanner = new Scanner(txt_file);

            while (qScanner.hasNextLine()) {
                String qData = qScanner.nextLine();
                String[] data = qData.split(",");

                category = data[0];  //category
                String question = data[1];  //question

                ArrayList answers = new ArrayList<String>();
                answers.add(data[2]);  //ans1
                answers.add(data[3]);  //ans2
                answers.add(data[4]);  //ans3
                answers.add(data[5]);  //ans4

                String correctAnswer = data[6];  //correct answer
                if(data[0] == "Εικόνα") {
                    path = data[7];
                }

                Question newQuestion = new Question(category, question, answers, correctAnswer);
                questions.add(newQuestion);
            }
            qScanner.close();
        } catch(FileNotFoundException e) {
            System.out.print("Could not find the file" + filename);
            e.printStackTrace();
        }
    }

    public String takeCategory() {
        return this.category;
    }
    public String takePath() {
        return this.path;
    }

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    /**
     * Returns a number of questions from questions array.
     * @param number the quantity of questions that will be returned.
     * @return ArrayList<Question> returnedQuestions. Holds the questions.
     */
    public ArrayList<Question> getQuestions(int number) {
        shuffleQuestions();
        ArrayList<Question> returnedQuestions = new ArrayList<>();
        if(number <= questions.size()) {
            for(int i=0 ; i<number ; i++) {
                returnedQuestions.add(questions.get(i));
                usedQuestions.add(questions.get(i));
                //questions.remove(i);
            }
            for(Question quest: usedQuestions) {
                questions.remove(quest);
            }
        }
        else {
            System.out.print("Not enough questions");
        }
        return returnedQuestions;

    }
}