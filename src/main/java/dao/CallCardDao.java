package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import entities.Books;
import entities.Books_Detail;
import entities.CallCard;
import model_view.BooksDetail_CallCard;
import model_view.Books_Category;
import model_view.CallCard_People;

public class CallCardDao {

	public List<CallCard_People> getListCallCard() {

		List<CallCard_People> list = new ArrayList<CallCard_People>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call selectCallCard}");
				ResultSet rs = cs.executeQuery();) {
			while (rs.next()) {
				CallCard_People callCard = new CallCard_People();
				callCard.setNo(rs.getInt("NO"));
				callCard.setCallCardId(rs.getInt("ID"));
				callCard.setPeopleId(rs.getString("ID_PEOPLE"));
				callCard.setPeopleName(rs.getString("NAME"));
				callCard.setClassName(rs.getString("CLASS_NAME"));
				callCard.setQuantityBrrowed(rs.getInt("QUANTITY_BORROWED"));
				list.add(callCard);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static void insertCallCard(CallCard callcard) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				PreparedStatement ps = connect.prepareStatement(
						"INSERT INTO CALLCARD(ID_PEOPLE, DATE_BORROWED, DATE_DUE, QUANTITY_BORROWED) VALUES(?,?,?,?)")) {
			ps.setString(1, callcard.getPeopleId());
			ps.setDate(2, Date.valueOf(callcard.getDateBorrowed()));
			ps.setDate(3, Date.valueOf(callcard.getDateDue()));
			ps.setInt(4, callcard.getQuantityBrrowed());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Integer idmax() {
		int idmax = 0;
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				Statement cs = connect.createStatement()) {
			var rs = cs.executeQuery("SELECT MAX(ID) IDMAX FROM CALLCARD");
			while (rs.next()) {
				idmax = rs.getInt("IDMAX");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return idmax;
	}

	// check callcard exsist
	public static boolean checkCallCardExsist(int id) {
		boolean check = false;
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				ResultSet rs = CreatePrepare.createCSselectcheckCallCardExsit(connect, id).executeQuery()) {
			while (rs.next()) {
				check = rs.getInt("SL") > 0 ? true : false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}

	public void RemoveCallCard(int bookCallCard) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call RemoveCallCard(?)}");) {
			cs.setInt(1, bookCallCard);
			cs.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public int TotalOfGuests(LocalDate dayStar, LocalDate dayEnd) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call TotalOfGuests(?,?)}");) {
			cs.setDate(1, Date.valueOf(dayStar));
			cs.setDate(2, Date.valueOf(dayEnd));
			var rs = cs.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public int TotalCallCard(LocalDate dayStar, LocalDate dayEnd) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call TotalCallCard(?,?)}");) {
			cs.setDate(1, Date.valueOf(dayStar));
			cs.setDate(2, Date.valueOf(dayEnd));
			var rs = cs.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public int TotalGuestsBorrowing(LocalDate dayStar, LocalDate dayEnd) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call TotalGuestsBorrowing(?,?)}");) {
			cs.setDate(1, Date.valueOf(dayStar));
			cs.setDate(2, Date.valueOf(dayEnd));
			var rs = cs.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public static boolean selectTotalBook(String id_people, int quantity) {
		boolean check = false;
		String sql = "SELECT  QUANTITY, BOOKS_DETAIL.QUANTITY_DUE  FROM CALLCARD JOIN BOOKS_DETAIL ON ID = ID_CALlCARD WHERE ID_PEOPLE = ? AND (QUANTITY > BOOKS_DETAIL.QUANTITY_DUE)";
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var rs = CreatePrepare.createPSselectTotalBook(connect, id_people, sql).executeQuery();) {
			if (rs.next()) {
				if (rs.getInt("QUANTITY") >= rs.getInt("QUANTITY_DUE") + quantity) {
					check = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return check;
	}

}
