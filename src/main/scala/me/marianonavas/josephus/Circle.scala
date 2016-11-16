package me.marianonavas.josephus

/**
  *
  */
case class Circle[T](elementsInCircle: Seq[T]) {
    require(elementsInCircle.nonEmpty,
        "Cannot create a circle without elements. The elements in circle sequence is empty")

    lazy val stream: Stream[T] =
        elementsInCircle.toStream.foldLeft(Stream(): Stream[T]) {
            (acc, value) => value #:: acc
        }.reverse #::: elementsInCircle.toStream

    def removeNthPositionFromCircle(position: Int): Option[Circle[T]] = {
        val maybeNthPosition: Option[T] = stream.drop(position - 1).headOption
        val elementsInNewCircle = maybeNthPosition.map(value => elementsInCircle.filterNot(_ == value))
        elementsInNewCircle.map(Circle(_))
    }
}

object Circle {
    def withIntElements(numOfElements: Int): Circle[Int] =
        Circle(1 to numOfElements)
}
