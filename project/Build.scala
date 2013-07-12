import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "the_wall"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
//    autoScalaLibrary := false,
//    scalaVersion := "2.10.2"
  )

}
