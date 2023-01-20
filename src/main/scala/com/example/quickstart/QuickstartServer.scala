package com.example.quickstart

import cats.effect.Async
import cats.syntax.all._
import com.comcast.ip4s._
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._

object QuickstartServer {

  def run[F[_]: Async]: F[Nothing] = {
    for {
      client <- EmberClientBuilder.default[F].build
      helloWorldAlg = HelloWorld.impl[F]
      jokeAlg = Jokes.impl[F](client)

      httpApp = (
        QuickstartRoutes.helloWorldRoutes[F](helloWorldAlg) <+>
        QuickstartRoutes.jokeRoutes[F](jokeAlg)
      ).orNotFound

      // With Middlewares in place
      // finalHttpApp = Logger.httpApp(logHeaders = false, logBody = false)(httpApp)

      _ <- 
        EmberServerBuilder.default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(httpApp)
          .build
    } yield ()
  }.useForever
}
