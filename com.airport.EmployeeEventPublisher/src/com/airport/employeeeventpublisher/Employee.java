package com.airport.employeeeventpublisher;

public class Employee {

	private String name;
	private int age;
	private String contactNo;
	private String address;
	private String nic;
	private double salary;
	
	public Employee() {}

	public Employee(String name, int age, String contactNo, String address, String nic, double salary) {
		this.name = name;
		this.age = age;
		this.contactNo = contactNo;
		this.address = address;
		this.nic = nic;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
}
