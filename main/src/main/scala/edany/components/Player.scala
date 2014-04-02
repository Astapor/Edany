package edany.components

import edany.{Game, Entity}
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys

case class Player(
  rectangle: Rectangle,
  texture: Texture
) extends Entity {
  override def update(game: Game) {
    if (Gdx.input.isKeyPressed(Keys.LEFT)) rectangle.x -= 200 * Gdx.graphics.getDeltaTime
    if (Gdx.input.isKeyPressed(Keys.RIGHT)) rectangle.x += 200 * Gdx.graphics.getDeltaTime
    if (Gdx.input.isKeyPressed(Keys.UP)) rectangle.y += 200 * Gdx.graphics.getDeltaTime
    if (Gdx.input.isKeyPressed(Keys.DOWN)) rectangle.y += 200 * Gdx.graphics.getDeltaTime
  }
}