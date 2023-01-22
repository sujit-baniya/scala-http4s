package com.example.quickstart.entity

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}


final case class Worklist(id: Long, worklistId: Long, nameId: Long) extends BaseEntity

object Worklist {
	implicit val decoder: Decoder[Worklist] = deriveDecoder[Worklist]
	implicit val entityDecoder: EntityDecoder[IO, Worklist] = jsonOf
	implicit val listEntityDecoder: EntityDecoder[IO, List[Worklist]] = jsonOf
	
	implicit val encoder: Encoder[Worklist] = deriveEncoder[Worklist]
	implicit val entityEncoder: EntityEncoder[IO, Worklist] = jsonEncoderOf
	implicit val listEntityEncoder: EntityEncoder[IO, List[Worklist]] = jsonEncoderOf
}
