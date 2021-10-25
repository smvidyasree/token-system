package com.example.demo.token.file.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.token.file.Bean.UserBean;
import com.example.demo.token.file.Bean.employeeBean;
import com.mysql.cj.protocol.Resultset;

import io.jsonwebtoken.Claims;


@Repository
public class EmployeeDao {
	@Autowired
	 JdbcTemplate Template;
	
	public  void saveform(employeeBean emp) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO `employee_tables`(`username`, `fullname`, `password`, `emailid`, `degination`) VALUES ('"+emp.getUsername()+"','"+emp.getFullname()+"','"+emp.getPassword()+"','"+emp.getEmailid()+"','"+emp.getDesgination()+"')";
		Template.execute(sql);
	}
	public String logpage(employeeBean emp){
		System.out.println("select count(*)as status_count from employee_tables WHERE username='"+emp.getUsername()+"'AND password='"+emp.getPassword()+"'");
		return Template.queryForObject("select count(*)as status_count from employee_tables WHERE username='"+emp.getUsername()+"'AND password='"+emp.getPassword()+"'",new RowMapper<String>() {
			@Override
		
					public String mapRow(ResultSet rs, int row) throws SQLException {
						String count = rs.getString("status_count");
						System.out.println("count"+emp.getStatus_count());
						return count;
					}
				});
}
	public employeeBean fetchEmpl(employeeBean emp) {
		String sql="SELECT * FROM `employee_tables` WHERE username='"+emp.getUsername()+"'AND password='"+emp.getPassword()+"'";
		return Template.queryForObject(sql, new RowMapper<employeeBean>() {

			@Override
			public employeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				employeeBean ebean = new employeeBean();
				ebean.setUsername(rs.getString("username"));
				ebean.setFullname(rs.getString("fullname"));
				ebean.setPassword(rs.getString("password"));
				ebean.setEmailid(rs.getString("emailid"));
				ebean.setDesgination(rs.getString("degination"));
				
				return ebean;
			}
			
			
		});
		
	}
	public String checkadmin(Claims body) {
		System.out.println("select count(*)as status_count from employee_tables WHERE password='"+body.get("password")+"'AND fullname='"+body.get("username")+"'");
		return Template.queryForObject("select count(*)as status_count from employee_tables WHERE password='"+body.get("password")+"'AND fullname='"+body.get("username")+"'",new RowMapper<String>() {
			@Override
		
					public String mapRow(ResultSet rs, int row) throws SQLException {
						String count = rs.getString("status_count");
						return count;
					}
				});
	}
	public void save(UserBean user) {
		String sql="INSERT INTO `user_table`(`name`, `fullname`, `emailid`, `desgination`) VALUES ('"+user.getName()+"','"+user.getFullname()+"','"+user.getEmailid()+"','"+user.getDesgination()+"')";
		Template.execute(sql);
	}
	public void update(UserBean user) {
		String Sql="UPDATE `user_table` SET `name`='"+user.getName()+"',`fullname`='"+user.getFullname()+"',`emailid`='"+user.getEmailid()+"',`desgination`='"+user.getDesgination()+"'";
		Template.execute(Sql);
	}
	public void delete(UserBean user) {
     String sql="DELETE FROM `user_table`WHERE id='"+user.getId()+"'";
     Template.execute(sql);
	}
	public UserBean viewuser(UserBean user) {
		String sql="SELECT * FROM `user_table` WHERE id='"+user.getId()+"'";
		return Template.queryForObject(sql, new RowMapper<UserBean>(){
			@Override
		public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBean user1 = new UserBean();
			 user1.setId(rs.getInt("id"));
			 user1.setName(rs.getString("name"));
			 user1.setFullname(rs.getString("fullname"));
			 user1.setEmailid(rs.getString("emailid"));
			 user1.setDesgination(rs.getString("desgination"));
			
			return user1;
		}
		});
		
	}
}
	

