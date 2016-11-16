package me.marianonavas.josephus

/**
  *
  */
case class Circle[T](elementsInCircle: Seq[T]) {

    lazy val stream: Stream[T] =
        elementsInCircle.toStream.foldLeft(Stream(): Stream[T]) {
            (acc, value) => value #:: acc
        }.reverse #::: stream

    def removeNthPositionFromCircle(position: Int): Option[Circle[T]] = {
        val maybeNthPosition: Option[T] = stream.drop(position - 1).headOption
        val elementsInNewCircle = maybeNthPosition.map(value => elementsInCircle.filterNot(_ == value))
        elementsInNewCircle.flatMap(l => if(l.isEmpty) None else Some(Circle(l)))
    }
}

object Circle {
    def withIntElements(numOfElements: Int): Circle[Int] =
        Circle(1 to numOfElements)
}
