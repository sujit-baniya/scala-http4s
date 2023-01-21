package com.example.quickstart.db

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object DBConnect {
	val cleardb = Database.forConfig("cleardb")
	val mirthdb = Database.forConfig("mirthdb")
	
	def execOnClearDB[R](a: DBIOAction[R, NoStream, Nothing]): Future[R] = cleardb.run(a)
	
	def execOnMirthDB[R](a: DBIOAction[R, NoStream, Nothing]): Future[R] = mirthdb.run(a)
}