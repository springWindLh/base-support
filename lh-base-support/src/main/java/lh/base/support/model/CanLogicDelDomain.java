package lh.base.support.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by lh on 2016/4/7.
 */
@MappedSuperclass
public abstract class CanLogicDelDomain extends BaseDomain {
    @Column
    protected Boolean del = false;

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
