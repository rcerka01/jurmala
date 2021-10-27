package jurmala

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import jurmala.config.Config
import jurmala.datasource.{ItemDataSource, RandomItemDataSource}

object JurmalaContext extends Config {
  implicit val system: ActorSystem = ActorSystem(serviceName)
  val log: LoggingAdapter = system.log

  val itemDataSource: ItemDataSource = new RandomItemDataSource
}
