package me.marianonavas.josephus

/**
  *
  */
object Circle {
    def createCircle[T](elementsInCircle: Seq[T]): Stream[T] =
        elementsInCircle.toStream.foldLeft(Stream(): Stream[T]) {
            (acc, value) => value #:: acc
        }.reverse #::: createCircle(elementsInCircle)

    def createInitialCircle(numberOfElements: Int): Stream[Int] =
        createCircle(1 to numberOfElements)
}
