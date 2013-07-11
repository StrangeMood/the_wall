package models

import org.joda.time.DateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Commit(
  id: String,
  message: String,
  author: (String, String),
  timestamp: String,
  url: String
//  project: String
)

object Commit {
  implicit val githubReads: Reads[Commit] = (
    (__ \ "id").read[String] ~
    (__ \ "message").read[String] ~
    (__ \ "author").read(
      (__ \ "email").read[String] ~
      (__ \ "name").read[String]
      tupled
    ) ~
    (__ \ "timestamp").read[String] ~
    (__ \ "url").read[String]
  )(Commit.apply _)
}