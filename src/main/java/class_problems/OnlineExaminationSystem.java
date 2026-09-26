package class_problems;

import java.util.*;

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Question {
    protected String questionText;
    protected int marks;

    public Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);

    public int getMarks() {
        return marks;
    }
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(
            String questionText,
            int marks,
            String correctAnswer) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
            String questionText,
            int marks,
            boolean correctAnswer) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    public ShortAnswerQuestion(
            String questionText,
            int marks,
            String correctAnswer) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    private String name;
    private ArrayList<Question> questions = new ArrayList<>();

    public Examination(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}

enum AttemptStatus {
    IN_PROGRESS,
    SUBMITTED
}

class Attempt {

    private Student student;
    private Examination examination;

    private HashMap<Question, String> answers =
            new HashMap<>();

    private AttemptStatus status =
            AttemptStatus.IN_PROGRESS;

    public Attempt(Student student,
                   Examination examination) {

        this.student = student;
        this.examination = examination;
    }

    public void answerQuestion(
            Question question,
            String answer) {

        if (status == AttemptStatus.SUBMITTED) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(question, answer);

        System.out.println(
                "Answer recorded for Question.");
    }

    public void submit() {

        if (status == AttemptStatus.SUBMITTED) {
            return;
        }

        status = AttemptStatus.SUBMITTED;

        int totalScore = 0;
        int totalMarks = 0;

        System.out.println(
                examination.getName()
                        + " submitted by "
                        + student.getName());

        for (Question q : examination.getQuestions()) {

            totalMarks += q.getMarks();

            String answer = answers.get(q);

            if (answer != null && q.evaluate(answer)) {

                System.out.println(
                        "Correct (" + q.getMarks() + " points)");

                totalScore += q.getMarks();

            } else {

                System.out.println(
                        "Incorrect (0 points)");
            }
        }

        System.out.println(
                "Total score: "
                        + totalScore + "/"
                        + totalMarks);
    }
}

public class OnlineExaminationSystem{

    public static void main(String[] args) {

        Student s = new Student("Student 1");

        Examination exam =
                new Examination("Exam A");

        Question q1 =
                new MultipleChoiceQuestion(
                        "Choose the correct option",
                        5,
                        "C");

        Question q2 =
                new TrueFalseQuestion(
                        "Java is object oriented",
                        5,
                        false);

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt =
                new Attempt(s, exam);

        System.out.println(
                "Exam A started by Student 1.");

        attempt.answerQuestion(q1, "C");
        attempt.answerQuestion(q2, "True");

        attempt.submit();

        attempt.answerQuestion(q1, "A");
    }
}
