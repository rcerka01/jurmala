package jurmala

import akka.http.scaladsl.Http
import jurmala.api.Api
import jurmala.config.Config
import JurmalaContext._

import scala.concurrent.ExecutionContext

object JurmalaService extends App
  with Api
  with Config {

  implicit def executor: ExecutionContext = system.dispatchers.lookup("jurmala-dispatcher")

  Http().newServerAt(httpInterface, httpPort).bind(routes)

  log.info(s"Starting $serviceName on $httpInterface:$httpPort")
}
