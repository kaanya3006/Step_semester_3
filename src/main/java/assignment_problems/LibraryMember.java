package assignment_problems;

import java.util.Arrays;

public class LibraryMember {

    protected String memberId;
    protected int borrowLimit;

    // Problem 5
    static int memberCounter = 100;
    final String memberNumber;

    // Borrow count
    protected int booksBorrowed = 0;

    // Fine history
    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    private int totalFine = 0;

    // Problem 1 constructor
    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null ||
                memberId.trim().isEmpty() ||
                memberId.length() < 4) {

            throw new IllegalArgumentException("Invalid member ID");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Borrow limit must be positive");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;

        // Problem 5
        memberCounter++;
        this.memberNumber = "LIB-" + memberCounter;
    }

    // Problem 5 constructor
    public LibraryMember(int borrowLimit) {

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Borrow limit must be positive");
        }

        this.borrowLimit = borrowLimit;

        memberCounter++;
        this.memberNumber = "LIB-" + memberCounter;
    }

    // Problem 1
    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    // Problem 2
    public void displayInfo() {
        System.out.println(
                "General Member | Books Borrowed: " + booksBorrowed
        );
    }

    // Problem 3
    protected void chargeFine(int amount) {

        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }

        totalFine += amount;
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        return totalFine;
    }

    // Problem 5
    private String lastGenre;

    public void borrowBook(String genre) {

        lastGenre = genre;

        // Reuse existing borrowing logic
        borrowBook();
    }

    public static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return memberCounter - 100;
    }

    // Problem 1
    public static String enrollBatch(String[] memberIds, int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (String memberId : memberIds) {

            try {
                new LibraryMember(memberId, borrowLimit);
                enrolled++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled +
                " | Rejected: " + rejected;
    }

    // Problem 2
    public static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        return "General member";
    }

    // Problem 2
    public static int getTotalBooksBorrowed(LibraryMember[] members) {

        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    // Problem 4
    public static String batchPrint(LibraryMember[] members) {

        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {

            // Polymorphism
            // Each object's own displayInfo() executes
            member.displayInfo();

            if (member instanceof StudentMember) {

                StudentMember student = (StudentMember) member;

                result.append(getDisplayInfo(student));

                result.append(
                        " [Course via downcast: "
                                + student.getCourse()
                                + "] | "
                );

            } else {

                result.append(getDisplayInfo(member))
                        .append(" | ");
            }
        }

        return result.toString();
    }

    // Helper for Problem 4
    private static String getDisplayInfo(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {

            HonorsStudentMember h =
                    (HonorsStudentMember) member;

            return "Honors Student Member | Course: "
                    + h.getCourse()
                    + " | Bonus Limit: "
                    + h.getBonusLimit()
                    + " | Books Borrowed: "
                    + h.getBooksBorrowed();

        } else if (member instanceof FacultyMember) {

            FacultyMember f =
                    (FacultyMember) member;

            return "Faculty Member | Department: "
                    + f.getDepartment()
                    + " | Books Borrowed: "
                    + f.getBooksBorrowed();

        } else if (member instanceof StudentMember) {

            StudentMember s =
                    (StudentMember) member;

            return "Student Member | Course: "
                    + s.getCourse()
                    + " | Books Borrowed: "
                    + s.getBooksBorrowed();

        } else {

            return "General | Books: "
                    + member.getBooksBorrowed();
        }
    }

    // Problem 5
    public static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }
}