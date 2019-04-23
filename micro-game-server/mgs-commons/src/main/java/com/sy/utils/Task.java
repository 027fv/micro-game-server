package com.sy.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;

public class Task {

	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	
	/** 任务集合 */
	private static final Map<String, Timer> timerMgrs = new ConcurrentHashMap<String, Timer>();

	/**
	 * 开始定时任务
	 * 
	 * @param task
	 * @param delay延时时间
	 * @param unit时间单位
	 * @return TimeoutUUID
	 */
	public static String taskStart(Runnable task, long delay, TimeUnit unit) {
		// String timeoutId =
		// IDUtils.getUUID();//UUID.randomUUID().toString().replace("-", "");
		Timer timer = new Timer(task, delay, unit);
		timer.start();
		timerMgrs.put(timer.timeoutId, timer);
		return timer.timeoutId;
	}
	/**
	 * 开始循环定时任务
	 * 
	 * @param task
	 * @param delay延时时间
	 * @param unit时间单位
	 * @return TimeoutUUID
	 */
	public static String taskLoop(Runnable task, long delay, TimeUnit unit) {
		// String timeoutId =
		// IDUtils.getUUID();//UUID.randomUUID().toString().replace("-", "");
		Timer timer = new Timer(task, delay, unit);
		timer.loopStart();
		timerMgrs.put(timer.timeoutId, timer);
		return timer.timeoutId;
	}

	/** 获取定时任务的执行时间 */
	public static Long getExecuteTime(String timeoutId) {
		Timer timer = timerMgrs.get(timeoutId);
		if (timer != null) {
			return timer.execute;
		}
		return null;
	}

	/**
	 * 暂停定时任务 暂停任务后,并没有清除任务集合的缓存,请务必调用com.sy.utils.Task.taskCancel(timeoutId)清除
	 */
	public static Long taskSuspend(String timeoutId) {
		Timer timer = timerMgrs.get(timeoutId);
		if (timer != null) {
			timer.suspend();
			return timer.execute;
		}
		return null;
	}

	/** 重启暂停的定时任务 */
	public static boolean taskReStart(String timeoutId) {
		Timer timer = timerMgrs.get(timeoutId);
		if (timer != null) {
			timer.restart(timer.delay, timer.timeUnit);
			return true;
		}
		return false;
	}

	/** 重启暂停的定时任务 */
	public static boolean taskReStart(String timeoutId, long delay, TimeUnit unit) {
		Timer timer = timerMgrs.get(timeoutId);
		if (timer != null) {
			timer.restart(delay, unit);
			return true;
		}
		return false;
	}

	/** 取消定时任务 */
	public static void taskCancel(String timeoutId) {
		Timer timer = timerMgrs.remove(timeoutId);
		if (timer != null) {
			timer.cancel();
		}
	}

	private static class Timer {

		private static final HashedWheelTimer wheelTimer = new HashedWheelTimer();

		/** uuid */
		private String timeoutId;
		/** 任务 */
		private Runnable task;
		/** 延迟 */
		private Long delay;
		/** 时间单位 */
		private TimeUnit timeUnit;

		private Timeout timeout;
		/** 执行时间 */
		private Long execute;

		// private Timer(Task task) {
		// super();
		// this.timeoutId =
		// IDUtils.getUUID();//UUID.randomUUID().toString().replace("-", "");
		// this.task = task;
		// }
		private Timer(Runnable task, long delay, TimeUnit timeUnit) {
			super();
			this.timeoutId = IDUtils.getUUID();// UUID.randomUUID().toString().replace("-", "");
			this.task = task;
			this.delay = delay;
			this.timeUnit = timeUnit;
		}

		private void start() {
			timeout = wheelTimer.newTimeout((t) -> {
				task.run();
				clear();
			}, delay, timeUnit);
			execute = System.currentTimeMillis() + timeUnit.toMillis(delay);
		}
		private void loopStart() {
			timeout = wheelTimer.newTimeout((t) -> {
				loopStart();
				task.run();
			}, delay, timeUnit);
			execute = System.currentTimeMillis() + timeUnit.toMillis(delay);
		}

		private boolean suspend() {
			if (timeout != null) {
				timeout.cancel();
				timeout = null;
				execute = null;
				logger.warn("暂停任务后,并没有清除任务集合的缓存,请务必调用com.sy.utils.Task.taskCancel(timeoutId)清除");
				return true;
			}
			return false;
		}

		private void restart(long delay, TimeUnit timeUnit) {
			this.delay = delay;
			this.timeUnit = timeUnit;
			start();
		}

		private boolean cancel() {
			timerMgrs.remove(timeoutId);
			if (timeout != null) {
				timeout.cancel();
				timeout = null;
				execute = null;
				return true;
			}
			return false;
		}

		/** 任务执行完或放弃后清除任务计时器,释放内存 */
		private void clear() {
			timerMgrs.remove(timeoutId);
			timeout = null;
			execute = null;
		}
	}
}
