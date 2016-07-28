package lh.base.support.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/4/6.
 */
public class DomainPage<T extends BaseDomain> implements Serializable {
    private int page;
    private int size;
    private int pageTotal;
    private long domainTotal;
    private boolean hasNext;
    private boolean isLast;
    private List<T> domains = new ArrayList<>();

    public DomainPage() {
    }

    public DomainPage(int page, int size, int pageTotal, long domainTotal, boolean hasNext, boolean isLast, List<T> domains) {
        this.page = page;
        this.size = size;
        this.pageTotal = pageTotal;
        this.domainTotal = domainTotal;
        this.hasNext = hasNext;
        this.isLast = isLast;
        this.domains = domains;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public long getDomainTotal() {
        return domainTotal;
    }

    public void setDomainTotal(long domainTotal) {
        this.domainTotal = domainTotal;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public List<T> getDomains() {
        return domains;
    }

    public void setDomains(List<T> domains) {
        this.domains = domains;
    }
}
