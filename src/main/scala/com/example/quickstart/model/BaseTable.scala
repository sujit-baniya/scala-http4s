package com.example.quickstart.model

import slick.jdbc.MySQLProfile.api._

abstract class BaseTable[T](tag: Tag, schemaName: Option[String], tableName: String)
	extends Table[T](tag, schemaName, tableName) {
	def id: Rep[Long] = column[Long]("Id", O.PrimaryKey, O.AutoInc)
}