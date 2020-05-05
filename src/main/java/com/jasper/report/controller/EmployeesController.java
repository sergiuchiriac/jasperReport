package com.jasper.report.controller;

import com.jasper.report.entity.Employee;
import com.jasper.report.service.EmployeeService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class EmployeesController {

  private EmployeeService employeeService;

  @Autowired
  public void setEmployeeService(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/getEmployees")
  public List<Employee> getEmployees() {
    return employeeService.listAll();
  }

  @GetMapping("/employeeReport/{format}")
  public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
    return employeeService.exportReport(format);
  }
}
