package com.erviszyka.kafkactl

import org.apache.kafka.clients.admin.AdminClient

class TopicDescriber(private val delegate: AdminClient) {
  fun describe(topic: String) {
    when (val result = delegate.describeTopics(listOf(topic)).all().get()[topic]) {
      null -> TODO()
      else -> TopicDescribePrinter().print(result)
    }
  }
}
