package edany

import com.badlogic.gdx

case class Rectangle(var x: Float, var y: Float, var width: Float, var height: Float) {
  def overlaps(rectangle: Rectangle) = toGdx.overlaps(rectangle.toGdx)

  lazy val toGdx = new gdx.math.Rectangle(x, y, width, height)
}