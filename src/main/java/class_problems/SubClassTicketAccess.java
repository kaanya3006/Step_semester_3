package class_problems;

    class MovieTicket2 {

        protected double ticketPrice;
        private String seatNumber;
        String screenId;
        public String movieTitle;
    }

    class PremiumMovieTicket extends MovieTicket2 {

        void accessOwnType() {
            System.out.println(ticketPrice);
        }
    }

    public class SubClassTicketAccess {

        static String classifyAccess(String fieldModifier,
                                     String accessorContext) {

            if (fieldModifier.equals("private")) {

                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                }

            } else if (fieldModifier.equals("default")) {

                if (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }

            } else if (fieldModifier.equals("protected")) {

                if (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE") ||
                        accessorContext.equals(
                                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                    return "ALLOWED";
                }

            } else if (fieldModifier.equals("public")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        public static void main(String[] args) {

            System.out.println(
                    classifyAccess(
                            "protected",
                            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                    )
            );

            System.out.println(
                    classifyAccess(
                            "protected",
                            "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                    )
            );
        }
    }