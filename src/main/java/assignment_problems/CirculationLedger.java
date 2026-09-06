package assignment_problems;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] copy = bookIds.clone();

        copy[index] = newId;

        return new LoanReceipt(memberId, copy);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}

public class CirculationLedger {

    static String branchCode;

    static {
        branchCode = "PTL-001";
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullCount = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullCount++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            }
            else {
                regular++;
            }
        }

        return processed + " processed | " +
                nullCount + " null skipped | " +
                referenceOnly + " reference-only | " +
                regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{"BK-100", "BK-101"}
                );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println(
                java.util.Arrays.toString(r.getBookIds())
        );

        System.out.println(
                java.util.Arrays.toString(corrected.getBookIds())
        );

        LoanReceipt[] receipts = {

                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}