package com.aniketpandey.ratelimiter.configurations;

public class RateLimiterProperties {
  private int maxRequestsPerMinute;

  public int getMaxRequestsPerMinute() {
    return this.maxRequestsPerMinute;
  }

  public void setMaxRequestsPerMinute(int maxRequestsPerMinute) {
    this.maxRequestsPerMinute = maxRequestsPerMinute;
  }
}
