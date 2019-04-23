package com.sy.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * SpringUtil工具类
 * 用以在普通类中获取Spring的ApplicationContext和baan
 * 
 * */
@Component
public class SpringUtil implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		logger.info("-----------------------------------------------------------------------------------");
		logger.info("--------sy.util.SpringUtil---------------------------------------------------------");
		logger.info("--------ApplicationContext配置成功-------------------------------------------------");
		logger.info("--------可通过SpringUtils.getApplicationContext()获取applicationContext对象---------");
		logger.info("--------applicationContext=" + SpringUtil.applicationContext + "-------------------");
		logger.info("-----------------------------------------------------------------------------------");
	}

	/**获取applicationContext*/
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**通过name获取 Bean.*/
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**通过class获取Bean.*/
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**通过name,以及Clazz返回指定的Bean*/
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
	/**
	* 从容器中获取BeanFactory
	* @param ctx
	* @return 
	*/
	public static DefaultListableBeanFactory getBeanFactory(ApplicationContext ctx) {
		return (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
	}

	/**
	* 向容器中动态添加Bean
	*
	* @param ctx
	* @param beanName
	* @param beanClass
	* @return 
	*/
	public static void addBean(ApplicationContext ctx, String beanName, Class<?> beanClass) {
		BeanDefinitionRegistry beanDefReg = getBeanFactory(ctx);
		BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
		BeanDefinition beanDef = beanDefBuilder.getBeanDefinition();
		if (beanDefReg.containsBeanDefinition(beanName)) {
			logger.warn("the bean named {} is already exist ,so it will be override.",beanName);
		}
		beanDefReg.registerBeanDefinition(beanName, beanDef);
	}

	/**
	 * 从容器中移除Bean
	 *
	 * @param ctx
	 * @param beanName
	 */
	public static void removeBean(ApplicationContext ctx, String beanName) {
		BeanDefinitionRegistry beanDefReg = getBeanFactory(ctx);
		beanDefReg.removeBeanDefinition(beanName);
	}

	/**
	 * 遍历获取所有Bean的信息
	 */
	public static Map<String, Object> getAllBeans(ApplicationContext ctx) {
		Map<String, Object> allBeans = new HashMap<String, Object>();
		for (String name : ctx.getBeanDefinitionNames()) {
			allBeans.put(name, ctx.getBean(name));
		}
		return allBeans;
	}
}
