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

    private def dropNthPosition(numElementsInCircle: Int)(position: Int): Option[Circle[Int]] =
        Circle.withIntElements(numElementsInCircle).removeNthPositionFromCircle(position)

    private def dropNthPositionAndTake
    (numElementsInCircle: Int)(position: Int, elementsToTake: Int): Option[Seq[Int]] = {
        dropNthPosition(numElementsInCircle)(position).map(_.stream.take(elementsToTake))
    }

    private val dropPositionFromCircleOf5AndTake = dropNthPositionAndTake(5)_

    "Trying to create an empty circle" should "give us an exception" in {
        intercept[IllegalArgumentException] {
            Circle(Seq())
        }
    }

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

    "Removing the 3th position in a 5 elements circle and taking 7" should "return the sequence 1, 2, 4, 5, 1, 2, 4" in {
        assert(dropPositionFromCircleOf5AndTake(3, 7).contains(Seq(1, 2, 4, 5, 1, 2, 4)))
    }
}
