package controllers

import models.{People, Person}
import play.api.mvc._
import play.api.data._
import play.api.Logger

/** Handles HTTP requests. */
object Application extends Controller {

  val PageSize = 50

  val form = Form(
    of(Person.apply _)(
      "id" -> ignored(0),
      "name" -> requiredText,
      "telephoneNumber" -> text,
      "fileAs" -> text,
      "office" -> text,
      "emailAddress" -> text
    )
  )

  val Home = Redirect(routes.Application.index(1, ""))

  /** Render one entry. */
  def details(id: Long) = Action {
    People.find(id) match {
      case None => NotFound("Invalid ID")
      case Some(person) => Ok(views.html.details(person))
    }
  }

  /** Edit one entry. */
  def edit(id: Long) = Action {

    People.find(id) match {
      case None => NotFound("Invalid ID")
      case Some(person) => {
        Ok(views.html.edit(person, form.fill(person)))
      }
    }
  }

  /** Render one page of results as an HTML page. */
  def index(pageNumber: Int, query: String) = Action {
    Ok(views.html.index(People.page(pageNumber, PageSize, query), query))
  }

  /** Render one page of results as an HTML fragment. */
  def list(pageNumber: Int, query: String) = Action {
    Ok(views.html.indexList(People.page(pageNumber, PageSize, query), query))
  }

  /** Update one entry. */
  def update(id: Long) = Action {
    implicit request =>
      Logger.debug("update")
    form.bindFromRequest.fold(
      formWithErrors => {
        Logger.debug("errors")
        People.find(id) match {
          case None => NotFound("Invalid ID")
          case Some(person) => BadRequest(views.html.edit(person, formWithErrors))
        }
      },
      person => {
        Logger.debug("People.update")
        People.update(id, person)
        Redirect(routes.Application.index(1, ""))
      }
    )
  }

}