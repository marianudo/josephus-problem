package me.marianonavas.josephus

/**
  *
  */
object Circle {
    // Smart constructor for a Nil stream of Integer values
    private val intNilStream: Stream[Int] = Stream()

    def valuesStream(numberOfElements: Int): Stream[Int] =
        (1 to numberOfElements).toStream.foldLeft(Circle.intNilStream) {
            (acc, value) => value #:: acc
        }.reverse #::: valuesStream(numberOfElements)
}
