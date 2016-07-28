package lh.base.support.util;

import java.util.ArrayList;
import java.util.List;

public class JsonFilterUtil {
    List<JsonFilter> jsonFilters;

    public List<JsonFilter> getJsonFilters() {
        return jsonFilters;
    }

    public void setJsonFilters(List<JsonFilter> jsonFilters) {
        this.jsonFilters = jsonFilters;
    }

    public JsonFilterUtil() {
        jsonFilters = new ArrayList<>();
    }

    public List<JsonFilter> exclusion(Class<?> class1, String... properties) {
        JsonFilter jsonFilter = new JsonFilter(class1);
        for (String field : properties) {
            jsonFilter.getExcludes().add(field);
        }
        jsonFilters.add(jsonFilter);
        return jsonFilters;
    }

    public List<JsonFilter> exclusionExcept(Class<?> class1, String... properties) {
        JsonFilter jsonFilter = new JsonFilter(class1);
        for (String field : properties) {
            jsonFilter.getIncludes().add(field);
        }
        jsonFilters.add(jsonFilter);
        return jsonFilters;
    }

    public List<JsonFilter> exclusionExceptPrimitive(Class<?> class1) {
        JsonFilter jsonFilter = new JsonFilter(class1);
        jsonFilter.setIsPrimitive(true);
        jsonFilters.add(jsonFilter);
        return jsonFilters;
    }


}
