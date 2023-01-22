package com.example.quickstart.model


import com.example.quickstart.entity.{HcpcsEvent, HcpcsFirstEvent, HcpcsSecondEvent}
import slick.jdbc.MySQLProfile.api._

import java.sql.Timestamp

class TblEventHcpcsFac(tag: Tag) extends BaseTable[HcpcsEvent](tag, None, "tbl_event_hcpcs_fac") {
	def id = column[Int]("id", O.AutoInc)
	
	def encounter_uid = column[Int]("encounter_uid")
	
	def hcpcs_num = column[String]("hcpcs_num")
	
	def code = hcpcs_num
	
	def hcpcs_qty = column[Int]("hcpcs_qty")
	
	def hcpcs_modifier1 = column[Option[String]]("hcpcs_modifier1")
	
	def hcpcs_modifier2 = column[Option[String]]("hcpcs_modifier2")
	
	def hcpcs_modifier3 = column[Option[String]]("hcpcs_modifier3")
	
	def hcpcs_modifier4 = column[Option[String]]("hcpcs_modifier4")
	
	def hcpcs_provider = column[Option[String]]("hcpcs_provider")
	
	def userid = column[String]("userid")
	
	def user_time_entered = column[String]("user_time_entered")
	
	def user_time_entered_utc = column[Option[Timestamp]]("user_time_entered_utc")
	
	def qa_disagree = column[Int]("qa_disagree")
	
	def qa_procedure_num = column[Option[String]]("qa_procedure_num")
	
	def qa_procedure_qty = column[Option[Int]]("qa_procedure_qty")
	
	def qa_procedure_modifier = column[Option[String]]("qa_procedure_modifier")
	
	def qa_procedure_provider = column[Option[String]]("qa_procedure_provider")
	
	def qa_note = column[Option[String]]("qa_note")
	
	def qa_userid = column[Option[String]]("qa_userid")
	
	def qa_user_time_entered = column[Option[String]]("qa_user_time_entered")
	
	def work_item_uid = column[Int]("work_item_uid")
	
	def qa_type = column[Option[String]]("qa_type")
	
	def event_dos = column[Option[String]]("event_dos")
	
	def patient_status_disagree = column[Option[Int]]("patient_status_disagree")
	
	def qa_note_patient_status = column[Option[String]]("charge_code_select")
	
	def charge_code_select = column[Option[String]]("qa_note_patient_status")
	
	def patient_status_uid = column[Option[Int]]("patient_status_uid")
	
	def * = (
		(id, encounter_uid, hcpcs_num, hcpcs_qty, hcpcs_modifier1, hcpcs_modifier2, hcpcs_modifier3, hcpcs_modifier4,
			hcpcs_provider, userid, user_time_entered, qa_disagree), (qa_procedure_num, qa_procedure_qty,
		qa_procedure_modifier, qa_procedure_provider, qa_note, qa_userid,
		qa_user_time_entered, work_item_uid, qa_type, event_dos, patient_status_disagree, patient_status_uid, qa_note_patient_status, charge_code_select)).shaped <> ( {
		case (hcpcsFirstEvent, hcpcsSecondEvent) =>
			HcpcsEvent(
				HcpcsFirstEvent.tupled.apply(hcpcsFirstEvent),
				HcpcsSecondEvent.tupled.apply(hcpcsSecondEvent)
			)
	}, {
		u: HcpcsEvent =>
			def f1(p: HcpcsFirstEvent) = HcpcsFirstEvent.unapply(p).get
			
			def f2(p: HcpcsSecondEvent) = HcpcsSecondEvent.unapply(p).get
			
			Some(f1(u.hcpcsEvent1), f2(u.hcpcsEvent2))
	}
	)
	
}

