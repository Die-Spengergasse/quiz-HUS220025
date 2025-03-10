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

    public Quiz()
    {
        EntityManager em = Persistence.createEntityManagerFactory("demo")
                .createEntityManager();
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
        List<Question> questions = query.getResultList();
    }

    public boolean showQaA()
    {
        List<Answer> answers = q.getAnwerList();
        for (int i = 0; i < answers.size(); i++)
        {
            Answer a = answers.get(i);
            System.out.println((i+1) + ") " + a.getText());
        }

        System.out.println("your answer: ");
        int userAnswer = Integer.parseInt(sc.nextLine());
        checkAnswer(q, userAnswer);
        return true;
    }

    private void checkAnswer(List<Question> q, int userAnswer) {
    }

}
