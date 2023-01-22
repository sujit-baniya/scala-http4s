package com.example.quickstart.entity

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

final case class Employee(id: Long, name: String)

object Employee {
	implicit val decoder: Decoder[Employee] = deriveDecoder[Employee]
	implicit val entityDecoder: EntityDecoder[IO, Employee] = jsonOf
	implicit val listEntityDecoder: EntityDecoder[IO, List[Employee]] = jsonOf
	
	implicit val encoder: Encoder[Employee] = deriveEncoder[Employee]
	implicit val entityEncoder: EntityEncoder[IO, Employee] = jsonEncoderOf
	implicit val listEntityEncoder: EntityEncoder[IO, List[Employee]] = jsonEncoderOf
}