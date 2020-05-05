package com;

import java.sql.*;

public class Hospital {

	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/healthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			return con;
		} catch (SQLException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	// insert hospital to database
	public String insertHospital(String Hospital_Name, String Hospital_Address, String Hosiptal_PhoneNo,
			String Hospital_City, String Hospital_Email) {

		try {
			Connection con = connect();
			// create a prepared statement
			String query = "insert into hospital(Hospital_ID, Hospital_Name, Hospital_Address, Hospital_PhoneNo,  Hospital_City, Hospital_Email)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Hospital_Name);
			preparedStmt.setString(3, Hospital_Address);
			preparedStmt.setString(4, Hosiptal_PhoneNo);
			preparedStmt.setString(5, Hospital_City);
			preparedStmt.setString(6, Hospital_Email);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newHospitals = readHospitals();
			return newHospitals;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	// search hospitals from database
	public String readHospitals() {
		String output = "";

		try {
			Connection con = connect();

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Hospital_Name</th>" + "<th>Hospital_Address</th>"
					+ "<th>Hosiptal_PhoneNo</th>" + "<th>Hospital_City</th>" + "<th>Hospital_Email</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from hospital";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String Hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));
				String Hospital_Name = rs.getString("Hospital_Name");
				String Hospital_Address = rs.getString("Hospital_Address");
				String Hosiptal_PhoneNo = rs.getString("Hospital_PhoneNo");
				String Hospital_City = rs.getString("Hospital_City");
				String Hospital_Email = rs.getString("Hospital_Email");

				// Add into the html table
				output += "<tr><td><input id='hidHospitalIDUpdate' name='hidHospitalIDUpdate' "
						+ "type='hidden' value='" + Hospital_ID + "'>" + Hospital_Name + "</td>";
				output += "<td>" + Hospital_Address + "</td>";
				output += "<td>" + Hosiptal_PhoneNo + "</td>";
				output += "<td>" + Hospital_City + "</td>";
				output += "<td>" + Hospital_Email + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button'"
						+ "value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button'"
						+ "value='Remove' class='btnRemove btn btn-danger' " + "data-hospitalid='" + Hospital_ID + "'>"
						+ "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		return output;
	}

	// delete hospitals from database
	public String deleteHospital(String Hospital_ID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from hospital where Hospital_ID = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Hospital_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newHospitals = readHospitals();
			output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";
			return output;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	// update patient
	public String updateHospital(String Hospital_ID, String Hospital_Name, String Hospital_Address,
			String Hosiptal_PhoneNo, String Hospital_City, String Hospital_Email) {
		String output = "";

		try {
			Connection con = connect();
			// create a prepared statement
			String query = "UPDATE hospitals SET Hospital_Name=?,  Hospital_Address=?, Hosiptal_PhoneNo=?,  Hospital_City=?, Hospital_Email=? WHERE Hospital_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Hospital_Name);
			preparedStmt.setString(2, Hospital_Address);
			preparedStmt.setString(3, Hosiptal_PhoneNo);
			preparedStmt.setString(4, Hospital_City);
			preparedStmt.setString(5, Hospital_Email);
			preparedStmt.setInt(7, Integer.parseInt(Hospital_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newHospitals = readHospitals();
			output = "{\"status\":\"success\", \"data\": \"" + newHospitals + "\"}";
			return output;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

}
