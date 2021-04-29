package main.pojo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeePair {

    Employee employee1;
    Employee employee2;
    long daysWorked;

    public EmployeePair(Employee employee1, Employee employee2) {
        this.employee1 = employee1;
        this.employee2 = employee2;
        this.daysWorked = totalDays();
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    private Long totalDays() {
        LocalDate start = employee1.getDateFrom().isAfter(employee2.getDateFrom()) ? employee1.getDateFrom() : employee2.getDateFrom();
        LocalDate end = employee1.getDateTo().isBefore(employee2.getDateTo()) ? employee1.getDateTo() : employee2.getDateTo();
        long totalDays = ChronoUnit.DAYS.between(start, end);
        return totalDays > 0 ? totalDays : 0L;
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public void addDays(long days) {
        this.daysWorked += days;
    }
}
