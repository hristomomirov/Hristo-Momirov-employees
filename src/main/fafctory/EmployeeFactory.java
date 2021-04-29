package main.fafctory;

import main.pojo.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EmployeeFactory {

    private static final String[] POSSIBLE_DATE_FORMATS =
            {
                    "yyyy-MM-dd",
                    "yyyyMMdd",
                    "yyyy/MM/dd",
                    "dd/MM/yy",
                    "dd/MM/yyyy",
                    "yyyy.MM.dd G 'at' HH:mm:ss z",
                    "EEE, MMM d, ''yy",
                    "h:mm a",
                    "hh 'o''clock' a, zzzz",
                    "K:mm a, z",
                    "yyyyy.MMMMM.dd GGG hh:mm a",
                    "EEE, d MMM yyyy HH:mm:ss Z",
                    "yyMMddHHmmssZ",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                    "YYYY-'W'ww-u",
                    "EEE, dd MMM yyyy HH:mm:ss z",
                    "EEE, dd MMM yyyy HH:mm zzzz",
                    "yyyy-MM-dd'T'HH:mm:ssZ",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz",
                    "yyyy-MM-dd'T'HH:mm:sszzzz",
                    "yyyy-MM-dd'T'HH:mm:ss z",
                    "yyyy-MM-dd'T'HH:mm:ssz",
                    "yyyy-MM-dd'T'HH:mm:ss",
                    "yyyy-MM-dd'T'HHmmss.SSSz"
            };

    public static List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        try (InputStream input = EmployeeFactory.class.getResourceAsStream("..\\employees.txt");
             Scanner sc = new Scanner(input)) {

            while (sc.hasNextLine()) {
                String[] attributes = sc.nextLine().split(",");
                try {
                    Employee employee = EmployeeFactory.create(attributes);
                    allEmployees.add(employee);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid employee or project id");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Sorry,there was a problem opening the file");
        }
        return allEmployees;
    }

    private static Employee create(String[] metadata) throws IllegalArgumentException {

        int employeeId = Integer.parseInt(metadata[0].trim());
        int projectId = Integer.parseInt(metadata[1].trim());
        LocalDate dateFrom = convertDate(metadata[2].trim());
        LocalDate dateTo = convertDate(metadata[3].trim());

        return new Employee()
                .withEmployeeId(employeeId)
                .withProjectId(projectId)
                .withDateFrom(dateFrom)
                .withDateTo(dateTo);
    }

    private static LocalDate convertDate(String date) throws IllegalArgumentException {
        if (date.equalsIgnoreCase("null")) {
            return LocalDate.now();
        }

        LocalDate localDate = null;
        for (String dateFormat : POSSIBLE_DATE_FORMATS) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
                localDate = LocalDate.parse(date, format);
            } catch (DateTimeParseException e) {

            }
        }

        if (localDate == null) {
            throw new IllegalArgumentException("Date format not supported " + date);
        }
        return localDate;
    }
}
