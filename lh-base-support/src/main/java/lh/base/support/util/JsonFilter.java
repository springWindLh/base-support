package lh.base.support.util;

import com.alibaba.fastjson.serializer.PropertyFilter;

import java.util.HashSet;
import java.util.Set;

public class JsonFilter implements PropertyFilter {
    private final Class<?> clazz;
    private Set<String> includes = new HashSet<String>();
    private Set<String> excludes = new HashSet<String>();
    private Boolean isPrimitive;

    public Boolean getIsPrimitive() {
        return isPrimitive;
    }

    public void setIsPrimitive(Boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
    }

    public JsonFilter(Class<?> clazz) {
        super();
        this.clazz = clazz;
        this.isPrimitive = false;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean apply(Object source, String name, Object value) {
        if (source == null) {
            return true;
        }

        if (clazz != null && !clazz.isInstance(source)) {
            return true;
        }

        if (this.excludes.contains(name)) {
            return false;
        }
        if (isPrimitive && clazz.isInstance(source)) {
            if (value != null && isBasicType(value.getClass())) {
                return true;
            } else {
                return false;
            }
        }

        if (includes.size() == 0 || includes.contains(name)) {
            return true;
        }

        return false;
    }

    public Set<String> getIncludes() {
        return includes;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public void setIncludes(Set<String> includes) {
        this.includes = includes;
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
    }

    public Boolean isBasicType(Class<?> type) {
        return type.isPrimitive() || String.class.isAssignableFrom(type)
                || Boolean.class.isAssignableFrom(type)
                || Character.class.isAssignableFrom(type)
                || Number.class.isAssignableFrom(type)
                || java.util.Date.class.isAssignableFrom(type)
                || java.sql.Timestamp.class.isAssignableFrom(type);
    }

}
