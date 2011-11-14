package models

import play.api.db._
import play.api.Play.current

import org.scalaquery.ql.extended.{ExtendedTable => Table}
import org.scalaquery.session._

/** A directory entry for one person. */

case class Person(id:Long, name:String, telephoneNumber:String, fileAs:String, office:String, emailAddress:String)

object Person extends Table[(Long, String, String, String, String, String)]("people") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[Long]("id", O PrimaryKey, O AutoInc)
  def name = column[String]("name", O NotNull)
  def telephoneNumber = column[String]("telephoneNumber")
  def fileAs = column[String]("fileAs")
  def office = column[String]("office")
  def emailAddress = column[String]("emailAddress")
  def * = id ~ name ~ telephoneNumber ~ fileAs ~ office ~ emailAddress

//  def findAll = database.withSession { implicit db:Session =>
//      (for(t <- this) yield t.id ~ t.name).list
//  }

}