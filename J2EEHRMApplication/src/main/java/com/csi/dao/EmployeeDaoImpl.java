package com.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.csi.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	Connection connection = null;

	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub

		try {
			Class.forName("com.mysql.jdbc.Driver");

			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/j2eehrm", "root", "root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void signUp(Employee employee) {
		// TODO Auto-generated method stub

		String insertSQL = "insert into employee(empid, empname, empaddress, empgender, empcontactnumber, empdob, empemailid, emppassword)values(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, employee.getEmpId());
			preparedStatement.setString(2, employee.getEmpName());
			preparedStatement.setString(3, employee.getEmpAddress());
			preparedStatement.setString(4, employee.getEmpGender());
			preparedStatement.setLong(5, employee.getEmpContactNumber());
			java.sql.Date dob = new java.sql.Date(employee.getEmpDOB().getTime());
			preparedStatement.setDate(6, dob);
			preparedStatement.setString(7, employee.getEmpEmailId());
			preparedStatement.setString(8, employee.getEmpPassword());
			preparedStatement.executeUpdate();

			System.out.println("Data Inserted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean signIn(String empEmailId, String empPassword) {
		// TODO Auto-generated method stub

		boolean flag = false;

		String selectSQL = "select * from employee where empemailid=? and emppassword=?";

		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, empEmailId);
			preparedStatement.setString(2, empPassword);

			ResultSet resultSet = preparedStatement.executeQuery();

			flag = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Employee getDataById(int empId) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		String selectSQLBYID = "select * from employee where empid=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQLBYID);

			preparedStatement.setInt(1, empId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				employee.setEmpId(resultSet.getInt(1));
				employee.setEmpName(resultSet.getString(2));
				employee.setEmpAddress(resultSet.getString(3));
				employee.setEmpGender(resultSet.getString(4));
				employee.setEmpContactNumber(resultSet.getLong(5));
				employee.setEmpDOB(resultSet.getDate(6));
				employee.setEmpEmailId(resultSet.getString(7));
				employee.setEmpPassword(resultSet.getString(8));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public List<Employee> getAllData() {
		// TODO Auto-generated method stub

		List<Employee> employees = new ArrayList<Employee>();

		String selectALLSQL = "select * from employee";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectALLSQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setEmpId(resultSet.getInt(1));
				employee.setEmpName(resultSet.getString(2));
				employee.setEmpAddress(resultSet.getString(3));
				employee.setEmpGender(resultSet.getString(4));
				employee.setEmpContactNumber(resultSet.getLong(5));
				employee.setEmpDOB(resultSet.getDate(6));
				employee.setEmpEmailId(resultSet.getString(7));
				employee.setEmpPassword(resultSet.getString(8));

				employees.add(employee);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;//.stream().sorted((e1, e2)->e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());
	}

	@Override
	public void updateData(int empId, Employee employee) {
		// TODO Auto-generated method stub

		String updateSQL = "update employee set empname=?, empaddress=?, empgender=?, empcontactnumber=?, empdob=?, empemailid=?, emppassword=? where empid=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, employee.getEmpName());
			preparedStatement.setString(2, employee.getEmpAddress());
			preparedStatement.setString(3, employee.getEmpGender());
			preparedStatement.setLong(4, employee.getEmpContactNumber());
			java.sql.Date dob = new java.sql.Date(employee.getEmpDOB().getTime());
			preparedStatement.setDate(5, dob);
			preparedStatement.setString(6, employee.getEmpEmailId());
			preparedStatement.setString(7, employee.getEmpPassword());
			preparedStatement.setInt(8, employee.getEmpId());
			preparedStatement.executeUpdate();

			System.out.println("Data Updated Successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteDataById(int empId) {
		// TODO Auto-generated method stub

		String deleteSQLBYID = "delete from employee where empid=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteSQLBYID);

			preparedStatement.setInt(1, empId);

			preparedStatement.executeUpdate();

			System.out.println("Data Deleted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub
		// code goes here
		
		String deleteALLSQL="delete from employee";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteALLSQL);
		
			preparedStatement.executeUpdate();
			
			System.out.println("All Data Deleted from DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Employee> sortByName() {
		// TODO Auto-generated method stub
		return getAllData().stream().sorted((e1, e2)-> e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());
	}

}
