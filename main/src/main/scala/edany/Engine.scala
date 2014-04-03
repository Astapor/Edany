package edany

import com.badlogic.gdx
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

class Engine extends gdx.Game {
  var game: Game = _

  override def create() {
    game = Game.create()
  }

  override def render() {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    game.components.foreach {
      case c: DrawableComponent => c.update(game); c.draw(game)
      case c: Component => c.update(game)
    }

    game.camera.update()
  }
}