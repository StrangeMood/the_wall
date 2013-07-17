package models

import play.api.libs.iteratee.Enumeratee

object Stats {

  val wallShare = Enumeratee.scanLeft[Commit](Map[String, Int]()) { (stats, commit) =>
    val statsForProject = stats.getOrElse(commit.project, 0)
    stats.updated(commit.project, statsForProject + 1)
  }

}
