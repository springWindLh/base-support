package model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message", catalog = "share_db")
public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private User accepter;
	private User sender;
	private String content;
	private Timestamp createTime;
	private Set<User> users = new HashSet<>(0);

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(User accepter, User sender) {
		this.accepter = accepter;
		this.sender = sender;
	}

	/** full constructor */
	public Message(User accepter, User sender, String content,
			Timestamp createTime) {
		this.accepter = accepter;
		this.sender = sender;
		this.content = content;
		this.createTime = createTime;
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
	@JoinColumn(name = "aceept_user_id", nullable = false)
	public User getAccepter() {
		return accepter;
	}

	public void setAccepter(User accepter) {
		this.accepter = accepter;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_user_id", nullable = false)
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createTime", length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@JSONField(serialize = false)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "messages")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}