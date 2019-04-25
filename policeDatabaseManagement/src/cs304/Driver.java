package cs304;

import java.sql.*;
import java.text.DateFormat;

public class Driver {

	public static String connectionURL = "jdbc:oracle:thin:@localhost:1522:ug";
	public static String userName = "ora_k0b9";
	public static String password = "a52043122";
	
	private static Driver driver;
	private Connection con;

	private Driver() {
		connect();
	}

	public static Driver getDriver() {
		if (driver == null) {
			driver = new Driver();
		}
		return driver;
		
	}
	
	public void connect() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			this.con = DriverManager.getConnection(connectionURL, userName, password);
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getTest() {
		ResultSet rs = null;
		String query = "SELECT * FROM Record";
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/* 
	 * Search and find queries
	 */

	//Selection and projection query
	public ResultSet getRecord(String startDate, String endDate) {
		ResultSet rs = null;
		String query = "SELECT * FROM record";
		boolean where = false;
		if(!startDate.equals("")) {
			where = true;
			query += " where case_date >= '" + startDate + "'";
			if(!endDate.equals("")) {
				query += " and case_date <= '" + endDate + "'";
			}
		}
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getRecord(String id) {
		ResultSet rs = null;
		String query = "SELECT * FROM Record where Record.ID = '" + id + "'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	
	
	//Join query
	public ResultSet getRecordFilter(String startDate, String endDate, boolean homicide, boolean arson, boolean assault, boolean burglary,
											boolean publicS, boolean tax, boolean bribery, boolean extortion, boolean falseS, boolean theft) {
		ResultSet rs = null;
		boolean where = false;
		boolean crt = false;
		String query = "";
		if(homicide || arson || assault || burglary || publicS || tax || bribery || extortion || falseS || theft)
			crt = true;
		if(crt)
			query = "SELECT * FROM record INNER JOIN criminalRecordType crt ON crt.recordID = record.id";
		else
			query = "SELECT * FROM record";
		
		if(homicide) {
			if(!where) {
				query += " WHERE";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Homicide'";
		}
		if(arson) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Arson'";
		}
		if(assault) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Assault'";
		}
		if(burglary) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Burglary'";
		}
		if(publicS) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Public indecency'";
		}
		if(tax) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Tax Evasion'";
		}
		if(bribery) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Bribery'";
		}
		if(extortion) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Extortion'";
		}
		if(falseS) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'False Pretenses'";
		}
		if(theft) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " crt.typeName = 'Theft'";
		}
		if(!startDate.equals("")) {
			if(crt) {
				query = "SELECT * FROM (" + query + ")";
			}
			query += " WHERE";
			query += " case_date >= '" + startDate + "'";
			if(!endDate.equals("")) {
				query += " and case_date <= '" + endDate + "'";
			}
		} else if(!endDate.equals("")) {
			if(crt) {
				query = "SELECT * FROM (" + query + ")";
			}
			query += " WHERE case_date <= '" + endDate + "'";
			
		}

		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getAllButVictims(String recordID) {
		ResultSet rs = null;
		String query = "select r.description, r.case_date, c.courtid, c.judge, c.description, c.result, c.hearing, crt.typename, ch.officerID "
				+ "from record r, court c, criminalRecordType crt, charge ch "
				+ "where r.id=c.recordID and r.id=crt.recordID and r.id= '" + recordID + "'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;	
	}
	public ResultSet getVictim(String recordID) {
		ResultSet rs = null;
		String query = "select p.age, p.name, p.address, p.phoneNumber, p.sin from people p, PeopleInvolved, Victim "
				+ "where p.sin = PeopleInvolved.sin and victim.sin=p.sin and PeopleInvolved.recordid='" + recordID + "'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;	
	}

	public ResultSet getSuspect(String recordID) {
		ResultSet rs = null;
		String query = "select p.age, p.name, p.address, p.phoneNumber, p.sin from people p, PeopleInvolved, Suspect "
				+ "where p.sin = PeopleInvolved.sin and suspect.sin=p.sin and PeopleInvolved.recordid='" + recordID + "'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;	
	}
	
	//Division query
	public ResultSet getCourtNumber() {
		ResultSet rs = null;
		String query = "SELECT * FROM Record WHERE EXISTS(SELECT recordID FROM court WHERE court.recordID = record.ID)";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	
	//Aggregation query
	public ResultSet getTotalCount(boolean homicide, boolean arson, boolean assault, boolean burglary,
			boolean publicS, boolean tax, boolean bribery, boolean extortion, boolean falseS, boolean theft) {
		ResultSet rs = null;
		boolean where = false;
		String query = "SELECT COUNT(RecordID) AS total_count FROM CriminalRecordType";

		if(homicide) {
			if(!where) {
				query += " WHERE";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Homicide'";
		}
		if(arson) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Arson'";
		}
		if(assault) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Assault'";
		}
		if(burglary) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Burglary'";
		}
		if(publicS) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Public indecency'";
		}
		if(tax) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Tax Evasion'";
		}
		if(bribery) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Bribery'";
		}
		if(extortion) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Extortion'";
		}
		if(falseS) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'False Pretenses'";
		}
		if(theft) {
			if(!where) {
				query += " where";
				where = true;
			} else {
				query += " OR";
			}
			query += " typeName = 'Theft'";
		}
		
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}	
	
	//Nested aggregation with group-by
	public ResultSet getAverage() {
		ResultSet rs = null;
		String query = "select avg(Age) as avg_age, TypeName from People p, PeopleInvolved pi, "
						+ "CriminalRecordType crt where p.SIN = pi.Sin and pi.recordID = crt.RecordID group by TypeName";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	public ResultSet getMin() {
		ResultSet rs = null;
		String query = "select * from (select avg(Age) as age, TypeName from People p, PeopleInvolved pi, CriminalRecordType "
				+ "crt where p.SIN = pi.Sin and pi.recordID = crt.RecordID group by TypeName) temp where temp.age = (select min(avg_age) "
				+ "from (select avg(Age) as avg_age, TypeName from People p, PeopleInvolved pi, CriminalRecordType crt where p.SIN = pi.Sin "
				+ "and pi.recordID = crt.RecordID group by TypeName))";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	public ResultSet getMax() {
		ResultSet rs = null;
		String query = "select * from (select avg(Age) as age, TypeName from People p, PeopleInvolved pi, CriminalRecordType "
				+ "crt where p.SIN = pi.Sin and pi.recordID = crt.RecordID group by TypeName) temp where temp.age = (select max(avg_age) "
				+ "from (select avg(Age) as avg_age, TypeName from People p, PeopleInvolved pi, CriminalRecordType crt where p.SIN = pi.Sin "
				+ "and pi.recordID = crt.RecordID group by TypeName))";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	public ResultSet getOfficer(String officerID) {
		ResultSet rs = null;
		String query = "select * from officer where id = '" + officerID + "'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	
	}

	

	/*
	 * update & insert queries
	 */
	//Update with check constraint. Include the constraint in the code, but do it in the user interface as well
	public ResultSet updateRecord(String id, String typeName, String description, String case_date) {
		ResultSet rs = null;
		String query = "UPDATE record SET Description = '" + description.trim() + "', case_date = '" + case_date + "' where id = '" + id + "'";
		System.out.println(query);
		String query2 = "UPDATE CriminalRecordType SET typeName = '" + typeName + "' where RecordID = '" + id + "'";
		System.out.println(query2);
		try {
			rs = this.con.createStatement().executeQuery(query);
			rs = this.con.createStatement().executeQuery(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}
	

	public void insertCourt(int trialID, String Judge, String outcome, String date, String description) {
		
	}

	public void insertSuspect(String name, String address, String phone, String SIN) {
		
	}
	public void insertVictim(String name, String address, String phone, String SIN) {
		
	}
	public void insertRecord(String recordID, String description, String type, String date, String officerID) {
		
	}
	
	
	
	
	/*
	 * delete queries
	 */
	//Delete without cascades - Case 1
	public ResultSet deleteRecord(String recordID) {
		ResultSet rs = null;
		String query = "delete from Record where ID = '" + recordID + "'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//Delete with cascades - Case 2
	public ResultSet deleteTrial(String recordID) {
		ResultSet rs = null;
		String query = "delete from court where recordID = '" + recordID +"'";
		System.out.println(query);
		try {
			rs = this.con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	
	
	public static void main(String args[]) {
		Driver currDriver = getDriver();
		
		ResultSet rs = currDriver.getTest();
		//ResultSet rs = currDriver.getRecord("2017-06-30", "2017-08-30", true, true, true, false);
		
		try {
			while(rs.next()) {
				System.out.println("test :( ");
				System.out.println(rs.getString("ID"));
				System.out.println(rs.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
