package jurmala.api.directives

import jurmala.api.directives.custom.{CacheDirectives, PaginateDirectives, SortDirectives}
import jurmala.domain.responses.ResponseWrapper

trait EnabledDirectives extends ResponseWrapper
  with CacheDirectives
  with PaginateDirectives
  with SortDirectives
