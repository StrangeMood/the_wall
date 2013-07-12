// @SOURCE:/Users/loki/Work/the_wall/conf/routes
// @HASH:63d8a85c9bc8071192b5d7a965be4f7903449aef
// @DATE:Fri Jul 12 09:53:40 MSK 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_events1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("events"))))
        

// @LINE:8
private[this] lazy val controllers_Application_websocket2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("websocket"))))
        

// @LINE:11
private[this] lazy val controllers_GithubHook_hook3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("github/hook"))))
        

// @LINE:14
private[this] lazy val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """events""","""controllers.Application.events"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """websocket""","""controllers.Application.websocket"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """github/hook""","""controllers.GithubHook.hook"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index, HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_events1(params) => {
   call { 
        invokeHandler(controllers.Application.events, HandlerDef(this, "controllers.Application", "events", Nil,"GET", """""", Routes.prefix + """events"""))
   }
}
        

// @LINE:8
case controllers_Application_websocket2(params) => {
   call { 
        invokeHandler(controllers.Application.websocket, HandlerDef(this, "controllers.Application", "websocket", Nil,"GET", """""", Routes.prefix + """websocket"""))
   }
}
        

// @LINE:11
case controllers_GithubHook_hook3(params) => {
   call { 
        invokeHandler(controllers.GithubHook.hook, HandlerDef(this, "controllers.GithubHook", "hook", Nil,"POST", """ Github hook""", Routes.prefix + """github/hook"""))
   }
}
        

// @LINE:14
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        