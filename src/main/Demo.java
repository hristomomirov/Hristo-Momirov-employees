package main;

import main.fafctory.EmployeeFactory;
import main.fafctory.EmployeePairFactory;
import main.pojo.Employee;
import main.pojo.EmployeePair;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {

        List<Employee> employees = EmployeeFactory.getAllEmployees();
        Set<EmployeePair> employeePairs = EmployeePairFactory.getAllPairs(employees);
        LinkedList<EmployeePair> sortedPairs = new LinkedList<>(employeePairs);
        sortedPairs.sort((e2, e1) -> Long.compare(e1.getDaysWorked(), e2.getDaysWorked()));
        if (sortedPairs.isEmpty()) {
            System.out.println("There is no such pair");
        } else {
            EmployeePair pairWithMostDaysTogether = sortedPairs.getFirst();
            System.out.println("The employees with the most days worked together on the same projects are " + "employee with ID " +
                    pairWithMostDaysTogether.getEmployee1().getEmployeeId() + " and " + "employee with ID " +
                    pairWithMostDaysTogether.getEmployee2().getEmployeeId() + " with " +
                    pairWithMostDaysTogether.getDaysWorked() + " total days");
        }
    }
}
