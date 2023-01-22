package com.example.quickstart.model

import com.example.quickstart.entity.Employee

object EmployeeTable {
	
	import slick.jdbc.MySQLProfile.api._
	
	class EmployeeTable(tag: Tag) extends Table[Employee](tag, None, "Employee") {
		def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
		
		def name = column[String]("name")
		
		override def * = (id, name) <> ((Employee.apply _).tupled, Employee.unapply)
	}
	
	lazy val instance = TableQuery[EmployeeTable]
}
