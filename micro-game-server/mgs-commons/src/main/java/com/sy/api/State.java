package com.sy.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface State {

	/**@key String state.value()*/
	static final Map<String, State> gameStatesMap = new ConcurrentHashMap<String, State>();

	/**通过String获取具体枚举*/
	public static State getState(String stateValue) {
		return gameStatesMap.get(stateValue);
	}

	/**String stateValue*/
	public String value();

	/**自定义String形参的equals*/
	public default boolean equals(String str) {
		return this.value().equals(str);
	}
}
