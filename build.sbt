name := "bowling"

version := "0.1"

scalaVersion := "2.11.8"

EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE18)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)