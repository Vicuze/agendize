package mx.agendize.api.v2.platform.reference;

public class Acl {
	private String role; 
	private String target;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	} 
}
