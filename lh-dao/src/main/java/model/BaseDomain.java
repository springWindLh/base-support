package model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lh on 2016/4/5.
 */
@MappedSuperclass
public abstract class BaseDomain implements Serializable, Cloneable {
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedTime;

    public abstract Long getId();

    public abstract void setId(Long id);

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
