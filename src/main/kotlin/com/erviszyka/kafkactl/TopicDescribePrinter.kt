package com.erviszyka.kafkactl

import org.apache.kafka.clients.admin.TopicDescription

class TopicDescribePrinter {
  fun print(result: TopicDescription) {
    println("id: ${result.topicId()}")
    println("name: ${result.name()}")
  }
}
