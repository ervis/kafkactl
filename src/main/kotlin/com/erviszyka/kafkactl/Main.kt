package com.erviszyka.kafkactl

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.KafkaAdminClient

fun main(args: Array<String>) {
  // kafkactl create topic --name zipkin --partitions 1 --replication 1
  // kafkactl describe topic --name zipkin
  val parser = ArgParser("kafkactl")
  val action =
    parser.argument(ArgType.Choice(listOf("create", "describe"), { it }), fullName = "action", description = "action")
  val resource =
    parser.argument(ArgType.Choice(listOf("topic"), { it }), fullName = "resource", description = "resource")

  val topic = parser.option(ArgType.String, fullName = "name", shortName = "n")
  parser.parse(args)

  val config = mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaConfig().url)
  val admin = KafkaAdminClient.create(config)

  when (action.value) {
    "describe" -> TopicDescribePrinter().print(admin.describeTopics(listOf(topic.value)).all().get()[topic.value]!!)
  }
}
