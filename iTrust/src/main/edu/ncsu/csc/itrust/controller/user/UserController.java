package edu.ncsu.csc.itrust.controller.user;

import javax.faces.bean.ManagedBean;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;
import edu.ncsu.csc.itrust.model.old.enums.Role;
import edu.ncsu.csc.itrust.model.user.User;
import edu.ncsu.csc.itrust.model.user.UserMySQLConverter;

@ManagedBean(name="user")
public class UserController {
	private DataBean<User> userData;
	private User user;
	public UserController() throws DBException{
		userData = new UserMySQLConverter();
		this.user = null;
	}
	
	public String getUserNameForID(String mid) throws DBException{
//		User user = null;
//		if( mid == null) return "";
//		if(mid.isEmpty()) return "";
//		long id = -1;
//		try{
//			id = Long.parseLong(mid);
//		}
//		catch(NumberFormatException ne){
//			return "";
//		}
//		//if(id<1) return "";
//		user = userData.getByID(id);
//		if(user != null){
//			if(user.getRole().equals(Role.TESTER)){
//				return Long.toString(user.getMID());
//			}
//			else{
//				return user.getLastName().concat(", "+user.getFirstName());
//			}
//			
//		}
//		else{
//			return "";
//		}
		
		getUser(mid);
		if(this.user != null){
			if(this.user.getRole().equals(Role.TESTER)){
				return Long.toString(this.user.getMID());
			}
			else{
				return this.user.getLastName().concat(", "+this.user.getFirstName());
			}
			
		}
		else{
			return "";
		}
	}
	public String getUserRoleForID(String mid) throws DBException{
//		User user = null;
//		if( mid == null) return "";
//		if(mid.isEmpty()) return "";
//		long id = -1;
//		try{
//			id = Long.parseLong(mid);
//		}
//		catch(NumberFormatException ne){
//			return "";
//		}
//		if(id<1) return "";
//		user = userData.getByID(id);
//		return user.getRole().getUserRolesString().toLowerCase();
		
		getUser(mid);
		if(this.user == null)
			return "";
		return this.user.getRole().getUserRolesString().toLowerCase();
	}
	
	public boolean doesUserExistWithID(String mid) throws DBException{
		User user = null;
		if( mid == null) return false;
		long id = -1;
		try{
			id = Long.parseLong(mid);
		}
		catch(NumberFormatException ne){
			return false;
		}
		user = userData.getByID(id);
		if(!(user == null)){
				return true;
		}
		else{
			return false;
		}		
	}
	public void getUser(String mid){
		if( mid == null) {
			this.user = null;
			return;
		}
		if(mid.isEmpty()) {
			this.user = null;
			return;
		}
		long id = -1;
		try{
			id = Long.parseLong(mid);
		} 
		catch(NumberFormatException ne){
			this.user = null;
			return;
		}
		if(id<1) {
			this.user = null;
			return;
		}
		try {
			this.user = userData.getByID(id);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	

}
