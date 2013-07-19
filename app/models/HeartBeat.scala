package models

import play.api.libs.iteratee.Enumerator
import play.api.libs.concurrent.Promise
import concurrent.duration._
import concurrent.ExecutionContext.Implicits.global
import org.joda.time.format.ISODateTimeFormat
import org.joda.time.DateTime

object HeartBeat {

  private val isoFmt = ISODateTimeFormat.dateTime()

  val events = Enumerator.generateM[Commit](
    Promise.timeout(
      Some(new Commit("__none__", "HEARTBEAT", ("Joppa", "joppa@driller.com"), isoFmt.print(new DateTime()), "http://google.com?q=joppa", "heartbeat")),
      5000 milliseconds)
  )

}
