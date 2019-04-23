package com.sy.api.consumer.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sy.api.consumer.restApi.UserFeignHystrixClient;
import com.sy.pojo.User;

@Component
public class UserFeignHystrixClientFallBack implements UserFeignHystrixClient {


    private static final Logger logger = LoggerFactory.getLogger(UserFeignHystrixClientFallBack.class);

    /**
     * hystrix fallback方法
     * @param id id
     * @return 默认的用户
     */
    @Override
    public User findByIdFeign(Long id) {
        UserFeignHystrixClientFallBack.logger.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        User user = makeUser();
        return user;
    }

    @Override
    public User testTimeOutFeign(Long time) {
        UserFeignHystrixClientFallBack.logger.info("异常发生，进入fallback方法，接收的参数：time = {}", time);
        User user = makeUser();
        return user;
    }

    private User makeUser() {
        User user = new User();
        user.setName("default username");
        return user;
    }

	@Override
	public User sayHi(String message) {
		User user = new User();
        user.setName(message);
        return user;
	}

	@Override
	public User sayHis(String message) {
		User user = new User();
        user.setName(message);
        return user;
	}

}
