package com.jasper.report.service;

import com.jasper.report.dao.EmployeeRepository;
import com.jasper.report.entity.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> listAll() {
    List<Employee> employees = new ArrayList<>();
    employeeRepository.findAll().forEach(employees::add);
    return employees;
  }

  @Override
  public String exportReport(final String reportFormat) throws FileNotFoundException, JRException {
    String path = "C:\\Users\\Armis\\Desktop\\JasperReports";
    String file_path = "C:\\workspace\\JReport\\src\\main\\resources\\reports_template";
    List<Employee> employees = new ArrayList<>();
    employeeRepository.findAll().forEach(employees::add);

    //load file and compile it
    File file = ResourceUtils.getFile(file_path + "\\employee.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("createdBy", "Generated Report");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

    if (reportFormat.equals("html")) {
      JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
    }

    if (reportFormat.equals("pdf")) {
      JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
    }

    return "Report generated in path " + path;
  }

}
