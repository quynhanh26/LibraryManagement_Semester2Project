package createPrepare;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePrepare {

	public static CallableStatement createCScheck_IDBook(Connection connect, String id_book) throws SQLException {
		CallableStatement cs = connect.prepareCall("{call selectBook_WithID(?)}");;
		cs.setString(1, id_book);
		return cs;
	}

	public static PreparedStatement createPScheckIdPeopl(Connection connect, String id) throws SQLException {
		PreparedStatement ps = connect.prepareStatement("SELECT * FROM PEOPLE WHERE ID = ?");
		ps.setString(1, id);
		return ps;
	}

	public static PreparedStatement createCSselectBookWithName(Connection connect, String namebook) throws SQLException {
		var ps  = connect.prepareStatement("SELECT * FROM BOOKS WHERE TITLE = ?");
		ps.setString(1, namebook);
		return ps;
	}
	public static PreparedStatement createCSselectcheckCallCardExsit(Connection connect, int id) throws SQLException {
		var ps  = connect.prepareStatement("SELECT COUNT(*) SL FROM CALLCARD WHERE ID = ?");
		ps.setInt(1, id);
		return ps;
	}
	
	public static PreparedStatement createCSselectInfoPeopleWithID(Connection connect, String id) throws SQLException {
		var ps  = connect.prepareStatement("SELECT * FROM PEOPLE WHERE ID = ?");
		ps.setString(1, id);
		return ps;
	}

	public static CallableStatement createCSselectDetailOverDue(Connection connect, int id) throws SQLException {
		CallableStatement cs = connect.prepareCall("{call DetailOverDue(?)}");;
		cs.setInt(1, id);
		return cs;
	}
	
	public static CallableStatement createCSselectInforCallCardPeople(Connection connect, int idCall) throws Exception {
		var cs = connect.prepareCall("{call selectCallCard_People(?)}");
		cs.setInt(1, idCall);
		return cs;
	}

	public static CallableStatement createCSselectAdmin(Connection connect, String user) throws Exception {
		var cs = connect.prepareCall("{call selectAdmin(?)}");
		cs.setString(1, user);
		return cs;
	}
	
	public static CallableStatement createCSselectPass(Connection connect, String pass) throws Exception {
		var cs = connect.prepareCall("{call selectPasword(?)}");
		cs.setString(1, pass);
		return cs;
	}
	
	public static PreparedStatement createPSselectTotalBook(Connection connect, String id_people, String sql)
			throws SQLException {
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.setString(1, id_people);
		return ps;
	}
	
	public static PreparedStatement createPSselectInfoCallcardOfPeople(Connection connect, String idPeople)
			throws SQLException {
		PreparedStatement ps = connect.prepareStatement("{call selectInfoCallcardOfPeople(?)}");
		ps.setString(1, idPeople);
		return ps;
	}
}
