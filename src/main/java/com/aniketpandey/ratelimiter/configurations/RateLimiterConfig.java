package com.aniketpandey.ratelimiter.configurations;

import com.aniketpandey.ratelimiter.middlewares.RateLimitInterceptor;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RateLimiterConfig implements WebMvcConfigurer {
  private final RateLimiterProperties rateLimiterProperties;

  public RateLimiterConfig(RateLimiterProperties rateLimiterProperties) {
    this.rateLimiterProperties = rateLimiterProperties;
  }

  @Bean
  public RateLimiter rateLimiter() {
    return RateLimiter.create(rateLimiterProperties.getMaxRequestsPerMinute());
  }

  @Bean
  public RateLimitInterceptor rateLimitInterceptor(RateLimiter rateLimiter) {
    return new RateLimitInterceptor(rateLimiter);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    HandlerInterceptor rateLimitInterceptor;
    registry.addInterceptor(rateLimitInterceptor(rateLimiter()));
  }
}
