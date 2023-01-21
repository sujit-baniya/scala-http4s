package com.example.quickstart.entity

import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}


final case class Worklist(id: Long, worklistId: Long, nameId: Long)

object Worklist {
	implicit val worklistEncoder: Encoder[Worklist] = deriveEncoder
	implicit val worklistDecoder: Decoder[Worklist] = deriveDecoder
}
