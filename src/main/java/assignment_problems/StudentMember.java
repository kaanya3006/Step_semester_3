package assignment_problems;

public class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);

        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {

        System.out.println(
                "Student Member | Course: "
                        + course
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }

    // Problem 3
    @Override
    protected void chargeFine(int amount) {

        // Student gets 50% discount
        super.chargeFine(amount / 2);
    }
}