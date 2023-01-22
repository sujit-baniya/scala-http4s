package com.example.quickstart.model

import com.example.quickstart.entity.Company

import slick.jdbc.MySQLProfile.api._

class CompanyTable(tag: Tag) extends BaseTable[Company](tag, None, "Company") {
	def company = column[String]("company")
	
	override def * = (id, company) <> ((Company.apply _).tupled, Company.unapply)
}

object CompanyTable {
	
	lazy val instance = TableQuery[CompanyTable]
}
