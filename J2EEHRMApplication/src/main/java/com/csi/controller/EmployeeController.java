package com.csi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.lang.model.element.NestingKind;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import com.csi.service.EmployeeServiceImpl;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeeService employeeServiceImpl = new EmployeeServiceImpl();

	String INDEX_PAGE = "/index.jsp";
	String SIGNUP_PAGE = "/signup.jsp";
	String SIGNIN_PAGE = "/signin.jsp";
	String SHOWDATA_PAGE = "/show.jsp";
	String EDITDATA_PAGE = "/edit.jsp";
	
	String LIST_PAGE="/list.jsp";

	public EmployeeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String redirect = "";

		String employeeId = request.getParameter("empid");

		String action = request.getParameter("action");

		if (employeeId != null && action.equals("signup")) {
			// signup

			int empId = Integer.parseInt(employeeId);

			String empName = request.getParameter("empname");
			String empAddress = request.getParameter("empaddress");
			String empGender = request.getParameter("empgender");
			long empContactNumber = Long.valueOf(request.getParameter("empcontactnumber"));
			Date empDobDate = null;

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				empDobDate = simpleDateFormat.parse(request.getParameter("empdob"));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String empEmailId = request.getParameter("empemailid");
			String empPassword = request.getParameter("emppassword");

			Employee employee = new Employee(empId, empName, empAddress, empGender, empContactNumber, empDobDate,
					empEmailId, empPassword);

			employeeServiceImpl.signUp(employee);

			redirect = SIGNIN_PAGE;

			System.out.println("Redirected to SignIn Page");

		} else if (action.equals("signin")) {
			// signin
			
			String empEmailId = request.getParameter("empemailid");
			String empPassword = request.getParameter("emppassword");
			
			if(employeeServiceImpl.signIn(empEmailId, empPassword)) {
				List<Employee> listEmployee = employeeServiceImpl.getAllData();
				request.setAttribute("listEmployee", listEmployee);
			
				redirect=LIST_PAGE;
			}else {
				redirect= SIGNIN_PAGE;
			}
		} else if (action.equals("editform")) {
			// call EDITDATA PAGE
			redirect = EDITDATA_PAGE;

		} else if (action.equals("edit")) {
			// update
			
			
			int empId = Integer.parseInt(request.getParameter("empid"));

			String empName = request.getParameter("empname");
			String empAddress = request.getParameter("empaddress");
			String empGender = request.getParameter("empgender");
			long empContactNumber = Long.valueOf(request.getParameter("empcontactnumber"));
			Date empDobDate = null;

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				empDobDate = simpleDateFormat.parse(request.getParameter("empdob"));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String empEmailId = request.getParameter("empemailid");
			String empPassword = request.getParameter("emppassword");

			Employee employee = new Employee(empId, empName, empAddress, empGender, empContactNumber, empDobDate,
					empEmailId, empPassword);

			employeeServiceImpl.updateData(empId, employee);

			redirect = SHOWDATA_PAGE;

			System.out.println("Redirected to SHOW Page");
			

		} else if (action.equals("delete")) {
			// delete
			
			int empId = Integer.parseInt(request.getParameter("empid"));
			
			employeeServiceImpl.deleteDataById(empId);
			
			redirect= LIST_PAGE;
			
			System.out.println("Redirected to SHOW Page");
		}else if(action.equals("deletealldata")) {
			employeeServiceImpl.deleteAllData();
			redirect=LIST_PAGE;
		}else if(action.equals("sortbyname")) {
			List<Employee> listEmployee = employeeServiceImpl.sortByName();
			request.setAttribute("listEmployee", listEmployee);
		
			redirect=LIST_PAGE;
		}else if(action.equals("list")) {
			List<Employee> listEmployee = employeeServiceImpl.getAllData();
			request.setAttribute("listEmployee", listEmployee);
		
			redirect=LIST_PAGE;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
