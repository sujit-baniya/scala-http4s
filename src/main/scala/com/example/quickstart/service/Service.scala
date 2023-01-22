package com.example.quickstart.service

import com.example.quickstart.dao.Dao
import org.http4s.HttpRoutes
import cats.effect.IO
import com.example.quickstart.db.DB
import com.example.quickstart.entity.Employee
import org.http4s.dsl.io._

object Routes {
	def testRoutes(): HttpRoutes[IO] = {
		val db = new Dao(DB.cleardb)
		HttpRoutes.of[IO] {
			case GET -> Root => Ok(db.readAllWorklist())
			case GET -> Root / "employee" => Ok(db.readAllEmployee())
			case req@POST -> Root / "employee" =>
				for {
					employee <- req.as[Employee]
					resp <-  Ok(db.insertEmployee(employee))
				} yield resp
			case GET -> Root / "company" => Ok(db.readAllCompany())
			case GET -> Root / "hello" / name => Ok("Hello " + name)
		}
	}
}