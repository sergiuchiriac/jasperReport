package com.jasper.report.service;

import com.jasper.report.entity.Employee;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface EmployeeService {

  List<Employee> listAll();

  String exportReport(String reportFormat) throws FileNotFoundException, JRException;
}
