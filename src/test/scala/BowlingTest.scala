import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.Matchers._
import org.scalatest.PropSpec
import scala.language.postfixOps
import org.scalatest.Matchers
import org.scalatest.prop.TableFor2

class BowlingTest extends PropSpec with Matchers {

  def score(t: TableFor2[List[Int], Int]) =
    forAll(t) { (input: List[Int], expected: Int) =>
      // println("==== " + input)
      new BowlingGame().score(input) should be(expected)
    }

  def scoreTextual(t: TableFor2[String, Int]) =
    forAll(t) { (input: String, expected: Int) =>
      // println("==== " + input)
      new BowlingGame().score(input) should be(expected)
    }

  val simplePins = Table(("input", "expected"),
    (List(0, 0), 0), (List(1), 1), (List(1, 3), 4),
    (List(1, 3, 5, 2, 1), 12), (List(1, 0, 5, 0), 6))

  property("a few pins") {
    score(simplePins)
  }

  val shortSpares = Table(("input", "expected"),
    (List(1, 9), 10), (List(1, 9, 0, 0), 10),
    (List(1, 9, 0, 5), 15), (List(1, 9, 3, 5, 0), 21),
    (List(1, 9, 3, 7, 2, 3), 30))

  property("a few spares") {
    score(shortSpares)
  }

  val shortStrikes = Table(("input", "expected"),
    (List(10), 10), (List(10, 0, 0), 10),
    (List(10, 0, 0, 5, 1), 16),
    (List(10, 5, 1), 22))

  property("a few strikes") {
    score(shortStrikes)
  }

  val textual = Table(("input", "expected"),
    ("--", 0), ("1", 1), ("13", 4), ("13521", 12),
    ("1-5-", 6), ("1/", 10), ("1/--", 10), ("1/-5", 15),
    ("1/35-", 21), ("1/3/23", 30), ("X", 10), ("X--", 10),
    ("X--51", 16), ("X51", 22), ("XX42", 46))

  property("textual representation") {
    scoreTextual(textual)
  }

  val normalPlay = Table(("input", "expected"),
    ("1/35XXX45", 103),
    ("11111111112222222222", 30),
    ("--------------------", 0),
    ("1-1----------------1", 3),
    ("9-9-9-9-9-9-9-9-9-9-", 90),
    ("5/11------------3/11", 26),
    // ("919-9-9-9-9-929-9-9-", 93),
    ("9-8/--9-9-9-9-9-9-9-", 82),
    ("--8/1---------------", 12),
    ("--8/-1--------------", 11),
    ("9-X8-9-9-9-9-9-9-9-", 98),
    ("--X81--------------", 28),
    ("--X8-1-------------", 27))

  property("normal games") {
    scoreTextual(normalPlay)
  }

  
val finalFrame = Table(("input", "expected"),
   ("1/35XXX458/X3/23", 160),
   ("1/35XXX458/X3/XX6", 189),
   ("1/35XXX458/X35", 149),
   ("1/35XXX458/X3/X", 173),
   ("XXXXXXXXXXXX", 300),
   ("XXXXXXXXXX12", 274),
   ("1/35XXX458/X3/", 153),
   ("5/5/5/5/5/5/5/5/5/5/5", 150),
   ("9-8/--9-9-9-9-9-9-9/1", 84),
   ("9-X8-9-9-9-9-9-9-X23", 104))

 property("complete games") {
   scoreTextual(finalFrame)
 }
}
