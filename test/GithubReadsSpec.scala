import org.specs2.mutable.Specification

import play.api.libs.json._
import play.api.libs.functional.syntax._

import models.Commit
import controllers.GithubHook.commitsRead

import scala.io.Source

class GithubReadsSpec extends Specification {
  val json = Json.parse(Source.fromFile("test/data/github_payload.json").mkString)

  "github reads" should {
    "read list of commits" in {
      val commits = json.as[List[Commit]]
      commits.size === 3
      commits.map {_.project} === List("testing", "testing", "testing")
    }
  }

}
