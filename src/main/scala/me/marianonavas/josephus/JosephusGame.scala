package me.marianonavas.josephus

import scala.annotation.tailrec

/**
  *
  */
object JosephusGame {
    def playGame(circleSize: Int, stepRate: Int): Int = {
        val initialCircle = Circle.withIntElements(circleSize)

        @tailrec
        def findOutSurvivorPosition(circle: Circle[Int]): Int = {
            val maybeNewCircle = circle.removeNthPositionFromCircle(stepRate)
            maybeNewCircle match {
                case Some(c) => findOutSurvivorPosition(c)
                case None => circle.stream.take(1).head
            }
        }

        findOutSurvivorPosition(initialCircle)
    }
}
