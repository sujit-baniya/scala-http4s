package com.example.quickstart.entity

import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf


final case class Company(id:Long, company:String)
object Company {
  implicit val companyEncoder: Encoder[Company] = (a: Company) => Json.obj(
    ("id", Json.fromLong(a.id)),
    ("company", Json.fromString(a.company)),
  )

  implicit def companyEntityEncoder[F[_]]: EntityEncoder[F, Company] =
    jsonEncoderOf[Company]
}
