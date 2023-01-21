package com.example.quickstart.db

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._


object DB {
	import cats.effect.IO
	
	val cleardb: MySQLProfile.backend.Database = Database.forConfig("cleardb")
	val mirthdb: jdbc.MySQLProfile.backend.Database = Database.forConfig("mirthdb")
	def run[R](db: MySQLProfile.backend.Database, a: DBIOAction[R, NoStream, Nothing]): IO[R] = IO.fromFuture(IO(db.run(a)))
}