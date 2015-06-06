package zttc.itat.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private String username;
	private String nickname;
	private String password;
	private String email;
	
	//构造函数没有返回类型
	public User(){
		
	}
	@NotEmpty(message="用户名不能为空")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Size(min=1, max=10, message="长度1-10")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Email(message="格式不正确")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User(String username, String nickname, String password, String email){
		super();
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
	}
}
