package assignment_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {

    protected String title;
    protected int maxMarks;
    protected LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public long getLateDays(LocalDate submissionDate) {

        if (submissionDate.isAfter(dueDate)) {
            return ChronoUnit.DAYS.between(dueDate, submissionDate);
        }

        return 0;
    }

    public abstract double applyPenalty(double marks, long lateDays);
}


class CodingAssignment extends Assignment {

    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyPenalty(double marks, long lateDays) {

        double penalty = lateDays * 0.10;

        return marks * (1 - penalty);
    }
}


class WrittenAssignment extends Assignment {

    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyPenalty(double marks, long lateDays) {

        double penalty = lateDays * 0.20;

        return marks * (1 - penalty);
    }
}


// Renamed from Student to avoid duplicate class name
class AssignmentStudent {

    private String name;

    public AssignmentStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


enum SubmissionStatus {
    SUBMITTED,
    GRADED
}


class Submission {

    private AssignmentStudent student;
    private Assignment assignment;
    private LocalDate submissionDate;

    private SubmissionStatus status = SubmissionStatus.SUBMITTED;

    private double finalMarks;

    public Submission(AssignmentStudent student,
                      Assignment assignment,
                      LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
    }

    public void grade(double awardedMarks) {

        if (status == SubmissionStatus.GRADED) {

            System.out.println(
                    "Cannot grade: submission already graded.");

            return;
        }

        long lateDays = assignment.getLateDays(submissionDate);

        finalMarks =
                assignment.applyPenalty(awardedMarks, lateDays);

        status = SubmissionStatus.GRADED;

        System.out.println(
                student.getName()
                        + " graded: "
                        + String.format("%.0f", finalMarks)
                        + "/"
                        + assignment.getMaxMarks()
                        + ". Status: "
                        + status);
    }


    public void resubmit(LocalDate newDate) {

        if (status == SubmissionStatus.GRADED) {

            System.out.println(
                    "Cannot resubmit: '"
                            + assignment.getTitle()
                            + "' has already been graded.");

            return;
        }

        submissionDate = newDate;
    }


    public SubmissionStatus getStatus() {
        return status;
    }
}


public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10));


        Assignment written =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12));


        AssignmentStudent asha =
                new AssignmentStudent("Asha");

        AssignmentStudent ravi =
                new AssignmentStudent("Ravi");


        Submission s1 =
                new Submission(
                        asha,
                        coding,
                        LocalDate.of(2026, 3, 10));


        Submission s2 =
                new Submission(
                        ravi,
                        written,
                        LocalDate.of(2026, 3, 14));


        System.out.println(
                "Asha's submission for 'Linked List Lab' received.");

        System.out.println(
                "Status: " + s1.getStatus());


        System.out.println(
                "Ravi's submission for 'Design Essay' received.");

        System.out.println(
                "Status: " + s2.getStatus());


        s1.grade(45);

        s2.grade(40);


        s1.resubmit(
                LocalDate.of(2026, 3, 11));
    }
}