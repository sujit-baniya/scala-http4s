package com.example.quickstart.model

import com.example.quickstart.entity.Company

object CompanyTable {
	
	import slick.jdbc.MySQLProfile.api._
	
	class CompanyTable(tag: Tag) extends Table[Company](tag, None, "Company") {
		def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
		
		def company = column[String]("company")
		
		override def * = (id, company) <> ((Company.apply _).tupled, Company.unapply)
	}
	lazy val instance = TableQuery[CompanyTable]
}
