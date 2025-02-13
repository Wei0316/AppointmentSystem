package dao;

import java.util.List;

import model.User;

public interface UserDao {
	void creatUser(User user);//新增用戶
	
	List<User> selectUserList(String email);//列出該用戶資料
	List<User> selectByEmailOrPhoneAndPassword(String account,String password);//透過信箱或電話及密碼搜尋
	List<User> selectByPhone(String phone);
	
	void updateUser(User user);//更改用戶資料
	
	void deleteUser(String email);//刪除用戶
}
