package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("A lot of interesting will be here. Stay tuned...."))
  }

}