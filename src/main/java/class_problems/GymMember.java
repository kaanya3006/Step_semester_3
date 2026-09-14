package class_problems;

import java.util.Arrays;

public class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    // Problem 3
    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;
    private int totalLateFees = 0;

    // Problem 5
    private static int memberCounter = 2000;
    public final String membershipNumber;

    private int feesPaid = 0;
    private String paymentMode;

    // Problem 1
    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null ||
                memberId.trim().isEmpty() ||
                memberId.length() < 4) {

            throw new IllegalArgumentException("Invalid member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        memberCounter++;
        this.membershipNumber = "GYM-" + memberCounter;
    }

    // Problem 5
    public GymMember(int monthlyFee) {

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive");
        }

        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        memberCounter++;
        this.membershipNumber = "GYM-" + memberCounter;
    }

    // Problem 1
    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    // Problem 2 and 4
    public String displayInfo() {
        return "Standard Member | Sessions: "
                + sessionsAttended;
    }

    // Problem 3
    protected void chargeLateFee(int amount) {

        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }

        totalLateFees += amount;
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public int getTotalLateFees() {
        return totalLateFees;
    }

    // Problem 1
    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String memberId : memberIds) {

            try {
                new GymMember(memberId, monthlyFee);
                signedUp++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }

    // Problem 2
    public static String classifyGeneration(GymMember member) {

        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Standard member";
    }

    // Problem 2
    public static int getTotalSessionsAttended(
            GymMember[] members) {

        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }

    // Problem 4
    public static String batchPrint(GymMember[] members) {

        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {

            // Polymorphism
            result.append(member.displayInfo());

            // Safe downcast
            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                result.append(
                        " [Trainer via downcast: "
                                + premium.getTrainerName()
                                + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    // Problem 5
    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {

        paymentMode = mode;

        // Reuse one-argument method
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    // Problem 5
    public static boolean isValidReferralCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return memberCounter - 2000;
    }

    // Problem 5
    public static String processWeeklyCheckIn(
            GymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}