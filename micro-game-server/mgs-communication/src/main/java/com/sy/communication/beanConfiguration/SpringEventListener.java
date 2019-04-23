package com.sy.communication.beanConfiguration;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sy.communication.annotation.Customize;
import com.sy.utils.SpringUtil;

@Component("BeanDefineConfigue")
public class SpringEventListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(SpringEventListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		ApplicationContext applicationContext = event.getApplicationContext();
		Map<String, Object> customizes = applicationContext.getBeansWithAnnotation(Customize.class);
		Map<String, Object> allBeans = SpringUtil.getAllBeans(applicationContext);
		for (Entry<String, Object> entry : customizes.entrySet()) {
			entry.getValue().getClass().getSuperclass();
		}

		

	}

}
