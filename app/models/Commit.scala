package models

import org.joda.time.DateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.EventSource.EventNameExtractor
import play.api.libs.Comet.CometMessage

case class Commit(
  id: String,
  message: String,
  author: (String, String),
  timestamp: String,
  url: String,
  project: String
)

object Commit {
  implicit val commitWrites = (
    (__ \ "id").write[String] ~
    (__ \ "message").write[String] ~
    (__ \ "author").write(
      (__ \ "email").write[String] ~
      (__ \ "name").write[String]
      tupled
    ) ~
    (__ \ "timestamp").write[String] ~
    (__ \ "url").write[String] ~
    (__ \ "project").write[String]
  )(unlift(Commit.unapply))

  implicit val eventNameExtractorForCommit = EventNameExtractor[Commit](c => Some("commit"))
  implicit val jsonCommits = CometMessage[Commit](Json.toJson(_).toString())
}