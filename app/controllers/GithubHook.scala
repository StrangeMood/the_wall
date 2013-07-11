package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models.Commit

object GithubHook extends Controller {

  implicit val commitsRead = new Reads[List[Commit]] {
    def reads(js: JsValue): JsResult[List[Commit]] = {
      (js \ "repository" \ "name").validate[String].flatMap { project =>
        implicit val commitReads = (
          (__ \ "id").read[String] ~
          (__ \ "message").read[String] ~
          (__ \ "author").read(
            (__ \ "email").read[String] ~
            (__ \ "name").read[String]
            tupled
          ) ~
          (__ \ "timestamp").read[String] ~
          (__ \ "url").read[String]
        )(Commit.apply(_, _, _, _, _, project))

        (js \ "commits").validate[List[Commit]]
      }
    }
  }

}
