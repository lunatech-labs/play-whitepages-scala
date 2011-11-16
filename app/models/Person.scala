package models

import play.api.db._
import play.api.Play.current

import org.scalaquery.ql.Query
import org.scalaquery.ql.extended.H2Driver.Implicit._
import org.scalaquery.ql.extended.{ExtendedTable => Table}
import org.scalaquery.session._
import org.scalaquery.session.Database.threadLocalSession

/** A directory entry for one person. */
case class Person(id: Long, name: String, telephoneNumber: String, fileAs: String, office: String, emailAddress: String)

/** Data access helper for entries. */
object People extends Table[(Long, String, String, String, String, String)]("people") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  // Columns…

  def id = column[Long]("id", O PrimaryKey, O AutoInc)

  def name = column[String]("name", O NotNull)

  def telephoneNumber = column[String]("telephoneNumber")

  def fileAs = column[String]("fileAs")

  def office = column[String]("office")

  def emailAddress = column[String]("emailAddress")

  // Projections…

  def * = id ~ name ~ telephoneNumber ~ fileAs ~ office ~ emailAddress

  def mapped = * <> (Person, Person.unapply _)

  def values = name ~ telephoneNumber ~ fileAs ~ office ~ emailAddress

  /** Returns the entry with the given ID. */
  def find(id:Long) : Person = database.withSession {
    val query = for(person <- People if person.id === id) yield person.mapped
    query.first
  }

  /** Returns the total number of entries. */
  def numberOfEntries:Int = database.withSession {
    Query(People.count).first
  }

  /** Returns one page of entries. */
  def page(pageNumber:Int, pageSize:Int, search:String) : Seq[Person] = database.withSession {
    val query = for(person <- People if person.name.toLowerCase like "%" + search.toLowerCase + "%") yield person.mapped
    val startIndex = (pageNumber - 1) * pageSize
    // TODO: is it possible to do a paged query in the database?
    query.list.slice(startIndex, startIndex + pageSize)
  }

}