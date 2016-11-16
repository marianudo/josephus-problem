package me.marianonavas.josephus

import org.scalatest.FlatSpec

/**
  *
  */
class JosephusGameSpec extends FlatSpec {
    import JosephusGame.playGame

    "In a 5 members circle with a step of 2" should "win the 3th" in {
        assert(playGame(5, 2) == 3)
    }

    "In a 5 members circle with a step of 3" should "win the 4th" in {
        assert(playGame(5, 3) == 4)
    }

    "In a 5 members circle with a step of 1" should "win the 5th" in {
        assert(playGame(5, 1) == 5)
    }

    "In a 5 members circle with a step of 5" should "win the 4th" in {
        assert(playGame(5, 5) == 2)
    }

    "In a 5 members circle with a step of 7" should "win the 4th" in {
        assert(playGame(5, 7) == 4)
    }
}
