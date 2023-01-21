package com.example.quickstart.entity

import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

final case class Employee(id:Long, name:String)
object Employee {
  implicit val employeeEncoder: Encoder[Employee] = (a: Employee) => Json.obj(
    ("id", Json.fromLong(a.id)),
    ("name", Json.fromString(a.name)),
  )

  implicit def employeeEntityEncoder[F[_]]: EntityEncoder[F, Employee] =
    jsonEncoderOf[Employee]
}