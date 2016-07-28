package model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "comment", catalog = "share_db")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Stuff stuff;
	private String content;
	private Timestamp createTime;
	private Set<Reply> replies = new HashSet<Reply>(0);

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, Stuff stuff) {
		this.user = user;
		this.stuff = stuff;
	}

	/** full constructor */
	public Comment(User user, Stuff stuff, String content,
			Timestamp createTime, Set<Reply> replies) {
		this.user = user;
		this.stuff = stuff;
		this.content = content;
		this.createTime = createTime;
		this.replies = replies;
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
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSONField(serialize = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stuff_id", nullable = false)
	public Stuff getStuff() {
		return this.stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comment")
	@OrderBy(clause="createTime ASC")
	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

}