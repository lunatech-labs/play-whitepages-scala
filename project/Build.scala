import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "whitepages-scala"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      "org.scalaquery" % "scalaquery_2.9.0-1" % "0.9.5"
    )

    val main = PlayProject(appName, appVersion, appDependencies).settings(defaultScalaSettings:_*).settings(
      // Add your own project settings here      
    )

}
