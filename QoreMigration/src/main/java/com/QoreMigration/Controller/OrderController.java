package com.QoreMigration.Controller;

import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.spring.pubsub.core.publisher.PubSubPublisherTemplate;

@RestController
public class OrderController {
  private final PubSubPublisherTemplate publisherTemplate;

  OrderController(
      PubSubPublisherTemplate publisherTemplate) {
    this.publisherTemplate = publisherTemplate;
  }

  @PostMapping("/order/submit")
  void submitOrder(@RequestBody Order order) {
    publisherTemplate.publish("qpm-publish1", order);
  }
}
