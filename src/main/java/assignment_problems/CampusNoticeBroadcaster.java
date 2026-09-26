package assignment_problems;

import java.util.*;

class child {

    private String name;
    private String department;

    private ArrayList<NotificationChannel> channels =
            new ArrayList<>();

    public child(
            String name,
            String department) {

        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(
            NotificationChannel channel) {

        channels.add(channel);
    }

    public ArrayList<NotificationChannel> getChannels() {
        return channels;
    }
}

interface NotificationChannel {
    void send(child student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(
            child student,
            Notice notice) {

        System.out.println(
                "[Email → "
                        + student.getName()
                        + "] "
                        + notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {

    public void send(
            child student,
            Notice notice) {

        System.out.println(
                "[SMS → "
                        + student.getName()
                        + "] "
                        + notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {

    public void send(
            child student,
            Notice notice) {

        System.out.println(
                "[App → "
                        + student.getName()
                        + "] "
                        + notice.getTitle());
    }
}

class Notice {

    private String title;
    private ArrayList<String> departments;

    public Notice(
            String title,
            ArrayList<String> departments) {

        this.title = title;
        this.departments = departments;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }

    public boolean isValid() {

        return title != null
                && !title.trim().isEmpty()
                && departments != null
                && !departments.isEmpty();
    }
}

class NoticeBoard {

    private ArrayList<child> students =
            new ArrayList<>();

    public void addStudent(child student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        if (!notice.isValid()) {

            System.out.println(
                    "Cannot post notice: At least one target department is required.");

            return;
        }

        System.out.println(
                "Notice '"
                        + notice.getTitle()
                        + "' posted to "
                        + String.join(
                        ", ",
                        notice.getDepartments())
                        + ".");

        for (child student : students) {

            if (notice.getDepartments()
                    .contains(student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getChannels()) {

                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster{

    public static void main(String[] args) {

        NoticeBoard board =
                new NoticeBoard();

        child asha =
                new child("Asha", "CSE");

        child ravi =
                new child("Ravi", "ECE");

        asha.addChannel(
                new EmailChannel());

        asha.addChannel(
                new AppChannel());

        ravi.addChannel(
                new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        ArrayList<String> cse =
                new ArrayList<>();

        cse.add("CSE");

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        cse);

        board.postNotice(notice1);

        ArrayList<String> both =
                new ArrayList<>();

        both.add("CSE");
        both.add("ECE");

        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        both);

        board.postNotice(notice2);

        Notice invalid =
                new Notice(
                        "Sports Day",
                        new ArrayList<>());

        board.postNotice(invalid);
    }
}