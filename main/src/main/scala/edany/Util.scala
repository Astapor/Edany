package edany

object Util {
  /** Returns true if the given place is empty/free of solid objects. */
  def isPlaceFree(scene: Scene, rectangle: Rectangle): Boolean = !findSolidAt(scene, rectangle).isDefined

  /** Returns the solid entity instance at the given position if any. */
  def findSolidAt(scene: Scene, rectangle: Rectangle): Option[Component] = scene.components.find {
    case e: Entity => e.solid && e.rectangle.overlaps(rectangle)
    case _ => false
  }
}