package me.marianonavas.josephus

import org.scalatest.FlatSpec

/**
  *
  */
class CircleSpec extends FlatSpec {
    import Circle.createInitialCircle

    private val doTestWith5Elements = doTest(5)_

    private val doTestWith3Elements = doTest(3)_

    private def doTest(elementsInCircle: Int)(elemsToTake: Int, expectedSequence: List[Int]) = {
        assert(createInitialCircle(elementsInCircle).take(elemsToTake).toList == expectedSequence)
    }

    "Taking 3 of 3 elements" should "return the sequence 1, 2, 3" in {
        doTestWith3Elements(3, List(1, 2, 3))
    }

    "Taking 1 of 3 elements" should "return the sequence 1" in {
        doTestWith3Elements(1, List(1))
    }

    "Taking 5 of 3 elements" should "return the sequence 1, 2, 3, 1, 2" in {
        doTestWith3Elements(5, List(1, 2, 3, 1, 2))
    }

    "Taking 3 of 5 elements" should "return the sequence 1, 2, 3" in {
        doTestWith5Elements(3, List(1, 2, 3))
    }

    "Taking 5 of 5 elements" should "return the sequence 1, 2, 3, 4, 5" in {
        doTestWith5Elements(5, List(1, 2, 3, 4, 5))
    }

    "Taking 7 of 5 elements" should "return the sequence 1, 2, 3, 4, 5, 1, 2" in {
        doTestWith5Elements(7, List(1, 2, 3, 4, 5, 1, 2))
    }

    "Taking 10 of 5 elements" should "return the sequence 1, 2, 3, 4, 5, 1, 2, 3, 4, 5" in {
        doTestWith5Elements(10, List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5))
    }
}
