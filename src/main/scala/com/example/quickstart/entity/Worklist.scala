package com.example.quickstart.entity

import cats.effect.IO
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}


final case class Worklist(id: Long, worklistId: Long, nameId: Long)

object Worklist {
	implicit val worklistDecoder: Decoder[Worklist] = deriveDecoder[Worklist]
	implicit val worklistEntityDecoder: EntityDecoder[IO, Worklist] = jsonOf
	implicit val worklistListEntityDecoder: EntityDecoder[IO, List[Worklist]] = jsonOf
	
	implicit val worklistEncoder: Encoder[Worklist] = deriveEncoder[Worklist]
	implicit val worklistEntityEncoder: EntityEncoder[IO, Worklist] = jsonEncoderOf
	implicit val worklistListEntityEncoder: EntityEncoder[IO, List[Worklist]] = jsonEncoderOf
}
