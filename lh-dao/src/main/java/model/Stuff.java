package model;

import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Stuff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stuff", catalog = "share_db")
public class Stuff implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Type type;
	private String title;
	private String description;
	private Double price;
	private Boolean completed;
	private Boolean free;
	private Timestamp createTime;
	private Boolean deleted;
	private School school;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Attachment> attachments = new HashSet<Attachment>(0);

	// Constructors

	/** default constructor */
	public Stuff() {
	}

	/** minimal constructor */
	public Stuff(User user, Type type, String title) {
		this.user = user;
		this.type = type;
		this.title = title;
	}

	/** full constructor */
	public Stuff(User user, Type type, String title, String description, Double price, Boolean completed, Boolean free,
			Timestamp createTime, Set<Comment> comments, Set<Attachment> attachments) {
		this.user = user;
		this.type = type;
		this.title = title;
		this.description = description;
		this.price = price;
		this.completed = completed;
		this.free = free;
		this.createTime = createTime;
		this.comments = comments;
		this.attachments = attachments;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name = "title", nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", precision = 6)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "completed")
	public Boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Column(name = "free")
	public Boolean getFree() {
		return this.free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Column(name = "createTime", length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "stuff")
	@OrderBy(clause = "createTime ASC")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "stuff_r_attachment", joinColumns = @JoinColumn(name = "stuff_id") , inverseJoinColumns = @JoinColumn(name = "attachment_id") )
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id", nullable = false)
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}