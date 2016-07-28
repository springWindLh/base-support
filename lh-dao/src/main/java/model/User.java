package model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "share_db")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Attachment headImg;
	private String name;
	private String password;
	private String email;
	private String phone;
	private Timestamp createTime;
	private String signature;
	private Boolean valid;
	private Boolean deleted;
	private Timestamp lastLoginTime;
	private String sex;
	private String type;
	private School school;
	private Set<Message> messages = new HashSet<Message>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name, String password, String email, String phone) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	/** full constructor */
	public User(Attachment headImg, String name, String password, String email, String phone, Timestamp createTime,
			String signature, Boolean valid, Timestamp lastLoginTime, String sex, String type) {
		this.headImg = headImg;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.createTime = createTime;
		this.signature = signature;
		this.valid = valid;
		this.lastLoginTime = lastLoginTime;
		this.sex = sex;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "headImg")
	public Attachment getHeadImg() {
		return headImg;
	}

	public void setHeadImg(Attachment headImg) {
		this.headImg = headImg;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", nullable = false, length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "createTime", length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "signature", length = 1000)
	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "valid")
	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Column(name = "lastLoginTime", length = 0)
	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Column(name = "type", length = 6)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JSONField(serialize = false)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "message_user", catalog = "share_db", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "message_id", nullable = false, updatable = false) })
	@OrderBy(clause = "message_id desc")
	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name = "school_id", nullable = false)
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}