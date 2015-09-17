package emptyflash.coopdj

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory

class TwitterSongSourceSpec extends FlatSpec with MockFactory {

  //  "An empty Set" should "have size 0" in {
  //    assert(Set.empty.size == 0)
  //  }

  it should "update it's callback function list when onSourceUpdated is called" in {
    lazy val songSource = new TwitterSongSource("#test")
    var called = false
    songSource.onSongSourceUpdated((message: String) => {
      called = true
    })
    songSource.applyMessageToCallbacks(songSource.callbackFunctions, "test")
    println(songSource.callbackFunctions)
    assert(called)
  }
}
