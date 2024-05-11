package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Product;

public class DAO {
	Connection connect = null; 			//ket noi SQL
	PreparedStatement prepare = null;   //Nem cau lenh sang SQL
	ResultSet resultSet = null; 		//Nhan ket qua tra ve
	
	public List<Product> getAllProduct(){
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM favorite.product;";
		try {
			new DBContext();
			connect = DBContext.getConnection(); //Mo ket noi SQL
			prepare = connect.prepareStatement(query);
			resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				list.add(new Product(
						resultSet.getInt("id"),
						resultSet.getString("productName"),
						resultSet.getString("productCategoryPath"),
						resultSet.getString("img"),
						resultSet.getDouble("unitPrice"),
						resultSet.getInt("cid")
						));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Product> getTop1(){
		List<Product> top1 = new ArrayList<>();
		String top = "SELECT * FROM product ORDER BY id DESC LIMIT 1;";
		try {
			new DBContext();
			connect = DBContext.getConnection();
			prepare = connect.prepareStatement(top);
			resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				top1.add(new Product(
						resultSet.getInt("id"),
						resultSet.getString("productName"),
						resultSet.getString("productCategoryPath"),
						resultSet.getString("img"),
						resultSet.getDouble("unitPrice"),
						resultSet.getInt("cid")
						));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return top1;
	}
}
