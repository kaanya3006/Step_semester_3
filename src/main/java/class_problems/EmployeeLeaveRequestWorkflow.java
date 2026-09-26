package class_problems;

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {

    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private LeaveStatus status;

    public LeaveRequest(Employee employee,
                        String startDate,
                        String endDate,
                        int days) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = LeaveStatus.PENDING;
    }

    public void approve() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                    "Cannot approve a request that is already "
                            + status);
            return;
        }

        if (!employee.canTakeLeave(days)) {
            System.out.println("Leave policy does not allow this request.");
            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(employee.getName()
                + "'s leave request ("
                + startDate + "-" + endDate
                + ") approved.");
    }

    public void reject() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                    "Cannot reject a request that is already "
                            + status);
            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(employee.getName()
                + "'s leave request ("
                + startDate + "-" + endDate
                + ") rejected.");
    }

    public void setStatus(LeaveStatus newStatus) {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                    "Cannot change leave request status from "
                            + status + " to " + newStatus);
            return;
        }

        status = newStatus;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}

public class EmployeeLeaveRequestWorkflow{

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");

        LeaveRequest request =
                new LeaveRequest(
                        john,
                        "Jan 1",
                        "Jan 5",
                        5
                );

        System.out.println(
                "Leave request submitted for "
                        + john.getName()
                        + " (Jan 1-Jan 5). Status: "
                        + request.getStatus());

        request.approve();

        System.out.println("Status: " +
                request.getStatus());

        request.setStatus(LeaveStatus.PENDING);
    }
}
