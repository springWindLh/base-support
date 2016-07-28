package util;

import java.util.HashMap;
import java.util.Map;

public class Maps {
	public static Map<String,Object> toMap(Object... items) {
		if (null != items && items.length % 2 != 0) {
			throw new IllegalArgumentException(
					"Count of items must be even !!!");// 个数必须为偶数,抛出异常
		} else {
			Map<String,Object> map = new HashMap<>();
			for (int i = 0; null != items && i < items.length; i = i + 2) {
				map.put(items[i].toString(), items[i + 1]);
			}
			return map;
		}
	}
}
