package com.example.quickstart.entity

import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf


final case class Worklist(id:Long, worklistId:Long, nameId:Long)

object Worklist {
  implicit val worklistEncoder: Encoder[Worklist] = (a: Worklist) => Json.obj(
    ("id", Json.fromLong(a.id)),
    ("worklist_id", Json.fromLong(a.worklistId)),
    ("name_id", Json.fromLong(a.nameId)),
  )

  implicit def worklistEntityEncoder[F[_]]: EntityEncoder[F, Worklist] =
    jsonEncoderOf[Worklist]
}
