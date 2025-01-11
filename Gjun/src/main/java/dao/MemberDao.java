package dao;

import model.Member;

public interface MemberDao {
	//create
	void addMember(Member m);
	
	//read
	Member findByUsernameAndPassword(String username,String password);
	boolean findByUsername(String username);
	
	//update
	
	
	//delete

}