package controllers

import play.api.mvc._
import models.{People, Person}

/** Handles HTTP requests. */
object Application extends Controller {

  val PageSize = 50

  /** Render one entry. */
  def details(id:Long) = Action {
    Ok(views.html.details(People.find(id)))
  }

  /** Edit one entry. */
  def edit(id:Long) = Action {
    Ok("")
  }

  /** Render one page of results as an HTML page. */
  def index(pageNumber:Int, query:String) = Action {
    Ok(views.html.index(People.page(pageNumber, PageSize, query), query))
  }

  /** Render one page of results as an HTML fragment. */
  def list(pageNumber:Int, query:String) = Action {
    Ok(views.html.indexList(People.page(pageNumber, PageSize, query), query))
  }

  /** Update one entry. */
  def update(id:Long) = Action {
    Ok("")
  }

}