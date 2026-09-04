package Class_problems;

class SrmStudent {

    static String collegeName;
    static String academicYear;

    String studentName;

    // Static block
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    SrmStudent(String studentName) {
        this.studentName = studentName;
    }
}

public class M4_College {
    public static void main(String[] args) {

        String[] names = {
                "Ravi",
                "Meera",
                "Karthik",
                "Divya",
                "Anitha"
        };

        for (int i = 0; i < names.length; i++) {

            SrmStudent student =
                    new SrmStudent(names[i]);

            System.out.println(
                    "Student record created: " +
                            student.studentName
            );
        }
    }
}
