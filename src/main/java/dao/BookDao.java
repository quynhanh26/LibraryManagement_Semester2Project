package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import entities.Books;
import model_view.Books_Category;
import model_view.Detail_OverDue;

public class BookDao {

	public List<Books_Category> getListBooks() {

		List<Books_Category> list = new ArrayList<Books_Category>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call selectBooks}");
				ResultSet rs = cs.executeQuery();) {
			while (rs.next()) {
				Books_Category book = new Books_Category();
				book.setNo(rs.getInt("NO"));
				book.setBookId(rs.getString("BOOK_ID"));
				book.setTitle(rs.getString("TITLE"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setCategoryId(rs.getString("ID_CATEGORY"));
				book.setQuantity(rs.getInt("QUANTITY"));
				book.setNameCategory(rs.getString("CATEGORY_NAME"));
				book.setImg(rs.getString("IMG"));
				list.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public Books selectBook_WithID(String id_book) {
		Books book = new Books();
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				ResultSet rs = CreatePrepare.createCScheck_IDBook(connect, id_book).executeQuery();) {
			while (rs.next()) {
				book.setBookId(rs.getString("BOOK_ID"));
				book.setTitle(rs.getString("TITLE"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setCategoryId(rs.getString("ID_CATEGORY"));
				book.setQuantity(rs.getInt("QUANTITY"));
				book.setImg(rs.getString("IMG"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return book;
	}

	public Books selectBook_WithName(String nameBook) {
		Books book = new Books();
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				ResultSet rs = CreatePrepare.createCSselectBookWithName(connect, nameBook).executeQuery();) {
			while (rs.next()) {
				book.setBookId(rs.getString("BOOK_ID"));
				book.setTitle(rs.getString("TITLE"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setCategoryId(rs.getString("ID_CATEGORY"));
				book.setQuantity(rs.getInt("QUANTITY"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return book;
	}

	public static boolean updateBooks(Books book) {
		boolean check = false;
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call UpdateBook(?,?,?,?,?,?)}")) {
			cs.setString(2, book.getTitle());
			cs.setString(3, book.getAuthor());
			cs.setString(4, book.getCategoryId());
			cs.setInt(5, book.getQuantity());
			cs.setString(6, book.getImg());
			cs.setString(1, book.getBookId());
			if (cs.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Update Sucessfull");
				check = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Update fail");
		}
		return check;
	}

	public static boolean updateQuantityBook(int quantity) {
		boolean check;
		try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var ps = connect.prepareStatement("UPDATE BOOKS SET QUANTITY = QUANTITY - ?")) {
			ps.setInt(1, quantity);
			ps.executeUpdate();
			check = true;
		} catch (Exception e) {
			check = false;
		}
		return check;
	}

	public static boolean addBook(Books book) {
		boolean check = false;
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call InsertBook(?,?,?,?,?,?)}")) {
			cs.setString(1, book.getBookId());
			cs.setString(2, book.getTitle());
			cs.setString(3, book.getAuthor());
			cs.setString(4, book.getCategoryId());
			cs.setInt(5, book.getQuantity());
			cs.setString(6, book.getImg());
			if (cs.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Insert successful");
				check = true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return check;
	}

	public boolean check_IDBook(String id_book) {
		boolean exists = false;
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				ResultSet rs = CreatePrepare.createCScheck_IDBook(connect, id_book).executeQuery();) {
			if (rs.next() == true) {
				exists = rs.getString("BOOK_ID").equals(id_book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return exists;
	}

	public void deleteBook(String id_book) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call DeleteBook(?)}");) {

			cs.setString(1, id_book);
			cs.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	


}
