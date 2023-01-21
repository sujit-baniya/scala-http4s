package com.example.quickstart.entity

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}


final case class Company(id: Long, company: String)

object Company {
	implicit val companyDecoder: Decoder[Company] = deriveDecoder[Company]
	implicit val companyEntityDecoder: EntityDecoder[IO, Company] = jsonOf
	
	implicit val companyEncoder: Encoder[Company] = deriveEncoder[Company]
	implicit val companyEntityEncoder: EntityEncoder[IO, Company] = jsonEncoderOf
}
