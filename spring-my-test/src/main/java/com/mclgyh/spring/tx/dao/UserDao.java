package com.mclgyh.spring.tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 15:52 2021/8/16
 * @Modified By:
 **/
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int updateAge(int userId){
		System.out.println("--------------updateAge start---------------");
		try {
			String sql = " update users set age = age-1 where id = " + userId;
			int update = jdbcTemplate.update(sql);
			for (int i = 0; i < 2; i++) {
				System.out.println(1 / i);
			}
			return update;
		}
//		catch (Exception e){
//			return -1;
//		}
		finally{
			System.out.println("--------------updateAge end---------------");
		}

	}

	public int updateName(String name,int userId){
		System.out.println("--------------updateAge start---------------");
		try {
			String sql = " update users set name = '"+name+"' where id = " + userId;
			int update = jdbcTemplate.update(sql);
//			for (int i = 0; i < 2; i++) {
//				System.out.println(1 / i);
//			}
			return update;
		}
//		catch (Exception e){
//			return -1;
//		}
		finally{
			System.out.println("--------------updateAge end---------------");
		}

	}

}
