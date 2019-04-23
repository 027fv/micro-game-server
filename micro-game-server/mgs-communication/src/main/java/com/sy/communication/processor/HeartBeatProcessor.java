package com.sy.communication.processor;

import com.alibaba.fastjson.JSONObject;
import com.sy.api.MsgProcessor;
import com.sy.communication.annotation.Processor;
import com.sy.communication.api.API;
import com.sy.pojo.Session;

/**  
 * @author fv
 */
@Processor(API.HEARTBEAT_REQUEST)
public class HeartBeatProcessor implements MsgProcessor {

	@Override
	public void process(Session session, JSONObject data) {
		session.setHeatBest(System.currentTimeMillis());
		respond(session, API.HEARTBEAT_RESPONSE, data);
	}
}
