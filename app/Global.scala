import io.Source
import java.io.File
import models.People
import org.scalaquery.ql.extended.H2Driver.Implicit._
import org.scalaquery.session._
import org.scalaquery.session.Database.threadLocalSession
import play.api._
import db.DB
import scala.math._

object Global extends GlobalSettings {

  /** Intercept application start-up to create the database and load test data. */
  override def onStart(app: Application) {

    Database.forDataSource(DB.getDataSource()(app)) withSession {
      People.ddl.create
      loadTestData
    }
  }

  /** Load test data from a file of names, with one name per line. */
  private def loadTestData {

    val testDataFile = new File("conf/test-data.txt")
    val testDataLines = Source.fromFile(testDataFile).getLines().toList

    for (name <- testDataLines if !name.startsWith("#")) {

      val telephoneNumber = "0" + number(3) + " " + number(6)
      val fileAs = name.substring(name.lastIndexOf(" ") + 1)
      val office = number(1) + "." + number(2)
      val emailAddress = name.replaceAll(" ", ".").toLowerCase() + "@example.org"

      People.values insert (name, telephoneNumber, fileAs, office, emailAddress)
    }
  }

  /** Return a random number with the given number of digits. */
  private def number(digits:Int): String = {
    val limit = pow(10.0, digits)
    ("%0" + digits + ".0f").format(ceil(random * (limit - 1)))
  }
}
