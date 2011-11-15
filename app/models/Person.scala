package models

import play.api.db._
import play.api.Play.current

import org.scalaquery.ql.Query
import org.scalaquery.ql.extended.H2Driver.Implicit._
import org.scalaquery.ql.extended.{ExtendedTable => Table}
import org.scalaquery.session._
import org.scalaquery.session.Database.threadLocalSession
import play.Logger

/**A directory entry for one person. */

case class Person(id: Long, name: String, telephoneNumber: String, fileAs: String, office: String, emailAddress: String)

object Person extends Table[(Long, String, String, String, String, String)]("people") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[Long]("id", O PrimaryKey, O AutoInc)

  def name = column[String]("name", O NotNull)

  def telephoneNumber = column[String]("telephoneNumber")

  def fileAs = column[String]("fileAs")

  def office = column[String]("office")

  def emailAddress = column[String]("emailAddress")

  def * = id ~ name ~ telephoneNumber ~ fileAs ~ office ~ emailAddress

  def values = name ~ telephoneNumber ~ fileAs ~ office ~ emailAddress

  /**Returns the number of entries. */
  def numberOfEntries: Int = database.withSession {
    Query(Person.count).first
  }

}