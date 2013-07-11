package models

import org.joda.time.DateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

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
}