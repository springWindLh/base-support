package model;

import javax.persistence.*;

@Entity
@Table(name = "msgcount", catalog = "share_db")
public class MsgCount implements java.io.Serializable {
	private Integer id;
	private Integer userId;
	private Integer count;

	public MsgCount() {
	}

	public MsgCount(Integer userId, Integer count) {
		this.userId = userId;
		this.count = count;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
