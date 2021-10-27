package jurmala.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import jurmala.api.handlers.{JurmalaExceptionHandler, JurmalaRejectionHandler}
import jurmala.JurmalaContext
import jurmala.datasource.ItemDataSource

trait Api extends StatusApi
   with ItemApi
   with JurmalaRejectionHandler
   with JurmalaExceptionHandler {

  override def itemDataSource: ItemDataSource = JurmalaContext.itemDataSource

  lazy val routes: Route = {
    logRequestResult("jurmala") {
      Route.seal {
        statusRoutes ~ itemRoutes
      }
    }
  }
}
