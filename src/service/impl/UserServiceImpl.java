package service.impl;

import java.util.List;

import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

	public static void main(String[] args) {
		System.out.println(new UserServiceImpl().findPassword("1234"));
	}

	private static UserDaoImpl userdaoimpl = new UserDaoImpl();

	@Override
	public User Login(String account, String password) {
		// TODO Auto-generated method stub
		User user = null;
		List<User> l = userdaoimpl.selectByEmailOrPhoneAndPassword(account, password);
		if (!l.isEmpty()) {
			user = l.get(0);
		}

		return user;
	}

	@Override
	public boolean isEmailBeenUse(String email) {
		// TODO Auto-generated method stub
		return !userdaoimpl.selectUserList(email).isEmpty();
	}

	@Override
	public boolean isPhoneBeenUse(String phone) {
		// TODO Auto-generated method stub
		return !userdaoimpl.selectByPhone(phone).isEmpty();
	}

	@Override
	public User findPassword(String account) {
		User user = null;
		List<User> l = userdaoimpl.selectUserList(account);
		if (!l.isEmpty()) {
			user = l.get(0);
		}
		if (!userdaoimpl.selectByPhone(account).isEmpty()) {
			l = userdaoimpl.selectByPhone(account);

			user = l.get(0);

		}
		return user;

	}

	@Override
	public void addUser(User user) {
		userdaoimpl.creatUser(user);

	}

}
