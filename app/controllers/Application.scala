package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.EventSource
import play.api.libs.iteratee.{Concurrent, Enumeratee}
import models.Commit

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Wait for messages in console..."))
  }

  private val toJson = Enumeratee.map[Commit](Json.toJson(_))
  def events = Action {
    Ok.stream(GithubHook.githubCommits &> toJson &> Concurrent.buffer(50) &> EventSource()).as("text/event-stream")
  }
}