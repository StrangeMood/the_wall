package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models.Commit
import play.api.libs.iteratee.{Enumerator, Enumeratee, Concurrent}
import play.api.data._
import play.api.data.Forms._

object Github extends Controller {

  implicit val commitsRead = new Reads[List[Commit]] {
    private val commitBuilder =
      (__ \ "id").read[String] ~
        (__ \ "message").read[String] ~
        (__ \ "author").read(
          (__ \ "email").read[String] ~
            (__ \ "name").read[String]
            tupled
        ) ~
        (__ \ "timestamp").read[String] ~
        (__ \ "url").read[String]

    def reads(js: JsValue): JsResult[List[Commit]] = {
      (js \ "repository" \ "name").validate[String].flatMap { project =>
        implicit val commitReads = commitBuilder(Commit.apply(_, _, _, _, _, project))

        (js \ "commits").validate[List[Commit]]
      }
    }
  }

  private val (commits, channel) = Concurrent.broadcast[Commit]

  val events = commits

  def hook = Action { implicit request =>
    val payload = Json.parse(Form("payload" -> text).bindFromRequest.get)

    payload.validate[List[Commit]].map {
      case commits => {
        commits.reverse.foreach(channel.push(_))
        Ok("Thanks")
      }
    }.recoverTotal {
      e => {
        Logger.error(JsError.toFlatJson(e).toString)
        BadRequest("Invalid json " + JsError.toFlatJson(e))
      }
    }
  }

}
