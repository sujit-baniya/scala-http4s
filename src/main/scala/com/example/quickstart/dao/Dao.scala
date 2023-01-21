package com.example.quickstart.dao


import cats.effect.IO
import com.example.quickstart.db.DB
import com.example.quickstart.model.SlickTables._
import com.example.quickstart.entity.{Company, Employee, Worklist}
import slick.jdbc.MySQLProfile


class Dao(db: MySQLProfile.backend.Database) {
	
	import slick.jdbc.MySQLProfile.api._
	
	def insertWorklist(worklist: Worklist): IO[Int] = DB.run(db, worklistTable += worklist)
	
	def readAllWorklist(): IO[List[Worklist]] = DB.run(db, worklistTable.result).map(_.toList)
	
	def insertCompany(company: Company): IO[Int] = DB.run(db, companyTable += company)
	
	def readAllCompany(): IO[List[Company]] = DB.run(db, companyTable.result).map(_.toList)
	
	def readOnceCompany(id: Long): IO[List[Company]] = DB.run(db, companyTable.filter(_.id === id).result).map(_.toList)
	
	def insertEmployee(employee: Employee): IO[Int] = DB.run(db, employeeTable += employee)
	
	def readAllEmployee(): IO[List[Employee]] = DB.run(db, employeeTable.result).map(_.toList)
	
	def readOnceEmployee(name: String): IO[List[Employee]] = DB.run(db, employeeTable.filter(_.name === name).result).map(_.toList)
	
	
	def insertData(): IO[Int] = {
		insertEmployee(Employee(1L, "Stas Stasovich"))
		insertEmployee(Employee(2L, "Ivan Ivanov"))
		insertEmployee(Employee(3L, "Petr Petrov"))
		
		insertCompany(Company(1L, "STASCOMP"))
		insertCompany(Company(2L, "AnotherComp"))
		
		insertWorklist(Worklist(1L, 1L, 1L))
		insertWorklist(Worklist(2L, 1L, 2L))
		insertWorklist(Worklist(3L, 2L, 3L)) //I know that the fst argument auto incrementing (P.S. Its just for me)
	}
}