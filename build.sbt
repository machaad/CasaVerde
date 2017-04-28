import play.PlayJava

val appName         = "Play2Angular"
val appVersion      = "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaEbean,
  "be.objectify" % "deadbolt-java_2.11" % "2.3.3",
  "com.codiform" % "moo-core" % "2.0"
)

javaOptions in Test += "-Dconfig.file=conf/application.test.conf"