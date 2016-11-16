package me.marianonavas.josephus

import org.scalatest.FlatSpec

/**
  *
  */
class CircleSpec extends FlatSpec {

    private val takeTestWith5Elements = takeTest(5)_

    private val takeTestWith3Elements = takeTest(3)_

    private def takeTest(numElementsInCircle: Int)(elemsToTake: Int, expectedSequence: Seq[Int]) = {
        assert(Circle.withIntElements(numElementsInCircle).stream.take(elemsToTake).toList == expectedSequence)
    }

    private def dropNthPosition(numElementsInCircle: Int)(position: Int): Option[Stream[Int]] = {
        val maybeResultingCircle = Circle.withIntElements(numElementsInCircle).removeNthPositionFromCircle(position)
        maybeResultingCircle.map(_.stream)
    }

    private val dropFrom5ElementsCircle = dropNthPosition(5)(_)

    "Taking 3 of 3 elements" should "return the sequence 1, 2, 3" in {
        takeTestWith3Elements(3, Seq(1, 2, 3))
    }

    "Taking 1 of 3 elements" should "return the sequence 1" in {
        takeTestWith3Elements(1, Seq(1))
    }

    "Taking 5 of 3 elements" should "return the sequence 1, 2, 3, 1, 2" in {
        takeTestWith3Elements(5, Seq(1, 2, 3, 1, 2))
    }

    "Taking 3 of 5 elements" should "return the sequence 1, 2, 3" in {
        takeTestWith5Elements(3, Seq(1, 2, 3))
    }

    "Taking 5 of 5 elements" should "return the sequence 1, 2, 3, 4, 5" in {
        takeTestWith5Elements(5, Seq(1, 2, 3, 4, 5))
    }

    "Taking 7 of 5 elements" should "return the sequence 1, 2, 3, 4, 5, 1, 2" in {
        takeTestWith5Elements(7, Seq(1, 2, 3, 4, 5, 1, 2))
    }

    "Taking 10 of 5 elements" should "return the sequence 1, 2, 3, 4, 5, 1, 2, 3, 4, 5" in {
        takeTestWith5Elements(10, Seq(1, 2, 3, 4, 5, 1, 2, 3, 4, 5))
    }

    "Removing the 3th position in a 5 elements circle" should "return the sequence 4, 5, 1, 2" in {
        val maybeStream = dropFrom5ElementsCircle(3)
        val maybeInts = maybeStream.map(_.take(4))
        assert(maybeInts.contains(Seq(4, 5, 1, 2)))
    }

    "Removing the 7th position in a 5 elements circle" should "return the sequence 3, 4, 5, 1" in {
        assert(dropFrom5ElementsCircle(7).map(_.take(4)).contains(Seq(3, 4, 5, 1)))
    }

    "Removing the only position from a 1 element circle" should "return None" in {
        val circle = Circle(List(1))
        val maybeCircle = circle.removeNthPositionFromCircle(1)

        assert(maybeCircle.isEmpty)
    }
}
