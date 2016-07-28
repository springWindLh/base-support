package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "school", catalog = "share_db")
public class School implements Serializable {
	private Integer schoolid;
	private String schoolname;
	private String province;
	private String schooltype;
	private String schoolproperty;
	private Boolean edudirctly;
	private Boolean f985;
	private Boolean f211;
	private String level;
	private String membership;
	private String schoolnature;
	private String shoufei;
	private String jianjie;
	private String schoolcode;
	private Integer ranking;
	private String guanwang;
	private String oldname;
	private List<User> users;

	public School(Integer schoolid, String schoolname, String province, String schooltype, String schoolproperty,
			Boolean edudirctyly, Boolean f985, Boolean f211, String level, String membership, String schoolnature,
			String shoufei, String jianjie, String schoolcode, Integer ranking, String guanwang, String oldname) {
		this.schoolid = schoolid;
		this.schoolname = schoolname;
		this.province = province;
		this.schooltype = schooltype;
		this.schoolproperty = schoolproperty;
		this.edudirctly = edudirctyly;
		this.f985 = f985;
		this.f211 = f211;
		this.level = level;
		this.membership = membership;
		this.schoolnature = schoolnature;
		this.shoufei = shoufei;
		this.jianjie = jianjie;
		this.schoolcode = schoolcode;
		this.ranking = ranking;
		this.guanwang = guanwang;
		this.oldname = oldname;
	}

	public School() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	@Column(name = "schoolname", length = 20)
	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	@Column(name = "province", length = 5)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "schooltype", length = 10)
	public String getSchooltype() {
		return schooltype;
	}

	public void setSchooltype(String schooltype) {
		this.schooltype = schooltype;
	}

	@Column(name = "schoolproperty", length = 10)
	public String getSchoolproperty() {
		return schoolproperty;
	}

	public void setSchoolproperty(String schoolproperty) {
		this.schoolproperty = schoolproperty;
	}

	@Column(name = "edudirctly", length = 10)
	public Boolean getEdudirctly() {
		return edudirctly;
	}

	public void setEdudirctly(Boolean edudirctly) {
		this.edudirctly = edudirctly;
	}

	@Column(name = "f985")
	public Boolean getF985() {
		return f985;
	}

	public void setF985(Boolean f985) {
		this.f985 = f985;
	}

	@Column(name = "f211")
	public Boolean getF211() {
		return f211;
	}

	public void setF211(Boolean f211) {
		this.f211 = f211;
	}

	@Column(name = "level", length = 5)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "membership", length = 30)
	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	@Column(name = "schoolnature", length = 5)
	public String getSchoolnature() {
		return schoolnature;
	}

	public void setSchoolnature(String schoolnature) {
		this.schoolnature = schoolnature;
	}

	@Column(name = "shoufei", length = 500)
	public String getShoufei() {
		return shoufei;
	}

	public void setShoufei(String shoufei) {
		this.shoufei = shoufei;
	}

	@Column(name = "jianjie", length = 800)
	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}

	@Column(name = "schoolcode", length = 10)
	public String getSchoolcode() {
		return schoolcode;
	}

	public void setSchoolcode(String schoolcode) {
		this.schoolcode = schoolcode;
	}

	@Column(name = "ranking")
	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	@Column(name = "guanwang", length = 50)
	public String getGuanwang() {
		return guanwang;
	}

	public void setGuanwang(String guanwang) {
		this.guanwang = guanwang;
	}

	@Column(name = "oldname", length = 50)
	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "school",cascade = CascadeType.MERGE)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
