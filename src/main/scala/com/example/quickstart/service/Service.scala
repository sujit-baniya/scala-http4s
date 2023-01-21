package com.example.quickstart.service

import com.example.quickstart.dao.Dao
import org.http4s.HttpRoutes
import cats.effect.IO
import org.http4s.dsl.io._

object Routes {
	def testRoutes(): HttpRoutes[IO] = {
		HttpRoutes.of[IO] {
			case GET -> Root / "text" => Ok(Dao.readAllWorklistF())
			case GET -> Root => Ok(Dao.readAllWorklist())
			case GET -> Root / "employee" => Ok(Dao.readAllEmployee())
			case GET -> Root / "company" => Ok(Dao.readAllCompany())
			case GET -> Root / "hello" / name => Ok("Hello " + name)
		}
	}
}