package edany

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.Display
import edany.Loop._

object Engine {
  def start(game: Game) {
    try {
      Display.setDisplayMode(game.displayMode)
      Display.setTitle(WindowTitle)
      Display.create()
    } catch {
      case e: Exception => e.printStackTrace(); System.exit(1337)
    }

    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    glOrtho(0, 800, 0, 600, 1, -1)
    glMatrixMode(GL_MODELVIEW)

    loop(60 fps) {
      // Clear the screen and depth buffer
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

      game.components.foreach{
        case c: DrawableComponent =>
          c.draw()
          c.update()
        case c: Component => c.update()
      }

      Display.update()
      Display.setTitle(WindowTitle)
    }

    Display.destroy()
  }
}
