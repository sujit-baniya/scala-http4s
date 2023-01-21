package com.example.quickstart.dao


import cats.effect.IO
import com.example.quickstart.db.DBConnect
import com.example.quickstart.model.SlickTables
import com.example.quickstart.entity.{Company, Employee, Worklist}

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext



object PrivateExecutionContext {
	val executor = Executors.newFixedThreadPool(4)
	implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)
}


object Dao {
	import PrivateExecutionContext._
	import slick.jdbc.MySQLProfile.api._
	
	private def insertWorklist(worklist: Worklist): IO[Int] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.worklistTable += worklist)
		))
	}
	
	def readAllWorklist(): IO[List[Worklist]] =
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.worklistTable.result).map(_.toList)
		))
	
	def insertCompany(company: Company): IO[Int] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.companyTable += company)
		))
	}
	
	def readAllCompany(): IO[List[Company]] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.companyTable.result).map(_.toList)
		))
	}
	
	def readOnceCompany(id: Long): IO[List[Company]] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.companyTable.filter(_.id === id).result).map(_.toList)
		))
	}
	
	
	//EMPLOYEE:
	
	def insertEmployee(employee: Employee): IO[Int] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.employeeTable += employee)
		))
	}
	
	def readAllEmployee(): IO[List[Employee]] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.employeeTable.result).map(_.toList)
		))
	}
	
	def readOnceEmployee(name: String): IO[List[Employee]] = {
		IO.fromFuture(IO(
			DBConnect.execOnClearDB(SlickTables.employeeTable.filter(_.name === name).result).map(_.toList)
		))
	}
	
	
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