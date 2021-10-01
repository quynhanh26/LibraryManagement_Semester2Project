package dao;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import model_view.BooksDetail_CallCard;

public class BooksDetail_CallCardDao {
	public static List<BooksDetail_CallCard> selectInfoCallcardOfPeople(String idPeople) {
		List<BooksDetail_CallCard> list = new ArrayList<BooksDetail_CallCard>();
			try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
					var rs = CreatePrepare.createPSselectInfoCallcardOfPeople(connect, idPeople).executeQuery()) {
				while (rs.next()) {
					var booksDetail_CallCard = new BooksDetail_CallCard();
					booksDetail_CallCard.setId(rs.getInt("ID_CALLCARD"));
					booksDetail_CallCard.setId_book(rs.getString("ID_BOOK"));
					booksDetail_CallCard.setIdPeople(rs.getString("ID_PEOPLE"));
					booksDetail_CallCard.setQuantity(rs.getInt("QUANTITY"));
					list.add(booksDetail_CallCard);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		return list;
	}
}
