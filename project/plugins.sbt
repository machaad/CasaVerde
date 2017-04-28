// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "1.3-beta3")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.4")