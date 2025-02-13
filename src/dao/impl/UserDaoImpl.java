package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import model.User;
import util.DbConnection;

public class UserDaoImpl implements UserDao {

	public static void main(String[] args) {
		//new UserDaoImpl().creatUser(new User("kk","ww@gmail","123","0912345"));
		
		/*List<User> user = new UserDaoImpl().selectUserList("ww@gmail");
		for (User u : user) {
			System.out.println("姓名："+u.getName()+"\t信箱："+u.getEmail()+"\t密碼："+u.getPassword()+"\t電話："+u.getPhone());
		}*/
		
		//System.out.println(new UserDaoImpl().selectByEmailOrPhoneAndPassword("555","uu3"));
		
		//List<User> user = new UserDaoImpl().selectByPhone("555");
		
		
		//new UserDaoImpl().updateUser(new User("O44O","QQ@123","01444443","QQ@14443"));
		
		//new UserDaoImpl().deleteUser("KK@gmail");

	}

	private static Connection connection = DbConnection.getDb();

	@Override
	public void creatUser(User user) {

		String SQL = "INSERT INTO users(name,email,password,phone) VALUES (?,?,?,?)";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getEmail());
			preparedstatement.setString(3, user.getPassword());
			preparedstatement.setString(4, user.getPhone());
			preparedstatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> selectUserList(String email) {
		String SQL = "SELECT *FROM users WHERE email=?";

		List<User> l = new ArrayList();

		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, email);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				User user = new User();
				user.setId(resultset.getInt("id"));
				user.setName(resultset.getString("name"));
				user.setEmail(resultset.getString("email"));
				user.setPassword(resultset.getString("password"));
				user.setPhone(resultset.getString("phone"));

				l.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}
	
	public List<User> selectByEmailOrPhoneAndPassword(String account,String password){
		String SQL = "SELECT *FROM users WHERE (email=? OR phone=?) AND password=? ";

		List<User> l = new ArrayList();

		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, account);
			preparedstatement.setString(2, account);
			preparedstatement.setString(3, password);
			
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				User user = new User();
				user.setId(resultset.getInt("id"));
				user.setName(resultset.getString("name"));
				user.setEmail(resultset.getString("email"));
				user.setPassword(resultset.getString("password"));
				user.setPhone(resultset.getString("phone"));

				l.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	};
	
	@Override
	public List<User> selectByPhone(String phone) {
		String SQL = "SELECT *FROM users WHERE phone=?";

		List<User> l = new ArrayList();

		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, phone);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				User user = new User();
				user.setId(resultset.getInt("id"));
				user.setName(resultset.getString("name"));
				user.setEmail(resultset.getString("email"));
				user.setPassword(resultset.getString("password"));
				user.setPhone(resultset.getString("phone"));

				l.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}
	

	@Override
	public void updateUser(User user) {
		String SQL = "UPDATE users SET name=?,password=?,phone=? WHERE email=?";

		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getPassword());
			preparedstatement.setString(3, user.getPhone());
			preparedstatement.setString(4, user.getEmail());
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(String email) {
		String SQL = "DELETE FROM users WHERE email=?";

		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1, email);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
