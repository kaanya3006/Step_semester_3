package class_problems;

public class Student {

    // Instance fields
    String name;
    int attendance;

    // Static fields
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    // Constructor
    Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    // Static method
    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {

        Student student1 = new Student("Ravi", 85);
        Student student2 = new Student("Anitha", 90);

        Student.printCollegeInfo();
    }
}