resolvers ++= Seq(
    DefaultMavenRepository,
    Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Scala-Tools Maven2 Releases Repository" at "http://scala-tools.org/repo-releases"
)

libraryDependencies += "play" %% "play" % "2.0-beta"

libraryDependencies += "org.scalaquery" % "scalaquery_2.9.0-1" % "0.9.5"
