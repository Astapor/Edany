package edany

import org.lwjgl.Sys
import org.lwjgl.opengl.Display

object Loop {
  def getTime = Sys.getTime * 1000 / Sys.getTimerResolution

  def loop[A](frameRate: FrameRate)(p: => A): Unit = {
    var fps = 0
    var lastTime = getTime

    while (!Display.isCloseRequested) {
      if (getTime - lastTime > 1000) {
        Display.setTitle("FPS: " + fps)
        fps = 0
        lastTime += 1000
      }
      fps += 1

      p

      Display.sync(frameRate.fps)
    }
  }
}
