package at.spengergasse;

import at.spengergasse.entities.Answer;
import at.spengergasse.entities.Question;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz
{
    private List<Question> questions;
    Scanner sc = new Scanner(System.in);
    Question q;
    int nextQ = 0;
    int correctAnswers = 0;

    public Quiz()
    {
        EntityManager em = Persistence.createEntityManagerFactory("demo")
                .createEntityManager();
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
        this.questions = query.getResultList();
    }

    public void start ()
    {
        for (Question q : questions)
        {
            showQaA(q);
        }
        finish();
    }
    public void showQaA(Question q)
    {
        System.out.println("\n" + q.getText());
        List<Answer> answers = q.getAnswerList();

        for (int i = 0; i < answers.size(); i++)
        {

            Answer a = answers.get(i);
            System.out.println((i+1) + ") " + a.getText());

        }

        System.out.println("your answer: ");
        int userAnswer = Integer.parseInt(sc.nextLine());
        checkAnswer(q, userAnswer );


    }

    private void finish()
    {
        double percentage = (double) correctAnswers / questions.size() * 100;
        System.out.println("You completed the Quiz!");
        System.out.println("You answered " + correctAnswers + " out of " + questions.size() + " questions correctly.");
        System.out.println("That's " + percentage + "% correct answers!");
    }

    private void checkAnswer(Question q, int userAnswer)
    {
        List<Answer> answers = q.getAnswerList();
        if (userAnswer >= 1 && userAnswer <= answers.size())
        {
            Answer selectedAnswer = answers.get(userAnswer - 1);
            if (selectedAnswer.isCorrect())
            {
                System.out.println("Correct!");
                correctAnswers++;
            }
            else
            {
                System.out.println("Wrong! The correct answer was: " + getCorrectAnswerText(answers));
            }
        }
        else
        {
            System.out.println("Invalid answer!");
        }
    }

    private String getCorrectAnswerText(List<Answer> answers)
    {
        for (Answer answer : answers)
        {
            if (answer.isCorrect())
            {
                return answer.getText();
            }
        }
        return "No correct answer found!";
    }

}
