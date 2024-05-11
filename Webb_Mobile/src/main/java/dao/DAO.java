package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import context.DBContext;
import entity.Category;
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
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDouble(5)
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
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDouble(5)
						));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return top1;
	}
	public List<Category> getCategory() {
        List<Category> productList = new ArrayList<>();

        try {
            String sql = "select * from categoryProduct";
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            while(resultSet.next()) {
            	productList.add(new Category(
            			resultSet.getInt(1),
            			resultSet.getString(2)
            			));
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
	public List<Product> getProductByCateID(String id){
		List<Product> list = new ArrayList<>();
		String query = "Select * from `product` where cid = ?;";
		try {
			connect = new DBContext().getConnection();
			prepare = connect.prepareStatement(query);
			prepare.setString(1, id);
			resultSet = prepare.executeQuery();
			
			while(resultSet.next()) {
				list.add(new Product(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDouble(5)
						));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	public Product getProductByID(String id){
		
		String query = "Select * from `product` where id = ?;";
		try {
			connect = new DBContext().getConnection();
			prepare = connect.prepareStatement(query);
			prepare.setString(1, id);
			resultSet = prepare.executeQuery();
			
			while(resultSet.next()) {
				return new Product(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDouble(5)
						);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public static void main(String[] args) {
		DAO dao = new DAO();
		List<Product> listX = dao.getAllProduct();
		List<Category> list = dao.getCategory();
		List<Product> listP = dao.getProductByCateID("1");
		
		for(Product o : listP) {
			System.out.println(o);
		}
	}
}
