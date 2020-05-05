package com.jasper.report.entity;

import javax.persistence.*;


@Entity
public class Employee{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String designation;
  private String salary;
  private String doj;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(final String designation) {
    this.designation = designation;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(final String salary) {
    this.salary = salary;
  }

  public String getDoj() {
    return doj;
  }

  public void setDoj(final String doj) {
    this.doj = doj;
  }
}
