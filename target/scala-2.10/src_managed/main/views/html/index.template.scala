
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

"""),_display_(Seq[Any](/*3.2*/main("SIMPLE STREAM")/*3.23*/ {_display_(Seq[Any](format.raw/*3.25*/("""
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
                    DATE: Fri Jul 12 09:53:44 MSK 2013
                    SOURCE: /Users/loki/Work/the_wall/app/views/index.scala.html
                    HASH: b2f5047a90bf367421940ec42402cd25a8886db5
                    MATRIX: 505->1|599->18|636->21|665->42|704->44|746->52|774->59|811->66
                    LINES: 19->1|22->1|24->3|24->3|24->3|25->4|25->4|26->5
                    -- GENERATED --
                */
            