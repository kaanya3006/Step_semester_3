package assignment_problems;

abstract class WashType {
    public abstract int getDuration();
    public abstract double getCharge();
}

class QuickWash extends WashType {
    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class NormalWash extends WashType {
    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class HeavyWash extends WashType {
    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }
}

class LaundryStudent {
    private String name;

    public LaundryStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String id;
    private boolean busy = false;

    public WashingMachine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return !busy;
    }

    private void setBusy(boolean busy) {
        this.busy = busy;
    }

    public WashCycle startWash(
            LaundryStudent student,
            WashType type) {

        if (busy) {
            System.out.println(
                    "Machine " + id + " is currently busy.");
            return null;
        }

        setBusy(true);

        WashCycle cycle =
                new WashCycle(student, this, type);

        System.out.println(
                type.getClass().getSimpleName()
                        + " started on " + id
                        + " for " + student.getName()
                        + " (" + type.getDuration()
                        + " min).");

        System.out.printf(
                "Charge: ₹%.2f%n",
                type.getCharge());

        return cycle;
    }

    public void completeCycle() {

        if (busy) {
            setBusy(false);

            System.out.println(
                    id + " cycle completed.");

            System.out.println(
                    id + " is now free.");
        }
    }
}

class WashCycle {

    private LaundryStudent student;
    private WashingMachine machine;
    private WashType type;

    public WashCycle(
            LaundryStudent student,
            WashingMachine machine,
            WashType type) {

        this.student = student;
        this.machine = machine;
        this.type = type;
    }

    public WashingMachine getMachine() {
        return machine;
    }
}

public class HostelLaundryQueue {

    public static void main(String[] args) {

        LaundryStudent asha =
                new LaundryStudent("Asha");

        LaundryStudent ravi =
                new LaundryStudent("Ravi");

        LaundryStudent neha =
                new LaundryStudent("Neha");

        WashingMachine m1 =
                new WashingMachine("M1");

        WashingMachine m2 =
                new WashingMachine("M2");

        WashCycle c1 =
                m1.startWash(
                        asha,
                        new QuickWash());

        m1.startWash(
                ravi,
                new HeavyWash());

        m2.startWash(
                ravi,
                new HeavyWash());

        m1.completeCycle();

        m1.startWash(
                neha,
                new NormalWash());
    }
}