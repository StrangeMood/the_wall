package models

import play.api.libs.iteratee.{Concurrent, Iteratee, Enumerator, Enumeratee}
import play.api.libs.EventSource.EventNameExtractor
import play.api.libs.Comet.CometMessage
import play.api.libs.json.Json
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Stats {

  def statsFor[T <: Commit](enumerator: Enumerator[T]): (Enumerator[Map[String, Int]]) = {
    val (items, out) = Concurrent.broadcast[Map[String, Int]]

    val listener = Iteratee.fold[T, Map[String, Int]](Map[String, Int]()) {(stats, item) =>
      val statsForProject = stats.getOrElse(item.project, 0)
      val updatedStats = stats.updated(item.project, statsForProject + 1)
      out.push(updatedStats)
      updatedStats
    }

    enumerator(listener)

    items
  }

  def bufferFor[T](max: Int, enumerator: Enumerator[T]): (Enumerator[List[T]]) = {
    val (items, out) = Concurrent.broadcast[List[T]]

    val listener = Iteratee.fold[T, List[T]](List[T]()) {(buffer, item) =>
      val newBuffer = (item :: buffer).take(max)
      out.push(newBuffer)
      newBuffer
    }

    enumerator(listener)

    items
  }

  implicit val eventNameExtractorForStats = EventNameExtractor[Map[String, Int]](c => Some("stats"))
  implicit val jsonStats = CometMessage[Map[String, Int]](Json.toJson(_).toString())

}
