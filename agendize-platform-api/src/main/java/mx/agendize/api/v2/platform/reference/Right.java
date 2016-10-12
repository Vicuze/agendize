package mx.agendize.api.v2.platform.reference;

import java.util.List;

public class Right {
	
	private String name; 
	private String accountStatus; 
	private String emailAddress; 
	private List<Acl> acls; 
	private boolean selfAccount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<Acl> getAcls() {
		return acls;
	}
	public void setAcls(List<Acl> acls) {
		this.acls = acls;
	}
	public boolean isSelfAccount() {
		return selfAccount;
	}
	public void setSelfAccount(boolean selfAccount) {
		this.selfAccount = selfAccount;
	} 

}
