package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import Connect.ConnectDatabase;
import createPrepare.CreatePrepare;
import entities.Account;

public class AccountDao {
	public void changePass(String user, String pass) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call changePass(?,?)}");) {
			cs.setString(1, user);
			cs.setString(2, pass);
			if(cs.executeUpdate() >0) {
				JOptionPane.showMessageDialog(null, "Change pass word successful");
			}else {
				JOptionPane.showMessageDialog(null, "Change pass word fail");
			}
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Update password fail");
		}
	}

	public Account selectAdmin(String user) {
		Account acc = new Account();
		try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var rs = CreatePrepare.createCSselectAdmin(connect, user).executeQuery()) {
			while (rs.next()) {
				acc.setUsername(rs.getString("USERNAME"));
				acc.setPass(rs.getString("PASSWORD"));
				acc.setStatus(rs.getBoolean("STATUS"));
			}
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Login fail");
		}
		return acc;
	}

	public Account selectPass(String pass) {
		Account acc = new Account();
		try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var rs = CreatePrepare.createCSselectPass(connect, pass).executeQuery()) {
			while (rs.next()) {
				acc.setUsername(rs.getString("USERNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}

	public Account selectWithPass(String pass) {
		Account acc = new Account();
		try (var connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				var rs = CreatePrepare.createCSselectPass(connect, pass).executeQuery()) {
			while (rs.next()) {
				acc.setUsername(rs.getString("USERNAME"));
				acc.setPass(rs.getString("PASSWORD"));
				acc.setStatus(rs.getBoolean("STATUS"));
			}
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Login fail");
		}
		return acc;
	}

}
