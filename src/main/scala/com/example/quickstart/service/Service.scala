package com.example.quickstart.service

import com.example.quickstart.dao.Dao
import org.http4s.HttpRoutes
import cats.effect.IO
import com.example.quickstart.db.DBConnect
import org.http4s.dsl.io._

object Routes {
	def testRoutes(): HttpRoutes[IO] = {
		val db = new Dao(DBConnect.cleardb)
		HttpRoutes.of[IO] {
			case GET -> Root / "text" => Ok(db.readAllWorklist())
			case GET -> Root => Ok(db.readAllWorklist())
			case GET -> Root / "employee" => Ok(db.readAllEmployee())
			case GET -> Root / "company" => Ok(db.readAllCompany())
			case GET -> Root / "hello" / name => Ok("Hello " + name)
		}
	}
}