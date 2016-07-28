package model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exchangestuff", catalog = "share_db")
public class ExchangeStuff {
    private Integer id;
    private User user;
    private String title;
    private String description;
    private String expectation;
    private Timestamp createTime;
    private Boolean completed;
    private Boolean deleted;
    private School school;
    private Set<Attachment> attachments = new HashSet<Attachment>(0);
    private Set<ExchangeStuff> exchangeStuffs = new HashSet<ExchangeStuff>(0);

    public ExchangeStuff() {

    }

    public ExchangeStuff(Integer id, User user, String title, String description, String expectation,
                         Timestamp createTime, Set<Attachment> attachments) {
        super();
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.expectation = expectation;
        this.createTime = createTime;
        this.attachments = attachments;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "expectation", length = 45)
    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "completed")
    public Boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Column(name = "createTime", length = 0)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "exchangestuff_r_attachment", joinColumns = @JoinColumn(name = "exchangeStuff_id"), inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @JSONField(serialize = false)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "exchangestuff_r_exchageReply", joinColumns = @JoinColumn(name = "exchangeStuff_id"), inverseJoinColumns = @JoinColumn(name = "exchageReply_id"))
    @OrderBy(clause = "exchangeStuff_id ASC")
    public Set<ExchangeStuff> getExchangeStuffs() {
        return exchangeStuffs;
    }

    public void setExchangeStuffs(Set<ExchangeStuff> exchangeStuffs) {
        this.exchangeStuffs = exchangeStuffs;
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
