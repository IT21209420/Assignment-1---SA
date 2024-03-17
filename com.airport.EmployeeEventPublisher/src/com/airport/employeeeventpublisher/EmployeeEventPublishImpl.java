package com.airport.employeeeventpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.airport.databasePublisher.Database;
import com.airport.databasePublisher.DatabaseImpl;


public class EmployeeEventPublishImpl implements EmployeeEventPublish{

	private Statement statement = null;
	private ResultSet resultset;
	private String sqlQuery;
	private Database database = new DatabaseImpl();
	private Connection connection = database.getDbConnection();
	
	Scanner scanner = new Scanner(System.in);
	@Override
	public void createEmployee() {
	    Employee employee = new Employee();
	    
	    ArrayList<Employee> employees = new ArrayList<>();
	    
	    System.out.print("\nEnter Employee Name : ");
	    String name = scanner.nextLine().trim();
	    if (name.isEmpty()) {
	        System.out.println("Employee name cannot be empty. Aborting...");
	        return;
	    }
	    employee.setName(name);
	    
	    System.out.print("\nEnter Employee Age : ");
	    int age;
	    try {
	        age = scanner.nextInt();
	        scanner.nextLine(); 
	        if (age <= 0) {
	            System.out.println("Invalid age. Age must be a positive integer. Aborting...");
	            return;
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Invalid age format. Age must be a positive integer. Aborting...");
	        scanner.nextLine(); 
	        return;
	    }
	    employee.setAge(age);
	    
	    System.out.print("\nEnter Employee Contact No : ");
	    String contactNo = scanner.nextLine().trim();
	    if (contactNo.isEmpty()) {
	        System.out.println("Employee contact number cannot be empty. Aborting...");
	        return;
	    }
	    employee.setContactNo(contactNo);
	    
	    System.out.print("\nEnter Employee Address : ");
	    String address = scanner.nextLine().trim();
	    if (address.isEmpty()) {
	        System.out.println("Employee address cannot be empty. Aborting...");
	        return;
	    }
	    employee.setAddress(address);
	    
	    System.out.print("\nEnter Employee NIC : ");
	    String nic = scanner.nextLine().trim();
	    if (nic.isEmpty()) {
	        System.out.println("Employee NIC cannot be empty. Aborting...");
	        return;
	    }
	    employee.setNic(nic);
	    
	    System.out.print("\nEnter Employee Basic Salary : ");
	    double salary;
	    try {
	        salary = scanner.nextDouble();
	        if (salary < 0) {
	            System.out.println("Invalid salary. Salary cannot be negative. Aborting...");
	            return;
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Invalid salary format. Salary must be a number. Aborting...");
	        scanner.nextLine(); 
	        return;
	    }
	    employee.setSalary(salary);
	    
	    employees.add(employee);
	    
	    System.out.println("\n================================================= Employee Details ==================================================");
	    System.out.println("Name\t\t\tAge\tContact No\tAddress\t\tNIC\t\tSalary");
	    for (Employee emp : employees) {
	        System.out.println(emp.getName()+"\t\t"+emp.getAge()+"\t"+emp.getContactNo()+"\t"+emp.getAddress()+"\t"+emp.getNic()+"\t"+emp.getSalary());
	    }
	    
	    String sqlQuery = "INSERT INTO employee(name, age, contactNo, address, nic, salary) "
	            + "VALUES('" + employee.getName() + "','" + employee.getAge() + "','" + employee.getContactNo() + "','" + employee.getAddress() + "','" + employee.getNic() + "','" + employee.getSalary() + "')";
	    
	    try {
	        statement = connection.createStatement();
	        statement.executeUpdate(sqlQuery);
	        System.out.println("\nEmployee created successfully...");
	    } catch (SQLException e) {
	        System.out.println("Employee Creation Failed...");
	        System.out.println(e.getMessage());
	    }
	}


	@Override
	public void getEmployee() {
		
		String nic;
		
		System.out.print("\nEnter Employee NIC : ");
		nic = scanner.next();
		
		sqlQuery = "SELECT * FROM employee WHERE nic = '"+nic+"'";
		
		System.out.println("\n================================================= Employee Details ==================================================");
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQuery);
			System.out.println("\n\tName\t\t  Age\t\tContact No\t\tAddress\t\t\tNIC\t\tBasic Salary");
			
			while (resultset.next()) {  
				System.out.printf("%20s\t%5d\t%18s\t%18s\t%15s\t%20.2f", resultset.getString("name"),resultset.getInt("age"),resultset.getString("contactno"),resultset.getString("address"),resultset.getString("nic"),resultset.getDouble("salary"));    	
			}	
			
		} catch (Exception e) {
			System.out.println("Employee could not find...");
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void getAllEmployeeDetails() {
		
		sqlQuery = "SELECT * FROM employee";
		System.out.println("\n================================================= Employee Details ==================================================");
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQuery);
			System.out.println("\n\tName\t\t  Age\t\tContact No\t\tAddress\t\t\tNIC\t\tBasic Salary");
			
			while (resultset.next()) {  
				System.out.printf("%20s\t%5d\t%18s\t%18s\t%15s\t%20.2f", resultset.getString("name"),resultset.getInt("age"),resultset.getString("contactno"),resultset.getString("address"),resultset.getString("nic"),resultset.getDouble("salary"));    	
				System.out.print("\n");
			}	
		} catch (Exception e) {
			System.out.println("Error while looking for employee data");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteEmployee() {
		
		System.out.print("Enter Employee NIC : ");
		String nic = scanner.next();
		
		sqlQuery = "DELETE FROM employee WHERE nic = '"+nic+"'";	
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlQuery);
			System.out.println("\nEmployee with NIC "+nic+" delete successfully.");
		} catch (Exception e) {
			System.out.println("Could not delete employee from the database...");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void calculateTotalSalary() {
		
		String name = null;
		double salary = 0, totSalary = 0;
		
		System.out.print("\nPlease Enter NIC of the relevant employee : ");
		String nic = scanner.next();
		
		System.out.print("\nPlease enter total OT hours for the month : ");
		int otHours = scanner.nextInt();
		
		System.out.print("\nPlease enter the OT rate for an hour : ");
		double otRate = scanner.nextDouble();
		
		sqlQuery = "SELECT name,salary FROM employee WHERE nic = '"+nic+"'";
		
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQuery);
			
			while (resultset.next()) {  
				name = resultset.getString("name");
				salary = resultset.getDouble("salary");
			}
			
			totSalary = salary + otHours * otRate;
			
			System.out.println("\nEmployee Name : " +name);
			System.out.println("Basic Salary : " +salary);
			System.out.println("OT Allowance : " +otHours * otRate);
			System.out.println("Total Salary for the month : " +totSalary);
			
		} catch (Exception e) {
			System.out.println("Employee could not find...");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void getAvailableEmployeeLeaves() {
		
		String name = null;
		System.out.print("\nEnter NIC of the employee : ");
		String nic = scanner.next();
		
		sqlQuery = "SELECT name,salary FROM employee WHERE nic = '"+nic+"'";
		
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQuery);
			
			while (resultset.next()) {  
				name = resultset.getString("name");
			}
			
			System.out.println("\nEmployee Name : " +name);
			System.out.println("\n======== Total Available leaves for the year 2024 ========");
			System.out.println("Casual Leaves\t:\t10");
			System.out.println("Medical Leaves\t:\t12");
			System.out.println("Annual Leaves\t:\t14");
			
		} catch (Exception e) {
			System.out.println("Employee could not find...");
			System.out.println(e.getMessage());
		}
		
	}
	
}
