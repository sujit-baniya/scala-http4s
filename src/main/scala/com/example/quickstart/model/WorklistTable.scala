package com.example.quickstart.model

import com.example.quickstart.entity.Worklist

object WorklistTable {
	
	import slick.jdbc.MySQLProfile.api._
	
	class WorklistTable(tag: Tag) extends BaseTable[Worklist](tag, None, "Worklist") {
		override def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
		
		def nameId = column[Long]("name")
		
		
		def companyId = column[Long]("company")
		
		def company = foreignKey("company_fk", companyId, CompanyTable.instance)(_.id, onDelete = ForeignKeyAction.Cascade)
		
		def name = foreignKey("name_fk", nameId, EmployeeTable.instance)(_.id, onDelete = ForeignKeyAction.Cascade)
		
		override def * = (id, companyId, nameId) <> ((Worklist.apply _).tupled, Worklist.unapply)
	}
	
	lazy val instance = TableQuery[WorklistTable]
	
}
