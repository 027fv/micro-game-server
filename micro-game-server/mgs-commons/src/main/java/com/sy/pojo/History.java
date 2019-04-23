package com.sy.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 房间历史记录
 * @author fv
 * */

public class History implements Serializable {

	private static final long serialVersionUID = 7106833813375333613L;

	private String uuid; // room uuid
	private int numOfTurns; // 第几局
	private String baseInfo; // 房间信息
	private Timestamp endTime; // 结束时间
	private String actionsRecord; // 回放用 	JSON字符串
	private String result; // 玩家得分 	JSON字符串
	private String participant; // 参与者
	private String relevant; // 房间相关者 连表用

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getNumOfTurns() {
		return numOfTurns;
	}

	public void setNumOfTurns(int numOfTurns) {
		this.numOfTurns = numOfTurns;
	}

	public String getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getActionsRecord() {
		return actionsRecord;
	}

	public void setActionsRecord(String actionsRecord) {
		this.actionsRecord = actionsRecord;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getRelevant() {
		return relevant;
	}

	public void setRelevant(String relevant) {
		this.relevant = relevant;
	}

	public History() {
		super();
	}

	public History(String uuid, int numOfTurns, String baseInfo, Timestamp endTime, String actionsRecord, String result, String participant, String relevant) {
		super();
		this.uuid = uuid;
		this.numOfTurns = numOfTurns;
		this.baseInfo = baseInfo;
		this.endTime = endTime;
		this.actionsRecord = actionsRecord;
		this.result = result;
		this.participant = participant;
		this.relevant = relevant;
	}

}
