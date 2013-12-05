/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Rajiv
 *
 */
public class User {
	@NotEmpty
	private String userName="";
	@NotEmpty
	private String password;
	@NotEmpty
	private String phoneNumber;
	private boolean owner;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the owner
	 */
	public boolean isOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", owner=" + owner
				+ "]";
	}
	

}
