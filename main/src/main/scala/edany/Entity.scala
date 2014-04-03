package edany

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.Texture

abstract class Entity extends DrawableComponent {
  def solid: Boolean = false
  def texture: Texture
  def rectangle: Rectangle

  def update(game: Game) {}

  def draw(game: Game) {
    game.spriteBatch.setProjectionMatrix(game.camera.combined)
    game.spriteBatch.begin()
    game.spriteBatch.draw(texture, rectangle.x, rectangle.y)
    game.spriteBatch.end()
  }
}