name := "SnakeGame"

version := "0.1"

scalaVersion := "2.12.14"


scalacOptions += "-Ypartial-unification"

val enumeratumVersion = "1.7.0"
val catsCoreVersion   = "2.6.1"

val scalaTest = "3.2.9"

libraryDependencies ++= Seq(
  "com.beachape"  %% "enumeratum" % enumeratumVersion,
  "org.typelevel" %% "cats-core"  % catsCoreVersion,

  "org.scalatest" %% "scalatest" % scalaTest % "test"
)
