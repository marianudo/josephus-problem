package me.marianonavas.josephus

/**
  *
  */
case class Circle[T](elementsInCircle: List[T]) {

    private def makeCircleForElements(elements: List[T]): Stream[T] =
        elements.foldLeft(Stream(): Stream[T]) {
            (acc, value) => value #:: acc
        }.reverse #::: makeCircleForElements(elements)

    lazy val stream: Stream[T] =
        makeCircleForElements(elementsInCircle)

    def removeNthPositionFromCircle(position: Int): Option[Circle[T]] = {
        require(position > 0, "The position to remove from the circle must be greater than 0")

        val before = stream.take(position - 1).toList

        val valueToRemove = stream.take(position).last
        val nextElementsStream = stream.drop(position)

        val initialElementsSize = elementsInCircle.size

        val after = nextElementsStream.take(initialElementsSize - (position - initialElementsSize % position)).toList

        val resultingStream = after ++ before.filterNot(_ == valueToRemove).filterNot(after.contains).toSet

        if(resultingStream.isEmpty) None
        else Some(Circle(resultingStream))

//        val before = stream.take(position - 1)
//        val valueToRemove = stream.take(position).head
//        val elementsToTake = elementsInCircle.size - position
//        val stream1 = stream.drop(position)
//        val after = stream1.take(elementsToTake).filterNot(_ == valueToRemove)
//
//        val resultingElements: Seq[T] = after ++ before
//
//        if (resultingElements.isEmpty) None
//        else Some(Circle(resultingElements.toList))
    }
}

object Circle {
    def withIntElements(numOfElements: Int): Circle[Int] =
        Circle((1 to numOfElements).toList)
}
