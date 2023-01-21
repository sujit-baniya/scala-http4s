package com.example.quickstart.model

import com.example.quickstart.entity.{Company, Employee, Worklist}

object SlickTables {
	
	import slick.jdbc.MySQLProfile.api._
	
	class CompanyTable(tag: Tag) extends Table[Company](tag, None, "Company") {
		def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
		
		def company = column[String]("company")
		
		override def * = (id, company) <> ((Company.apply _).tupled, Company.unapply)
	}
	
	
	class EmployeeTable(tag: Tag) extends Table[Employee](tag, None, "Employee") {
		def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
		
		def name = column[String]("name")
		
		override def * = (id, name) <> ((Employee.apply _).tupled, Employee.unapply)
	}
	
	lazy val companyTable = TableQuery[CompanyTable]
	lazy val employeeTable = TableQuery[EmployeeTable]
	
	class WorklistTable(tag: Tag) extends Table[Worklist](tag, None, "Worklist") {
		def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
		
		def nameId = column[Long]("name")
		
		
		def companyId = column[Long]("company")
		
		def company = foreignKey("company_fk", companyId, companyTable)(_.id, onDelete = ForeignKeyAction.Cascade)
		
		def name = foreignKey("name_fk", nameId, employeeTable)(_.id, onDelete = ForeignKeyAction.Cascade)
		
		override def * = (id, companyId, nameId) <> ((Worklist.apply _).tupled, Worklist.unapply)
	}
	
	lazy val worklistTable = TableQuery[WorklistTable]
	
}
