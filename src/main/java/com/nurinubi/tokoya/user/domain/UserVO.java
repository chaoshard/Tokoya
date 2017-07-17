package com.nurinubi.tokoya.user.domain;

/**
<!--
 * 
 * @ClassName : SampleVO.java
 * @Description : 
 * @
 * @		修正日			修正者			修正内容
 * @ 	---------		---------		-------------------------------
 * @ 	2017. 7. 13.		山木雄矢			最初作成
 * 
 * @author 山木雄矢
 * @since 2017
 * @version 0.1
 *
 *  Copyright (C) by NuriNubi All right reserved.
 * -->
 */

public class UserVO{
	
	private String userId;
	
	private String userPassword;
	
	private String userName;
	private String userHuri;
	private String userEmail;
	private String userPhone;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
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
	 * @return the userHuri
	 */
	public String getUserHuri() {
		return userHuri;
	}
	/**
	 * @param userHuri the userHuri to set
	 */
	public void setUserHuri(String userHuri) {
		this.userHuri = userHuri;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SampleVO [userId=");
		builder.append(userId);
		builder.append(", userPassword=");
		builder.append(userPassword);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userHuri=");
		builder.append(userHuri);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", userPhone=");
		builder.append(userPhone);
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
