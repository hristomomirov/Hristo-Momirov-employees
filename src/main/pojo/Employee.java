package main.pojo;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private int employeeId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Employee withEmployeeId(int employeeId) throws IllegalArgumentException {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Employee id cannot be less than 0");
        }
        this.employeeId = employeeId;
        return this;
    }

    public Employee withProjectId(int projectId) throws IllegalArgumentException {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Project id cannot be less than 0");
        }
        this.projectId = projectId;
        return this;
    }

    public Employee withDateFrom(LocalDate dateFrom) throws IllegalArgumentException {
        if (dateFrom.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Starting date cannot be after " + LocalDate.now());
        }
        this.dateFrom = dateFrom;
        return this;
    }

    public Employee withDateTo(LocalDate dateTo) throws IllegalArgumentException  {
        if (dateTo.isBefore(dateFrom)){
            throw new IllegalArgumentException("End date cannot be before starting date");
        }
        this.dateTo = dateTo;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                projectId == employee.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, projectId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}


