package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import com.sun.jdi.Type;

import Connect.ConnectDatabase;
import entities.Books_Detail;
import model_view.BooksDetail_CallCard;

public class Books_DetailDao {
	public static void insertBookDetail(List<Books_Detail> list, int idcallcard) {
		try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var cs = connect
						.prepareCall("INSERT INTO BOOKS_DETAIL(ID_CALLCARD, ID_BOOK, QUANTITY) VALUES (?,?,?)")) {
			connect.setAutoCommit(false);
			for (Books_Detail booksDetail : list) {
				cs.setInt(1, idcallcard);
				cs.setString(2, booksDetail.getId_book());
				cs.setInt(3, booksDetail.getQuantity());
				cs.addBatch();
			}
			cs.executeBatch();
			connect.commit();
			JOptionPane.showMessageDialog(null, "Successful");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean payBooks(int quantity, int id, String id_people, String idbook) {
		boolean check = false;
		try (Connection conn = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = conn.prepareCall("{? = call PayBooks(?,?,?,?)}")) {
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, quantity);
			cs.setInt(3, id);
			cs.setString(4, id_people);
			cs.setString(5, idbook);
			cs.executeUpdate();
			if (cs.getInt(1) == 1 || cs.getInt(1) == 2) {
				JOptionPane.showMessageDialog(null, "Successful");
				check = true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return check;
	}

}
