package com.example.sirmasolutionstask.model.entity;

import java.time.LocalDate;

public class Employee {
    private long id;

    private long projectId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public Employee(long id, long project, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.projectId = project;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getId() {
        return id;
    }

    public Employee setId(long id) {
        this.id = id;
        return this;
    }

    public long getProjectId() {
        return projectId;
    }

    public Employee setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public Employee setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Employee setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", projectId=" + projectId +
               ", dateFrom=" + dateFrom +
               ", dateTo=" + dateTo +
               '}';
    }
}
