package main.factory;

import main.pojo.Employee;
import main.pojo.EmployeePair;

import java.util.*;

public class EmployeePairFactory {

    public static Set<EmployeePair> getAllPairs(List<Employee> employees) {
        HashMap<EmployeePair,EmployeePair> employeePairs = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee1 = employees.get(i);

            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee2 = employees.get(j);

                if (employee1.getEmployeeId() == employee2.getEmployeeId()) {
                    continue;
                }
                if (employeesWorkedTogether(employee1, employee2)) {
                    EmployeePair pair = new EmployeePair(employee1, employee2);
                    if (employeePairs.containsKey(pair)){
                        employeePairs.get(pair).addDays(pair.getDaysWorked());
                    }
                    employeePairs.put(pair,pair);
                }
            }
        }
        return new HashSet<>(employeePairs.values());
    }

    private static boolean employeesWorkedTogether(Employee employee2, Employee employee1) {
        return (employee1.getProjectId() == employee2.getProjectId() &&
                periodsOverlap(employee1, employee2));
    }

    private static boolean periodsOverlap(Employee employee1, Employee employee2) {
        return employee1.getDateFrom().compareTo(employee2.getDateTo()) <= 0 &&
               employee2.getDateFrom().compareTo(employee1.getDateTo()) <= 0;
    }


}
