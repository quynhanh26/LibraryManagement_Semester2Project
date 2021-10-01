package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import model_view.CallCard_People;

public class People_CallCardDao {
	public List<CallCard_People> getListCallCardFalse() {

		List<CallCard_People> list = new ArrayList<CallCard_People>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call selectCallCardFalse}");
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

	public CallCard_People getInforCallCard(Integer idCall) {
		CallCard_People callCard = new CallCard_People();
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = CreatePrepare.createCSselectInforCallCardPeople(connect, idCall)) {
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				callCard.setCallCardId(rs.getInt("ID"));
				callCard.setPeopleId(rs.getString("ID_PEOPLE"));
				callCard.setPeopleName(rs.getString("NAME"));
				callCard.setPhone(rs.getString("PHONENUMBER"));
				callCard.setEmail(rs.getString("EMAIL"));
				callCard.setClassName(rs.getString("CLASS_NAME"));
				callCard.setDateBorrowed(rs.getDate("DATE_BORROWED").toLocalDate());
				callCard.setDateDue(rs.getDate("DATE_DUE").toLocalDate());
				callCard.setQuantityBrrowed(rs.getInt("QUANTITY_BORROWED"));
				callCard.setStatus(rs.getBoolean("STATUS"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return callCard;
	}

	public List<CallCard_People> getListCheckDate() {

		List<CallCard_People> list = new ArrayList<CallCard_People>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call DueList()}");
				ResultSet rs = cs.executeQuery();) {
			while (rs.next()) {
				CallCard_People callCard = new CallCard_People();
				callCard.setNo(rs.getInt("NO"));
				callCard.setCallCardId(rs.getInt("ID_CALLCARD"));
				callCard.setPeopleId(rs.getString("ID_PEOPLE"));
				callCard.setPeopleName(rs.getString("NAME"));
				callCard.setClassName(rs.getString("CLASS_NAME"));
				callCard.setQuantityBrrowed(rs.getInt("QUANTITY_BORROWED"));
				callCard.setDateDue(rs.getDate("DATE_DUE").toLocalDate());
				list.add(callCard);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public List<CallCard_People> getPayList() {

		List<CallCard_People> list = new ArrayList<CallCard_People>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call PayList()}");
				ResultSet rs = cs.executeQuery();) {
			while (rs.next()) {
				CallCard_People callCard = new CallCard_People();
				callCard.setNo(rs.getInt("NO"));
				callCard.setCallCardId(rs.getInt("ID"));
				callCard.setPeopleId(rs.getString("ID_PEOPLE"));
				callCard.setPeopleName(rs.getString("NAME"));
				callCard.setClassName(rs.getString("CLASS_NAME"));
				callCard.setQuantityBrrowed(rs.getInt("QUANTITY_BORROWED"));
				callCard.setDateDue(rs.getDate("DATE_DUE").toLocalDate());
				callCard.setOverdueFines(rs.getInt("OVERDUE_FINES"));
				list.add(callCard);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	

	public List<CallCard_People> getListCheckDateBorrowed() {

		List<CallCard_People> list = new ArrayList<CallCard_People>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call OverDueList}");
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
}
