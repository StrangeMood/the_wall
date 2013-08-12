package controllers

import io.Source
import play.api._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.EventSource
import play.api.libs.iteratee.{Iteratee, Enumerator, Concurrent, Enumeratee}
import models.{Stats, HeartBeat, Commit}
import play.api.libs.EventSource.EventNameExtractor
import play.api.libs.Comet.CometMessage
import models.Stats._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.wall("cloudcastle"))
  }

  def wall(name: String) = Action {
    Ok(views.html.wall(name))
  }

  val commits = Github.events >- HeartBeat.events
  val stats = Stats.statsFor[Commit](commits)

  def events = Action {
    Ok.stream((commits &> EventSource[Commit]()) >- (stats &> EventSource[Map[String, Int]]())).as("text/event-stream")
  }
}