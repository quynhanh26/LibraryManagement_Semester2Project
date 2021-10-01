package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import model_view.Book_BookDetail_CallCard;
import model_view.BooksDetail_CallCard;
import model_view.Detail_OverDue;

public class Book_BookDetail_CallCardDao {

	public static boolean payBooks(int quantity, int id, String id_people, String idbook) {
		boolean check = false;
		try (Connection conn = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = conn.prepareCall("{call PayBooks(?,?,?,?)}")) {
			cs.setInt(1, quantity);
			cs.setInt(2, id);
			cs.setString(3, id_people);
			cs.setString(4, idbook);
			cs.executeUpdate();
			if (cs.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, " successful");
				check = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return check;
	}

	public List<Book_BookDetail_CallCard> selectBookHightLight() {

		List<Book_BookDetail_CallCard> list = new ArrayList<Book_BookDetail_CallCard>();
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call bookBorrowedest()}");) {
			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				Book_BookDetail_CallCard book = new Book_BookDetail_CallCard();
				book.setId_book(rs.getString("BOOK_ID"));
				book.setNum(rs.getInt("NUM"));
				book.setTitle(rs.getString("TITLE"));
				book.setAuthor(rs.getString("AUTHOR"));
				list.add(book);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return list;
	}
	
	public List<Book_BookDetail_CallCard> selectBook_Detail_CallCard_Book(int id) {

		List<Book_BookDetail_CallCard> list = new ArrayList<Book_BookDetail_CallCard>();
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call selectBook_Detail_CallCard_Books(?)}");) {

			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				Book_BookDetail_CallCard book = new Book_BookDetail_CallCard();
				book.setNo(rs.getInt("NO"));
				book.setId_book(rs.getString("BOOK_ID"));
				book.setTitle(rs.getString("TITLE"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setId_category(rs.getString("ID_CATEGORY"));
				book.setQuantity(rs.getInt("QUANTITY"));
				book.setQuantity_due(rs.getInt("QUANTITY_DUE"));
				list.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public List<Detail_OverDue> getDetailOverDue(Integer id) {

		List<Detail_OverDue> list = new ArrayList<Detail_OverDue>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				ResultSet rs = CreatePrepare.createCSselectDetailOverDue(connect, id).executeQuery();)
			{
			while (rs.next()) {
				Detail_OverDue book = new Detail_OverDue();
				book.setNo(rs.getInt("NO"));
				book.setBookId(rs.getString("BOOK_ID"));
				book.setTitle(rs.getString("TITLE"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setIdCallCard(rs.getInt("ID_CALLCARD"));
				book.setQuantity(rs.getInt("QUANTITY"));
				book.setQuantityDue(rs.getInt("QUANTITY_DUE"));
				book.setImg(rs.getString("IMG"));
				book.setCategoryId(rs.getString("ID_CATEGORY"));
				book.setDateBorrowed(rs.getDate("DATE_BORROWED").toLocalDate());
				book.setDateDue(rs.getDate("DATE_DUE").toLocalDate());
				book.setTotal(rs.getInt("TOTAL"));
				book.setIdPeople(rs.getString("ID_PEOPLE"));
				list.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public int BookIsBorrowed(LocalDate dayStar, LocalDate dayEnd) {
		
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call BookIsBorrowed(?,?)}");) {
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
	
	public int TotalCardOverdue() {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call TotalCardOverdue()}");
				ResultSet rs = cs.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public int SumTotal_DetailOverDue(int id) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call SumTotal_DetailOverDue(?)}");) {
			
			cs.setInt(1, id);
			cs.executeQuery();
			var rs = cs.executeQuery();
			if (rs.next()) {
				return rs.getInt(2);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
}
