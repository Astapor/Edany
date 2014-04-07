package edany

import edany.util.{Dimension, Rectangle, Vector2}

/** An entity occupies space in the spatial world and has a location. */
abstract class Actor extends Component {
  def solid: Boolean = false
  def position: Vector2 = Vector2()
  def size: Dimension = Dimension()

  lazy val rectangle = Rectangle(position.x, position.y, size.width, size.height)
}