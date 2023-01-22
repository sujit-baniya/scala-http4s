package com.example.quickstart.model

import com.example.quickstart.entity.BaseEntity
import slick.jdbc.MySQLProfile.api._

abstract class BaseTable[E <: BaseEntity](tag: Tag, schemaName: Option[String], tableName: String) extends Table[E](tag, schemaName, tableName) {
	def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
}