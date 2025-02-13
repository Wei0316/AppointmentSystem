package service;

import model.User;

public interface UserService {
	
	User Login(String account,String password);
	boolean isEmailBeenUse(String email);//判斷email是否被用過
	boolean isPhoneBeenUse(String phone);//判斷phone是否被用過
	User findPassword(String account);//email/phone找密碼
	
	void addUser(User user);//添加user
	
	
	

}
