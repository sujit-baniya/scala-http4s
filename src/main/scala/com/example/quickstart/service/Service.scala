package com.example.quickstart.service

import cats.effect.Sync
import com.example.quickstart.dao.Dao
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes

class Service[F[_] : Sync]() extends Http4sDsl[F] {
	val routes: HttpRoutes[F] = HttpRoutes.of[F] {
		case GET -> Root => Ok(Dao.readAllWorklist())
		case GET -> Root / "employee" => Ok(Dao.readAllEmployee())
		case GET -> Root / "company" => Ok(Dao.readAllCompany())
		case GET -> Root / "hello" / name => Ok("Hello " + name)
	}
}
