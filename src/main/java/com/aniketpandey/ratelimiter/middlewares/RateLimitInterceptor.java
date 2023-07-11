package com.aniketpandey.ratelimiter.middlewares;

import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class RateLimitInterceptor implements HandlerInterceptor {

  private final RateLimiter rateLimiter;

  public RateLimitInterceptor(RateLimiter rateLimiter) {
    this.rateLimiter = rateLimiter;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if(!rateLimiter.tryAcquire()) {
      response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Rate limit exceeded");
      return false;
    }
    return true;
  }
}
