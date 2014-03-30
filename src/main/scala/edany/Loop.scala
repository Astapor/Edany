package edany

import org.lwjgl.Sys
import org.lwjgl.opengl.Display
import scala.annotation.tailrec

object Loop {
  def getTime = Sys.getTime * 1000 / Sys.getTimerResolution

  def loop[A](frameRate: FrameRate)(p: => A): Unit = {
    var fps = 0
    var lastTime = getTime

    @tailrec
    def step() {
      if (getTime - lastTime > 1000) {
        Display.setTitle("Edany - FPS: " + fps)
        fps = 0
        lastTime += 1000
      }
      fps += 1

      p

      Display.sync(frameRate.fps)

      if (!Display.isCloseRequested) step()
    }

    step()
  }
}