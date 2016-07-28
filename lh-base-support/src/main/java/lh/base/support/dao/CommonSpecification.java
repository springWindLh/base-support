package lh.base.support.dao;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/3/4.
 * 查询条件容器
 */
public class CommonSpecification implements Specification {
    private List<Predicate> restrictions = new ArrayList<>();
    private Root root;
    private CriteriaQuery criteriaQuery;
    private CriteriaBuilder criteriaBuilder;

    public CommonSpecification(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.criteriaQuery = criteriaQuery;
        this.criteriaBuilder = criteriaBuilder;
    }

    public List<Predicate> addRestriction(Predicate restriction) {
        restrictions.add(restriction);
        return restrictions;
    }

    public List<Predicate> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Predicate> restrictions) {
        this.restrictions = restrictions;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public CriteriaQuery getCriteriaQuery() {
        return criteriaQuery;
    }

    public void setCriteriaQuery(CriteriaQuery criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }


    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }


    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
        Predicate[] restrictions = this.getRestrictions().toArray(new Predicate[this.getRestrictions().size()]);
        query.where(restrictions);
        return query.getRestriction();
    }
}
