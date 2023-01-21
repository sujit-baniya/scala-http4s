package com.example.quickstart

import cats.effect.IO
import com.comcast.ip4s._
import com.example.quickstart.service.Routes
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.middleware.Logger

object QuickstartServer {
	
	def run: IO[Nothing] = {
		val httpApp = (
			Routes.testRoutes()
			).orNotFound
		
		val finalHttpApp = Logger.httpApp(logHeaders = false, logBody = false)(httpApp)
		EmberServerBuilder.default[IO]
			.withHost(ipv4"0.0.0.0")
			.withPort(port"8080")
			.withHttpApp(finalHttpApp)
			.build
	}.useForever
}
