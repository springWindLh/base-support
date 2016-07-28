package model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reply", catalog = "share_db")
public class Reply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Comment comment;
	private User author;
	private User replyer;
	private String content;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Comment comment, User author, User replyer) {
		this.comment = comment;
		this.author = author;
		this.replyer = replyer;
	}

	/** full constructor */
	public Reply(Comment comment, User author, User replyer,
			String content, Timestamp createTime) {
		this.comment = comment;
		this.author = author;
		this.replyer = replyer;
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

	@JSONField(serialize=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "replyer_id", nullable = false)
	public User getReplyer() {
		return replyer;
	}

	public void setReplyer(User replyer) {
		this.replyer = replyer;
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

}