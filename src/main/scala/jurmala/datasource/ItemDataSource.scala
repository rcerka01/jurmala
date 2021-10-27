package jurmala.datasource

import jurmala.domain.Item
import jurmala.domain.responses.ResponseWrapper.{MultiEntityResponseData, SingleEntityResponseData}

import scala.concurrent.Future

trait ItemDataSource {
  def getSingleItem(id: Int): Future[SingleEntityResponseData[Item]]
  def getMultipleItems(limit: Int, offset: Int): Future[MultiEntityResponseData[Item]]
}
