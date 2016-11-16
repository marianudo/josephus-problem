package me.marianonavas.josephus

/**
  *
  */
object Circle {
    def valuesStream[T](elementsInCircle: Seq[T]): Stream[T] =
        elementsInCircle.toStream.foldLeft(Stream(): Stream[T]) {
            (acc, value) => value #:: acc
        }.reverse

    def valuesStream(numberOfElements: Int): Stream[Int] =
        valuesStream(1 to numberOfElements) #::: valuesStream(numberOfElements)
}
