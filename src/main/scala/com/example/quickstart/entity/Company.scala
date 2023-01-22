package com.example.quickstart.entity

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}


final case class Company(id: Long, company: String) extends BaseEntity

object Company {
	implicit val decoder: Decoder[Company] = deriveDecoder[Company]
	implicit val entityDecoder: EntityDecoder[IO, Company] = jsonOf
	implicit val listEntityDecoder: EntityDecoder[IO, List[Company]] = jsonOf
	
	implicit val encoder: Encoder[Company] = deriveEncoder[Company]
	implicit val entityEncoder: EntityEncoder[IO, Company] = jsonEncoderOf
	implicit val listEntityEncoder: EntityEncoder[IO, List[Company]] = jsonEncoderOf
}
