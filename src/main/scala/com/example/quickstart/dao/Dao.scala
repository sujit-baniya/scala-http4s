package com.example.quickstart.dao


import cats.effect.IO
import com.example.quickstart.db.DB
import com.example.quickstart.entity.{Company, Employee, Worklist}
import com.example.quickstart.model.{CompanyTable, EmployeeTable, WorklistTable}
import slick.jdbc.MySQLProfile


class Dao(db: MySQLProfile.backend.Database) {
	
	import slick.jdbc.MySQLProfile.api._
	
	def insertWorklist(worklist: Worklist): IO[Int] = DB.run(db, WorklistTable.instance += worklist)
	
	def readAllWorklist(): IO[List[Worklist]] = DB.run(db, WorklistTable.instance.result).map(_.toList)
	
	def insertCompany(company: Company): IO[Int] = DB.run(db, CompanyTable.instance += company)
	
	def readAllCompany(): IO[List[Company]] = DB.run(db, CompanyTable.instance.result).map(_.toList)
	
	def readOnceCompany(id: Long): IO[List[Company]] = DB.run(db, CompanyTable.instance.filter(_.id === id).result).map(_.toList)
	
	def insertEmployee(employee: Employee): IO[Employee] = DB.run(db, {
		def returnObj = (EmployeeTable.instance returning EmployeeTable.instance.map(_.id)).into((obj, id) => obj.copy(id = id))
		returnObj += employee
	})
	
	def readAllEmployee(): IO[List[Employee]] = DB.run(db, EmployeeTable.instance.result).map(_.toList)
	
	def readOnceEmployee(name: String): IO[List[Employee]] = DB.run(db, EmployeeTable.instance.filter(_.name === name).result).map(_.toList)
	
	
	def insertData(): IO[Int] = {
		insertEmployee(Employee(1L, "Stas Stasovich"))
		insertEmployee(Employee(2L, "Ivan Ivanov"))
		insertEmployee(Employee(3L, "Petr Petrov"))
		
		insertCompany(Company(1L, "STASCOMP"))
		insertCompany(Company(2L, "AnotherComp"))
		
		insertWorklist(Worklist(1L, 1L, 1L))
		insertWorklist(Worklist(2L, 1L, 2L))
		insertWorklist(Worklist(3L, 2L, 3L))
	}
}