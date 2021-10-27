package jurmala.api.handlers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.ExceptionHandler
import jurmala.JurmalaContext.log
import jurmala.config.Config

trait JurmalaExceptionHandler extends Config with ErrorFormat {

  implicit val routingExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case e: Throwable => {
        log.error(e.getMessage)
        completeWithError(errorDocumentationUrl) {
          ErrorResponseData(StatusCodes.InternalServerError, "Server Error")
        }
      }
    }
}
