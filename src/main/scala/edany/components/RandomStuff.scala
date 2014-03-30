package edany.components

import edany.DrawableComponent
import org.lwjgl.opengl.GL11._

class RandomStuff extends DrawableComponent {
  def update() {}

  def draw() {
    // set the color of the quad (R,G,B,A)
    glColor3f(0.5f,0.5f,1.0f)

    // draw quad
    glBegin(GL_QUADS)
    glVertex2f(100,100)
    glVertex2f(100+200,100)
    glVertex2f(100+200,100+200)
    glVertex2f(100,100+200)
    glEnd()
  }
}
