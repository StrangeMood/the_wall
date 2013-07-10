
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main("PELASE STAND BY")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
  <h1>"""),_display_(Seq[Any](/*4.8*/message)),format.raw/*4.15*/("""</h1>
""")))})),format.raw/*5.2*/("""
"""))}
    }
    
    def render(message:String): play.api.templates.Html = apply(message)
    
    def f:((String) => play.api.templates.Html) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jul 10 18:36:08 MSK 2013
                    SOURCE: /Users/loki/Work/the_wall/app/views/index.scala.html
                    HASH: b74ff1cac8d24fab568da2a10421bef2c58ba840
                    MATRIX: 505->1|599->18|636->21|667->44|706->46|748->54|776->61|813->68
                    LINES: 19->1|22->1|24->3|24->3|24->3|25->4|25->4|26->5
                    -- GENERATED --
                */
            