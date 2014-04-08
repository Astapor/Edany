package edany.util

import edany.{Actor, Component, Scene}

object Util {
  /** Returns true if the given place is empty/free of solid objects. */
  def isPlaceFree(scene: Scene, rectangle: Rectangle): Boolean = !findSolidAt(scene, rectangle).isDefined

  /** Returns the solid entity instance at the given position if any. */
  def findSolidAt(scene: Scene, rectangle: Rectangle): Option[Component] = scene.components.find {
    case c: Actor => c.solid && c.rectangle.overlaps(rectangle)
    case _ => false
  }

  /** Finds any component at the given position. */
  def findComponentAt(scene: Scene, position: Vector2) = scene.components.find {
    case c: Actor => c.rectangle.contains(position)
    case _ => false
  }
}