package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import entities.People;

public class PeopleDao {
	public static boolean checkIDpeople(String id) {
		boolean check = false;
		try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var rs = CreatePrepare.createPScheckIdPeopl(connect, id).executeQuery()) {
			if (rs.isBeforeFirst()) {
				check = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}

	public static People infoPeopleWithID(String id) {
		People people = new People();
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				ResultSet rs = CreatePrepare.createCSselectInfoPeopleWithID(connect, id).executeQuery();) {
			if(rs.next()) {
				System.out.println(rs.getString("ID"));
				people.setId(rs.getString("ID"));
				people.setName(rs.getString("NAME"));
				people.setPhoneNumber(rs.getString("PHONENUMBER"));
				people.setEmail(rs.getString("EMAIL"));
				people.setClassName(rs.getString("CLASS_NAME"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return people;
	}
}
