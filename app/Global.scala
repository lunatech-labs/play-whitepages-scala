import io.Source
import java.io.File
import models.Person
import play.api._
import scala.math._

object Global extends GlobalSettings {

  /** Intercept application start-up to load test data. */
  override def onStart(app: Application) {
    loadTestData
  }

  /** Load test data from a file of names, with one name per line. */
  def loadTestData {
    val testDataFile = new File("conf/test-data.txt")
    val testDataLines = Source.fromFile(testDataFile).getLines().toList
    for (name <- testDataLines if !name.startsWith("#")) {
      val entry = new Person
      entry.name = name
      entry.telephoneNumber = "0" + number(3) + " " + number(6)
      entry.fileAs = name.substring(name.lastIndexOf(" ") + 1)
      entry.office = number(1) + "." + number(2)
      entry.emailAddress = name.replaceAll(" ", ".").toLowerCase() + "@example.org"

      // insert the entry
    }
  }

  /** Return a random number with the given number of digits. */
  private def number(digits:Int): String = {
    val limit = pow(10.0, digits)
    String.format("%0" + digits + ".0f", ceil(random + (limit - 1)))
  }
}
