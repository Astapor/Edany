package edany

import edany.Loop._

import org.lwjgl.opengl.{GL11, DisplayMode, Display}
import org.lwjgl.Sys

class DisplayExample {
  def start() {
    try {
      Display.setDisplayMode(new DisplayMode(800, 600))
      Display.create()
    } catch {
      case e: Exception => e.printStackTrace(); System.exit(0)
    }

    GL11.glMatrixMode(GL11.GL_PROJECTION)
    GL11.glLoadIdentity()
    GL11.glOrtho(0, 800, 0, 600, 1, -1)
    GL11.glMatrixMode(GL11.GL_MODELVIEW)

    loop(60 fps) {
      // Clear the screen and depth buffer
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT)

      // set the color of the quad (R,G,B,A)
      GL11.glColor3f(0.5f,0.5f,1.0f)

      // draw quad
      GL11.glBegin(GL11.GL_QUADS)
      GL11.glVertex2f(100,100)
      GL11.glVertex2f(100+200,100)
      GL11.glVertex2f(100+200,100+200)
      GL11.glVertex2f(100,100+200)
      GL11.glEnd()

      Display.update()
    }

    Display.destroy()
  }
}

object HelloWorldExample extends App {
  val e = new DisplayExample
  e.start()
}