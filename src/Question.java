import java.util.ArrayList;
import java.util.Collections;

public class Question {
    private String category;
    private String question;
    private String correctAnswer;
    private ArrayList<String> answers;

    public Question(String category, String question, ArrayList<String> answers, String correctAnswer) {
        this.category = category;
        this.answers = answers;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public void shuffleAnswers() {
        Collections.shuffle(answers);
    }

    public String printQuestion() {
//        System.out.println(this.question);
        return this.question;
    }

    public void printCategory() {
        System.out.println(this.category);
    }

    public ArrayList<String> printAnswers() {
        shuffleAnswers();
//        int counter = 1;
//
//        for(int k=0 ; k<this.answers.size() ; k++) {
//            System.out.println(counter + "." + answers.get(k));
//            counter++;
//        }
        return answers;
    }

    public int getCorrectAnswer() {
        int temp = answers.indexOf(this.correctAnswer);
        return ++temp;
    }
}