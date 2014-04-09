package edany

trait Logical {
  /** Returns the new state. */
  def update(scene: Scene): Scene
}