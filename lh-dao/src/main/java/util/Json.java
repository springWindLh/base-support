package util;

import java.util.List;

/**
 * 属性过滤
 **/
public class Json {

    private JsonFilterUtil jsonFilterUtil;

    public JsonFilterUtil getJsonFilterUtil() {
        return jsonFilterUtil;
    }

    public void setJsonFilterUtil(JsonFilterUtil jsonFilterUtil) {
        this.jsonFilterUtil = jsonFilterUtil;
    }

    public JsonFilter[] getJsonFilters() {
        return (JsonFilter[]) jsonFilterUtil.getJsonFilters().toArray(
                new JsonFilter[jsonFilterUtil.getJsonFilters().size()]);
    }

    public Json() {
        jsonFilterUtil = new JsonFilterUtil();
    }

    /**
     * 不序列化指定属性
     **/
    public List<JsonFilter> exclusion(Class<?> class1, String... properties) {
        return jsonFilterUtil.exclusion(class1, properties);
    }

    /**
     * 只序列化指定属性
     **/
    public List<JsonFilter> exclusionExcept(Class<?> class1,
                                            String... properties) {
        return jsonFilterUtil.exclusionExcept(class1, properties);
    }

    /**
     * 只序列化基本属性
     **/
    public List<JsonFilter> exclusionExceptPrimitive(Class<?> class1) {
        return jsonFilterUtil.exclusionExceptPrimitive(class1);
    }
}
