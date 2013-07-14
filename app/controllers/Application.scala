package controllers

import io.Source
import play.api._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.EventSource
import play.api.libs.iteratee.{Iteratee, Enumerator, Concurrent, Enumeratee}
import models.Commit

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.wall("cloudcastle"))
  }

  def wall(name: String) = Action {
    Ok(views.html.wall(name))
  }

  def events = Action {
    Ok.stream(Github.events >- Github.stats &> Concurrent.buffer(50) &> EventSource()).as("text/event-stream")
  }
}