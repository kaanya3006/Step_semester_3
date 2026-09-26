package assignment_problems;

abstract class MembershipPlan {

    public abstract int getMonths();

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {

    public int getMonths() {
        return 1;
    }

    public double calculateFee() {
        return 1000;
    }
}

class QuarterlyPlan extends MembershipPlan {

    public int getMonths() {
        return 3;
    }

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {

    public int getMonths() {
        return 12;
    }

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {

    private Member member;
    private MembershipPlan plan;

    private MembershipStatus status =
            MembershipStatus.ACTIVE;

    public Membership(
            Member member,
            MembershipPlan plan) {

        this.member = member;
        this.plan = plan;
    }

    public void checkIn() {

        if (status == MembershipStatus.ACTIVE) {

            System.out.println(
                    member.getName()
                            + " checked in successfully.");

        } else {

            System.out.println(
                    "Check-in denied: "
                            + member.getName()
                            + "'s membership is "
                            + status + ".");
        }
    }

    public void freeze() {

        if (status == MembershipStatus.ACTIVE) {

            status = MembershipStatus.FROZEN;

            System.out.println(
                    member.getName()
                            + "'s membership frozen.");

            System.out.println(
                    "Status: " + status);

        } else {

            System.out.println(
                    "Cannot freeze an "
                            + status
                            + " membership.");
        }
    }

    public void unfreeze() {

        if (status == MembershipStatus.FROZEN) {

            status = MembershipStatus.ACTIVE;

            System.out.println(
                    member.getName()
                            + "'s membership unfrozen.");

        } else {

            System.out.println(
                    "Cannot unfreeze an "
                            + status
                            + " membership.");
        }
    }

    public void expire() {

        status = MembershipStatus.EXPIRED;

        System.out.println(
                member.getName()
                        + "'s membership expired.");

        System.out.println(
                "Status: " + status);
    }

    public MembershipStatus getStatus() {
        return status;
    }
}

public class FitZoneMembershipDesk{

    public static void main(String[] args) {

        Member asha =
                new Member("Asha");

        Member ravi =
                new Member("Ravi");

        MembershipPlan quarterly =
                new QuarterlyPlan();

        MembershipPlan monthly =
                new MonthlyPlan();

        Membership ashaMembership =
                new Membership(
                        asha,
                        quarterly);

        Membership raviMembership =
                new Membership(
                        ravi,
                        monthly);

        System.out.println(
                "Quarterly membership created for Asha.");

        System.out.printf(
                "Fee: ₹%.2f%n",
                quarterly.calculateFee());

        System.out.println(
                "Status: "
                        + ashaMembership.getStatus());

        System.out.println(
                "Monthly membership created for Ravi.");

        System.out.printf(
                "Fee: ₹%.2f%n",
                monthly.calculateFee());

        System.out.println(
                "Status: "
                        + raviMembership.getStatus());

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}
