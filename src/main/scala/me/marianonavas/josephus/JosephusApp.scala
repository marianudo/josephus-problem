package me.marianonavas.josephus

import scala.util.{Failure, Success, Try}

/**
  *
  */
object JosephusApp {

    private def areAllDigits(args: Array[String]): Boolean =
        Try(args.map(_.toInt)) match {
            case Success(_) => true
            case Failure(_) => false
        }

    def main(args: Array[String]): Unit = {
        if(args.length != 2 || !areAllDigits(args))
            println("Expected two integer parameters as input for this program")
        else {
            val circleSize = args(0).toInt
            val stepRate = args(1).toInt

            val survivor = JosephusGame.playGame(circleSize, stepRate)

            println(s"Initial position of the survivor: $survivor")
        }

    }
}
