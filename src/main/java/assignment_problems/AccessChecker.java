package assignment_problems;

class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

public class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
        }
        else if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
        }
        else if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
        }
        else if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
                "private", "default", "protected", "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            for (int j = 0; j < modifiers.length; j++) {

                if (modifier.equals(modifiers[j])) {

                    if (result.equals("ALLOWED")) {
                        allowed[j]++;
                    } else {
                        denied[j]++;
                    }

                    break;
                }
            }
        }

        return "private: " + allowed[0] + " allowed / " + denied[0] + " denied | " +
                "default: " + allowed[1] + " allowed / " + denied[1] + " denied | " +
                "protected: " + allowed[2] + " allowed / " + denied[2] + " denied | " +
                "public: " + allowed[3] + " allowed / " + denied[3] + " denied";
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));
    }
}