package typeclasses.eithert

import org.scalatest.freespec.AnyFreeSpec

class SampleTest extends AnyFreeSpec {

  "exec" - {
    "normal" in {
      Sample.exec
    }
  }

}
