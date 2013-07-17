package models

import play.api.libs.iteratee.Enumerator
import play.api.libs.concurrent.Promise
import concurrent.duration._
import concurrent.ExecutionContext.Implicits.global

object HeartBeat {

  val events = Enumerator.generateM[Commit](
    Promise.timeout(
      Some(new Commit("__none__", "HEARTBEAT", ("Joppa Driller", "joppa.driller@toilet.com"), "xxx", "http://google.com", "heartbeat")),
      1000 milliseconds)
  )

}
