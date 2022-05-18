package com.example.sirmasolutionstask.service.impl;

import com.example.sirmasolutionstask.model.entity.Employee;
import com.example.sirmasolutionstask.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<String[]> lines;
    private final List<Employee> employees;

    @Autowired
    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    @Override
    public String getPairOfEmployees(List<String> inputLines) {
        inputLines.forEach(i -> lines.add(i.split(", ")));

        for (String[] arr : lines) {
            long employeeId = Long.parseLong(arr[0]);
            long projectId = Long.parseLong(arr[1]);

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate dateFrom = LocalDate.parse(arr[2], pattern);

            String date = arr[3];
            LocalDate dateTo = date.equals("NULL") ? LocalDate.now() : LocalDate.parse(date, pattern);

            Employee employee = new Employee(employeeId, projectId, dateFrom, dateTo);

            employees.add(employee);
        }
        int[] employeesWithMostDaysTogether = getEmployeesWithMostDaysTogether(employees);

        if (employeesWithMostDaysTogether != null) {
            int projectId = employeesWithMostDaysTogether[0];
            int employee1 = employeesWithMostDaysTogether[1];
            int employee2 = employeesWithMostDaysTogether[2];
            int days = employeesWithMostDaysTogether[3];

            return String.format("ProjectID: %d EmployeesID: %d %d WorkedDaysTogether: %d",
                    projectId,
                    employee1,
                    employee2,
                    days);
        } else {
            return "no days together";
        }
    }

    private int[] getEmployeesWithMostDaysTogether(List<Employee> employees) {
        final int[] mostDayWorkedTogether = new int[4];
        mostDayWorkedTogether[3] = Integer.MIN_VALUE;

        employees.forEach(e1 -> {
            employees.subList(employees.indexOf(e1) + 1, employees.size())
                    .forEach(e2 -> {
                        LocalDate startDate1 = e1.getDateFrom();
                        LocalDate endDate1 = e1.getDateTo();

                        LocalDate startDate2 = e2.getDateFrom();
                        LocalDate endDate2 = e2.getDateTo();

                        LocalDate startDate = startDate1.isAfter(startDate2) ? startDate1 : startDate2;
                        LocalDate endDate = endDate1.isBefore(endDate2) ? endDate1 : endDate2;

                        if (e1.getProjectId() == e2.getProjectId() && startDate.isBefore(endDate)) {

                            Period period = Period.between(startDate, endDate);
                            int daysTogether = period.getYears() * 365 + period.getMonths() * 30 + period.getDays();

                            if (daysTogether > mostDayWorkedTogether[3]) {
                                mostDayWorkedTogether[0] = (int) e1.getProjectId();
                                mostDayWorkedTogether[1] = (int) e1.getId();
                                mostDayWorkedTogether[2] = (int) e2.getId();
                                mostDayWorkedTogether[3] = daysTogether;
                            }
                        }
                    });
        });

        return mostDayWorkedTogether[3] > 0 ? mostDayWorkedTogether : null;
    }
}
