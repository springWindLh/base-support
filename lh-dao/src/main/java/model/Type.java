package model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Type entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "type", catalog = "share_db")
public class Type implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<Stuff> stuffs = new HashSet<Stuff>(0);

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String name) {
		this.name = name;
	}

	/** full constructor */
	public Type(String name, Set<Stuff> stuffs) {
		this.name = name;
		this.stuffs = stuffs;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSONField(serialize = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "type")
	public Set<Stuff> getStuffs() {
		return this.stuffs;
	}

	public void setStuffs(Set<Stuff> stuffs) {
		this.stuffs = stuffs;
	}

}