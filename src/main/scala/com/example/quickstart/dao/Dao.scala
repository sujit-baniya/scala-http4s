package com.example.quickstart.dao


import com.example.quickstart.db.DBConnect
import com.example.quickstart.model.SlickTables
import com.example.quickstart.entity.{Company, Employee, Worklist}

import java.util.concurrent.Executors
import scala.collection.Seq
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


object PrivateExecutionContext{
  val executor = Executors.newFixedThreadPool(4)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)
}


object Dao {

  import PrivateExecutionContext._
  import slick.jdbc.MySQLProfile.api._

  private def insertWorklist(worklist: Worklist): Unit = {

    val queryDescription = SlickTables.worklistTable += worklist

    val futureId :Future[Int] =  DBConnect.execOnClearDB(queryDescription)
    futureId.onComplete{
      case Success(newWorklistId) => println(s"Add $newWorklistId")
      case Failure(exception) => println(s"Err: $exception")
    }
  }

  def readAllWorklist(): Unit={
    val resFuture:Future[Seq[Worklist]] = DBConnect.execOnClearDB(SlickTables.worklistTable.result)
    resFuture.onComplete {
      case Success(worklist) => println(s"Fetched ${worklist.mkString(",")}")
      case Failure(exception) => println(s"Err: $exception")
    }
  }

  def insertCompany(company: Company): Unit = {

    val queryDescription = SlickTables.companyTable += company

    val futureId: Future[Int] = DBConnect.execOnClearDB(queryDescription)
    futureId.onComplete {
      case Success(newCompanyId) => println(s"Add $newCompanyId")
      case Failure(exception) => println(s"Err: $exception")
    }
  }

  def readAllCompany(): Unit = {
    val resFuture: Future[Seq[Company]] = DBConnect.execOnClearDB(SlickTables.companyTable.result)
    resFuture.onComplete {
      case Success(company) => println(s"Fetched ${company.mkString(",")}")
      case Failure(exception) => println(s"Err: $exception")
    }
  }

  def readOnceCompany(id:  Long): Unit = {
    val resFuture: Future[Seq[Company]] = DBConnect.execOnClearDB(SlickTables.companyTable.filter(_.id === id).result)
    resFuture.onComplete {
      case Success(id) => println(s"Fetched ${id.mkString(",")}")
      case Failure(exception) => println(s"Err: $exception")
    }
  }


  //EMPLOYEE:

  def insertEmployee(employee: Employee): Unit = {

    val queryDescription = SlickTables.employeeTable += employee

    val futureId: Future[Int] = DBConnect.execOnClearDB(queryDescription)
    futureId.onComplete {
      case Success(newEmployeeId) => println(s"Add $newEmployeeId")
      case Failure(exception) => println(s"Err: $exception")
    }
  }

  def readAllEmployee(): Future[scala.Seq[Employee]] = DBConnect.execOnClearDB(SlickTables.employeeTable.result)

  def readOnceEmployee(name: String): Unit = {
    val resFuture: Future[Seq[Employee]] = DBConnect.execOnClearDB(SlickTables.employeeTable.filter(_.name === name).result)
    resFuture.onComplete {
      case Success(name) => println(s"Fetched ${name.mkString(",")}")
      case Failure(exception) => println(s"Err: $exception")
    }
  }


  def insertData(): Unit = {
    insertEmployee(Employee(1L, "Stas Stasovich"))
    insertEmployee(Employee(2L, "Ivan Ivanov"))
    insertEmployee(Employee(3L, "Petr Petrov"))

    insertCompany(Company(1L, "STASCOMP"))
    insertCompany(Company(2L, "AnotherComp"))

    insertWorklist(Worklist(1L, 1L, 1L))
    insertWorklist(Worklist(2L, 1L, 2L))
    insertWorklist(Worklist(3L, 2L, 3L))    //I know that the fst argument auto incrementing (P.S. Its just for me)
  }
}