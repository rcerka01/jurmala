package jurmala.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import jurmala.api.directives.EnabledDirectives
import jurmala.config.Config
import jurmala.datasource.ItemDataSource
import jurmala.domain.marshalling.JsonSerializers
import jurmala.JurmalaContext._

trait ItemApi extends JsonSerializers
  with EnabledDirectives
  with Config
  with SprayJsonSupport {

  def itemDataSource: ItemDataSource

  val itemRoutes: Route = {
    path("items") {
      get {
        respondWithCacheHeaders {
          paginate(defaultPageLimit, maximumPageLimit) { pagination =>
            sort { itemSort =>
              complete {
                log.info("/items")
                toResponse(pagination)(itemDataSource.getMultipleItems(pagination.limit, pagination.offset))
              }
            }
          }
        }
      }
    } ~ path("item" / IntNumber) { id =>
      get {
        respondWithCacheHeaders {
          complete {
            log.info(s"/item/$id")
            toResponse()(itemDataSource.getSingleItem(id))
          }
        }
      }
    }
  }
}

