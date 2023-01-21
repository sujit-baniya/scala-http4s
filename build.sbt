lazy val root = (project in file("."))
	.enablePlugins(LiveReloadPlugin)
	.settings(
		organization := "com.example",
		name := "quickstart",
		version := "0.0.1-SNAPSHOT",
		scalaVersion := "2.13.10",
		libraryDependencies ++= Seq(
			"org.http4s" %% "http4s-ember-server" % "1.0.0-M38",
			"org.http4s" %% "http4s-ember-client" % "1.0.0-M38",
			"org.http4s" %% "http4s-circe" % "1.0.0-M38",
			"org.http4s" %% "http4s-dsl" % "1.0.0-M38",
			"io.circe" %% "circe-generic" % "0.14.3",
			"org.scalameta" %% "munit" % "0.7.29" % Test,
			"org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
			"ch.qos.logback" % "logback-classic" % "1.4.5" % Runtime,
			"org.scalameta" %% "svm-subs" % "20.2.0",
			
			"com.typesafe.slick" %% "slick" % "3.4.1",
			"mysql" % "mysql-connector-java" % "8.0.32",
			"com.typesafe.slick" %% "slick-hikaricp" % "3.4.1"
		),
		addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full),
		addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
		testFrameworks += new TestFramework("munit.Framework")
	)
