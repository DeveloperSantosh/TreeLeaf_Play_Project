import scala.collection.JavaConverters._

name := """my_project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.8"

libraryDependencies += guice

libraryDependencies ++= Seq(
  javaJdbc,
  jdbc,
  evolutions,
  javaCore,
  javaJpa,
  javaForms,
  "mysql" % "mysql-connector-java" % "8.0.25",

  "org.hibernate" % "hibernate-entitymanager" % "5.4.33",
  "javax.persistence" % "persistence-api" % "1.0.2",
)
playEnhancerEnabled := false
PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"