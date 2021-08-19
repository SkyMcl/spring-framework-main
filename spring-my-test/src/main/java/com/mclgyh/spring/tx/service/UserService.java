package com.mclgyh.spring.tx.service;

import com.mclgyh.spring.tx.dao.UserDao;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 15:53 2021/8/16
 * @Modified By:
 **/
public class UserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void check(String name,int userId){
		try {
			System.out.println("-----check start-----");
			userDao.updateName(name,userId);
			userDao.updateAge(userId);

//			for (int i = 0; i < 2; i++) {
//				System.out.println(1 / i);
//			}
		}
		catch (Exception e){

		}
		finally {
			System.out.println("-----check end-----");
		}

	}


}
