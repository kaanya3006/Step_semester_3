package class_problems;

public class GroupClassMember extends GymMember {

    private String className;

    // Problem 2
    public GroupClassMember(
            String memberId,
            int monthlyFee,
            String className) {

        super(memberId, monthlyFee);

        this.className = className;
    }

    // Problem 5
    public GroupClassMember(
            int monthlyFee,
            String className) {

        super(monthlyFee);

        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {

        return "Group Class Member | Class: "
                + className
                + " | Sessions: "
                + sessionsAttended;
    }
}