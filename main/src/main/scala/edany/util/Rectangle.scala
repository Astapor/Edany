package edany.util

import com.badlogic.gdx

case class Rectangle(x: Float, y: Float, width: Float, height: Float) {
  def overlaps(rectangle: Rectangle) = toGdx.overlaps(rectangle.toGdx)

  lazy val toGdx = new gdx.math.Rectangle(x, y, width, height)
}