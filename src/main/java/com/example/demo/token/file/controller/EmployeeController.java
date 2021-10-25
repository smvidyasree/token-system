package com.example.demo.token.file.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.token.file.Bean.StatusBean;
import com.example.demo.token.file.Bean.UserBean;
import com.example.demo.token.file.Bean.employeeBean;
import com.example.demo.token.file.Dao.EmployeeDao;
import com.example.demo.token.file.JwtToken.JwtlToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeDao emldao;
	@Autowired
	JwtlToken jwtTkn;
	
	@RequestMapping(value="/form",method = RequestMethod.POST)
	public @ResponseBody Object studform(@RequestBody  employeeBean emp, StatusBean sBean, HttpServletRequest request, HttpServletResponse response) {
		
		emldao.saveform(emp);
		sBean.setMessage("suceess");
		sBean.setStatus(true);
		sBean.setData(emp);
		return sBean;
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public @ResponseBody Object loginform(@RequestBody  employeeBean emp, StatusBean sBean, HttpServletRequest request, HttpServletResponse response) {

		String count=emldao.logpage(emp);
		 System.out.println("status_count"+count);
		
		if (count.equals("1"))
		{
		employeeBean emp1 = emldao.fetchEmpl(emp);
		 String token=jwtTkn.generateTokent(emp1.getPassword(),emp1.getFullname());
		 emp1.setToken(token);
		 sBean.setMessage("sucess");
		 
		 sBean.setStatus(true);
		 sBean.setData(emp1);
		 
		}
		else
		{
			 sBean.setMessage("not sucess");	
			 sBean.setStatus(false);
		}
		return sBean ; 
	}
	@RequestMapping(value="/user",method = RequestMethod.POST)
	public @ResponseBody Object user(@RequestBody  UserBean user, StatusBean sBean, @RequestHeader String token, HttpServletRequest request, HttpServletResponse response) {
		Jws<Claims> data = jwtTkn.parseJwt(token);
		Claims body = (data.getBody());
		System.out.println("body data    =======  "+body.get("password"));
		System.out.println("inside body =========="+body);
		String count=emldao.checkadmin(body);
		System.out.println("status_count"+count);
		 if (count.equals("1"))
			{
			 emldao.save(user);
			 sBean.setMessage("useradded");
			 sBean.setStatus(true);
			 sBean.setData(user);
			 
			}
		 else
		 {
			 sBean.setMessage("authentication failed");
		     sBean.setStatus(false);
	}
		 return sBean;
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody Object update(@RequestBody UserBean user,StatusBean sBean,@RequestHeader String token,HttpServletRequest request, HttpServletResponse response) {
		Jws<Claims> data=jwtTkn.parseJwt(token);
		Claims body=(data.getBody());
		String count=emldao.checkadmin(body);
		System.out.println("status_count"+count);
		if(count.equals("1"))
		{
			emldao.update(user);
			sBean.setMessage("updateduser");
			sBean.setStatus(true);
			sBean.setData(user);
			
		}
		else
		{
			 sBean.setMessage("authentication failed");
		     sBean.setStatus(false);
		}
		return sBean;
		
	}
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public @ResponseBody Object delete(@RequestBody UserBean user,StatusBean sBean,@RequestHeader String token,HttpServletRequest request, HttpServletResponse response) {
	Jws<Claims> data=jwtTkn.parseJwt(token);
	Claims body=(data.getBody());
	String count=emldao.checkadmin(body);
	System.out.println("status_count"+count);
	if(count.equals("1"))
	{
		emldao.delete(user);
		sBean.setMessage("sucessfully deleted");
		sBean.setStatus(true);
		
	}
	else
	{
		 sBean.setMessage("authentication failed");
	     sBean.setStatus(false);
	}
	return sBean;
	
	}
	@RequestMapping(value="/view",method = RequestMethod.POST)
	public @ResponseBody Object view(@RequestBody  UserBean user,StatusBean sBean,@RequestHeader String token,HttpServletRequest request, HttpServletResponse response) {
    Jws<Claims> data=jwtTkn.parseJwt(token);
    Claims body=(data.getBody());
    String count=emldao.checkadmin(body);
    System.out.println("status_count"+count);
    if(count.equals("1"))
    {
    	user=emldao.viewuser(user);
    	sBean.setMessage("succesfully listed");
    	sBean.setStatus(true);
    	sBean.setData(user);
    	
    }
    else
    {
    	 sBean.setMessage("authentication failed");
	     sBean.setStatus(false);
    }
	return sBean;
}
	
}
	
