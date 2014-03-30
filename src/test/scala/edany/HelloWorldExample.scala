package edany

import org.lwjgl.opengl.{DisplayMode, Display}

class DisplayExample {
  def start() {
    try {
      Display.setDisplayMode(new DisplayMode(800, 600))
      Display.create()
    } catch {
      case e: Exception => e.printStackTrace(); System.exit(0)
    }

    while (!Display.isCloseRequested) {
      Display.update()
    }

    Display.destroy()
  }
}

object HelloWorldExample extends App {
  val e = new DisplayExample
  e.start()
}