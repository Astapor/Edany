package edany.components

import com.badlogic.gdx.graphics.Texture
import edany._
import edany.Dimension

case class Ground(
  texture: Texture,
  override val position: Vector2,
  override val size: Dimension
) extends Entity with Drawable {
  override def solid = true

  def draw(scene: Scene) {
    scene.spriteBatch.setProjectionMatrix(scene.camera.combined)
    scene.spriteBatch.begin()
    scene.spriteBatch.draw(texture, position.x, position.y)
    scene.spriteBatch.end()
  }
}
