package com.example.quickstart.entity

import cats.effect.IO
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}



final case class HcpcsFirstEvent(
	                          id: Int,
	                          encounter_uid: Int,
	                          hcpcs_num: String,
	                          hcpcs_qty: Int,
	                          hcpcs_modifier1: Option[String],
	                          hcpcs_modifier2: Option[String],
	                          hcpcs_modifier3: Option[String],
	                          hcpcs_modifier4: Option[String],
	                          hcpcs_provider: Option[String],
	                          userid: String,
	                          user_time_entered: String,
	                          qa_disagree: Int
                          )

final case class HcpcsSecondEvent(
	                           qa_procedure_num: Option[String],
	                           qa_procedure_qty: Option[Int],
	                           qa_procedure_modifier: Option[String],
	                           qa_procedure_provider: Option[String],
	                           qa_note: Option[String],
	                           qa_userid: Option[String],
	                           qa_user_time_entered: Option[String],
	                           work_item_uid: Int,
	                           qa_type: Option[String],
	                           event_dos: Option[String],
	                           patient_status_disagree: Option[Int],
	                           patient_status_uid: Option[Int],
	                           qa_note_patient_status: Option[String],
	                           charge_code_select: Option[String]
                           )

final case class HcpcsEvent(
	                           var hcpcsEvent1: HcpcsFirstEvent,
	                           var hcpcsEvent2: HcpcsSecondEvent)

object HcpcsEvent {
	implicit val decoder: Decoder[HcpcsEvent] = deriveDecoder[HcpcsEvent]
	implicit val entityDecoder: EntityDecoder[IO, HcpcsEvent] = jsonOf
	implicit val listEntityDecoder: EntityDecoder[IO, List[HcpcsEvent]] = jsonOf
	
	implicit val encoder: Encoder[HcpcsEvent] = deriveEncoder[HcpcsEvent]
	implicit val entityEncoder: EntityEncoder[IO, HcpcsEvent] = jsonEncoderOf
	implicit val listEntityEncoder: EntityEncoder[IO, List[HcpcsEvent]] = jsonEncoderOf
}