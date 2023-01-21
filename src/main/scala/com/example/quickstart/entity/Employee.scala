package com.example.quickstart.entity

import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

final case class Employee(id:Long, name:String)
object Employee {
  implicit val employeeEncoder: Encoder[Employee] = deriveEncoder
  implicit val employeeDecoder: Decoder[Employee] = deriveDecoder
}