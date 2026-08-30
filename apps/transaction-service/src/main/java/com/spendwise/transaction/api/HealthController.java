package com.spendwise.transaction.api;

import java.time.Instant;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

  @GetMapping("/ping")
  public Map<String, Object> ping() {
    return Map.of(
        "status", "ok",
        "service", "transaction-service",
        "timestamp", Instant.now().toString());
  }
}
