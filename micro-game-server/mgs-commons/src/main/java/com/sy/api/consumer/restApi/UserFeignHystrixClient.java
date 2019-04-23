package com.sy.api.consumer.restApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sy.api.consumer.fallback.UserFeignHystrixClientFallBack;
import com.sy.pojo.User;

/**
 * 使用@FeignClient注解的fallback属性，指定fallback类
 */
@FeignClient(name = "service-demo", fallback = UserFeignHystrixClientFallBack.class)
public interface UserFeignHystrixClient {

  @RequestMapping("/{id}")
  User findByIdFeign(@PathVariable("id") Long id);

  @RequestMapping("/time_out/{time}")
  User testTimeOutFeign(@PathVariable("time") Long time);
  
  @RequestMapping(value = "/hi",method= RequestMethod.GET)
  User sayHi(@RequestParam("message") String message);
  
  @GetMapping(value = "/his")
  User sayHis(@RequestParam("message") String message);

}
