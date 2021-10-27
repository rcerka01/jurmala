package jurmala.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import jurmala.api.directives.EnabledDirectives
import jurmala.domain.marshalling.JsonSerializers
import jurmala.domain.responses.Status

trait StatusApi extends EnabledDirectives with JsonSerializers with SprayJsonSupport {

  val statusRoutes: Route = {
    path("status") {
      get {
        respondWithNoCacheHeaders {
          complete {
            Status("OK")
          }
        }
      }
    }
  }
}
