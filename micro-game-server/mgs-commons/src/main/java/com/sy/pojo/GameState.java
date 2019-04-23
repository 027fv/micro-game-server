package com.sy.pojo;

import com.sy.api.State;

public enum GameState implements State{
	/** 空闲中 */
	IDLE("idle"),
	/** 初始化中 */
	INIT("init"),
	/** 选择钓鱼中 */
	FISHING("fishing"),
	/**选缺中 */
	XUANQUE("xuanQue"),
	/** 游戏中 */
	PLAYING("playing"),
	/** 游戏结束 */
	GAMEOVER("gameOver");

	private final String state;

	private GameState(String state) {
		this.state = state;
	}
	
	@Override
	public String value() {
		return state;
	}


	static {
		for (GameState state : GameState.values()) {
			gameStatesMap.put(state.value(), state);
		}
	}


}
