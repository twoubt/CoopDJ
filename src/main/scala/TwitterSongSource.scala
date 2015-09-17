package emptyflash.coopdj

class TwitterSongSource(sourceHashtag: String) extends SongSource {
  var callbackFunctions: List[String => Unit] = List()

  def applyMessageToCallbacks(callbacks: List[String => Unit], message: String): Unit = {
    callbacks foreach {
      _.apply(message)
    }
  }

  def onSongSourceUpdated(callback: String => Unit) = {
    callbackFunctions = callbackFunctions :+ callback
  }
}
