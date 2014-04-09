package edany.util

import edany.Scene
import com.badlogic.gdx.graphics.Texture

trait SimpleDrawer {
  def texture: Texture
  def position: Vector2
  def size: Dimension

  def draw(scene: Scene) {
    scene.spriteBatch.setProjectionMatrix(scene.camera.combined)
    scene.spriteBatch.begin()
    scene.spriteBatch.draw(
      texture,
      position.x,
      position.y,
      size.width,
      size.height
    )
    scene.spriteBatch.end()
  }
}