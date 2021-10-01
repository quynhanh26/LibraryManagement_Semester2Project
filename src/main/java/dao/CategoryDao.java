package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connect.ConnectDatabase;
import entities.Category;
import model_view.Books_Category;

public class CategoryDao {

	public List<Category> getNameCategory() {

		List<Category> list = new ArrayList<Category>();

		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("select * from CATEGORY");
				ResultSet rs = cs.executeQuery();) {
			while (rs.next()) {
				Category cate = new Category();
				cate.setId(rs.getString("CATEGORY_ID"));
				cate.setName(rs.getString("CATEGORY_NAME"));
				list.add(cate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public boolean check_IDCate(String id_cate) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call selectCategory_WithID(?)}");) {

			cs.setString(1, id_cate);
			boolean exists = false;
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
			 exists = rs.getString("CATEGORY_ID").equals(id_cate);
			}
			rs.close();
			return exists;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean check_CateName(String cateName) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call selectCategory_WithCateName(?)}");) {

			cs.setString(1, cateName);
			ResultSet rs = cs.executeQuery();
			boolean exists = false;
			if(rs.next()) {
				exists = rs.getString("CATEGORY_NAME").equals(cateName);
			}
			rs.close();
			return exists;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void addCategory(Category cate) {
		try (Connection connect = DriverManager.getConnection(ConnectDatabase.getConnectionUrlFromClassPath());
				CallableStatement cs = connect.prepareCall("{call InsertCategory(?,?)}")) {

			cs.setString(1, cate.getId());
			cs.setString(2, cate.getName());
			var rs = cs.executeUpdate();
			if (rs > 0) {
				JOptionPane.showMessageDialog(null, "Insert successful");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Insert fail!!");
		}
	}
	
}
