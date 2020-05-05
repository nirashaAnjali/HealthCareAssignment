package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/hospitals")
public class HospitalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Hospital hospital = new Hospital();

	public HospitalsServlet() {
		super();
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");// setting the content type
		PrintWriter pw = res.getWriter();// get the stream to write the data

		// writing html in the stream
		pw.println("<html><body>");
		pw.println("Welcome to hospital servlet");
		pw.println("</body></html>");

		pw.close();// closing the stream
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		String output = hospital.insertHospital(request.getParameter("Hospital_Name"),
				request.getParameter("Hospital_Address"), request.getParameter("Hosiptal_PhoneNo"),
				request.getParameter("Hospital_City"), request.getParameter("Hospital_Email"));
		PrintWriter pw = res.getWriter();
		pw.println("<html><body>");
		pw.println(output);
		pw.println("</body></html>");

		pw.close();
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}

		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NullPointerException {
		Map paras = getParasMap(request);
		String output = hospital.updateHospital(paras.get("hidHospitalIDSave").toString(),
				paras.get("Hospital_Name").toString(), paras.get("Hospital_Address").toString(),
				paras.get("Hosiptal_PhoneNo").toString(), paras.get("Hospital_City").toString(),
				paras.get("Hospital_Email").toString());
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = hospital.deleteHospital(paras.get("Hospital_ID").toString());
		response.getWriter().write(output);
	}
}
