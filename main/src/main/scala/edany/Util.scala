package edany

import com.badlogic.gdx.math.Rectangle

object Util {
  /** Returns true if the given place is empty/free of solid objects. */
  def isPlaceFree(game: Game, rectangle: Rectangle): Boolean = !findSolidAt(game, rectangle).isDefined

  /** Returns the solid entity instance at the given position if any. */
  def findSolidAt(game: Game, rectangle: Rectangle): Option[Component] = game.components.find {
    case e: Entity => e.solid && e.rectangle.overlaps(rectangle)
    case _ => false
  }
}