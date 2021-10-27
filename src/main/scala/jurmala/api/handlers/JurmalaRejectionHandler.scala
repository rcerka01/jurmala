package jurmala.api.handlers

import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.http.scaladsl.server.{Rejection, RejectionHandler}
import jurmala.config.Config


trait JurmalaRejection extends Rejection {
  def statusCode: StatusCode
  def message: String
}

trait JurmalaRejectionHandler extends ErrorFormat
  with Config {

  implicit val rejectionHandler = RejectionHandler.newBuilder()
    .handle { case jurmalaRejection: JurmalaRejection =>
      completeWithError(errorDocumentationUrl) {
        ErrorResponseData(jurmalaRejection.statusCode, jurmalaRejection.message)
      }
    }
    .handleNotFound {
      completeWithError(errorDocumentationUrl) {
        ErrorResponseData(StatusCodes.NotFound, "Not found")
      }
    }
    .result()
}
