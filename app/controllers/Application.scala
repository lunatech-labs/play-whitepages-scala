package controllers

import play.api.mvc._
import models.{People, Person}

/** Handles HTTP requests. */
object Application extends Controller {
  
  /** Render one entry. */
  def details(id:Long) = Action {
    Ok("")
  }

  /** Edit one entry. */
  def edit(id:Long) = Action {
    Ok("")
  }

  /** Render one page of results as an HTML page. */
  def index(page:Int, query:String) = Action {
    Ok(views.html.index(People.page, query))
  }

  /** Render one page of results as an HTML fragment. */
  def list(page:Int, query:String) = Action {
    Ok("")
  }

  /** Update one entry. */
  def update(id:Long) = Action {
    Ok("")
  }

}