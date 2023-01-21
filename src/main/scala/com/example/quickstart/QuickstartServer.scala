package com.example.quickstart

import cats.effect.Async
import com.comcast.ip4s._
import com.example.quickstart.service.Service
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.middleware.Logger

object QuickstartServer {
	
	def run[F[_] : Async]: F[Nothing] = {
		val httpApp = (
			new Service[F]().routes
          ).orNotFound
		
		val finalHttpApp = Logger.httpApp(logHeaders = false, logBody = false)(httpApp)
		EmberServerBuilder.default[F]
			.withHost(ipv4"0.0.0.0")
			.withPort(port"8080")
			.withHttpApp(finalHttpApp)
			.build
	}.useForever
}
