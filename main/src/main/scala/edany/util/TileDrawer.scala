package edany.util

import com.badlogic.gdx.graphics.Texture
import edany.Scene
import com.badlogic.gdx.graphics.g2d.TextureRegion

trait TileDrawer {
  def texture: Texture
  def position: Vector2
  def size: Dimension

  def draw(scene: Scene) {
    val sourceX = position.x % 1024
    val sourceY = position.y % 1024
    val region = new TextureRegion(texture, sourceX.toInt, sourceY.toInt, size.width.toInt, size.height.toInt)
    region.flip(false, true)

    scene.spriteBatch.setProjectionMatrix(scene.camera.combined)
    scene.spriteBatch.begin()
    scene.spriteBatch.draw(
      region,
      position.x, position.y
    )
    scene.spriteBatch.end()
  }
}
