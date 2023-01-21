package com.example.quickstart.entity

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

final case class Employee(id: Long, name: String)

object Employee {
	implicit val employeeDecoder: Decoder[Employee] = deriveDecoder[Employee]
	implicit val employeeEntityDecoder: EntityDecoder[IO, Employee] = jsonOf
	
	implicit val employeeEncoder: Encoder[Employee] = deriveEncoder[Employee]
	implicit val employeeEntityEncoder: EntityEncoder[IO, Employee] = jsonEncoderOf
}