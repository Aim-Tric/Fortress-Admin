/**
 *@Generated by sagacity-quickvo 5.0
 */
package cn.codebro.fortress.system.persistence.po;

import cn.codebro.fortress.common.model.BaseEntity;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

/**
 * @project fortress-admin
 * @author Guo Wentao
 * @version 1.0.0
 * Table: f_user 	
 */
@Entity(tableName="f_user",comment="",pk_constraint="PRIMARY")
public class FUserPO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5801246137747164066L;

	/**
	 * jdbcType:VARCHAR
	 */
	@Id(strategy="generator",generator="org.sagacity.sqltoy.plugins.id.impl.NanoTimeIdGenerator")
	@Column(name="id",comment="主键",length=32L,type=java.sql.Types.VARCHAR,nullable=false)
	protected String id;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="username",comment="账号",length=200L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String username;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="password",comment="密码",length=128L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String password;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="nickname",comment="昵称",length=200L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String nickname;
	/**
	 * jdbcType:INT
	 */
	@Column(name="sex",comment="性别: 0 男; 1 女; 2 未知",length=10L,type=java.sql.Types.INTEGER,nullable=true)
	protected Integer sex;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="phone",comment="手机号",length=11L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String phone;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="email",comment="邮箱地址",length=200L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String email;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="post",comment="岗位",length=32L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String post;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="dept",comment="部门",length=32L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String dept;
	/**
	 * jdbcType:VARCHAR
	 */
	@Column(name="avatar",comment="头像地址",length=500L,type=java.sql.Types.VARCHAR,nullable=true)
	protected String avatar;
	/**
	 * jdbcType:INT
	 */
	@Column(name="status",comment="账号状态: 0 冻结; 1 正常",length=10L,defaultValue="1",type=java.sql.Types.INTEGER,nullable=true)
	protected Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}