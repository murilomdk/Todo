name := """ToDo"""
organization := "com.murilo"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

playEbeanDebugLevel := 4

libraryDependencies ++= Seq(
	guice,
	javaJpa,
	javaJdbc,
	javaWs,
	"mysql" % "mysql-connector-java" % "8.0.16"	,
	"org.hibernate" % "hibernate-entitymanager" % "5.4.10.Final"
	)


EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)
PlayKeys.externalizeResources := false

