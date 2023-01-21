package com.example.quickstart

import cats.effect.IOApp

object Main extends IOApp.Simple {
	val run = QuickstartServer.run
}
