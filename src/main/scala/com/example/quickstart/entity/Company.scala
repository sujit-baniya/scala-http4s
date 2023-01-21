package com.example.quickstart.entity

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._


final case class Company(id: Long, company: String)

object Company {
	implicit val companyEncoder: Encoder[Company] = deriveEncoder
	implicit val companyDecoder: Decoder[Company] = deriveDecoder
}
