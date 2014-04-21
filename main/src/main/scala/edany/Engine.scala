package edany

import com.badlogic.gdx
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

class Engine extends gdx.Game {
  var scene: Scene = _

  override def create() {
    scene = Scene.create()
  }

  override def render() {
    if (scene.assets.update()) {
      Gdx.gl.glClearColor(0, 0, 0.2f, 1)
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

      scene.components.foreach {
        case c: Logical with Drawable => scene = c.update(scene); c.draw(scene)
        case c: Logical => scene = c.update(scene)
        case c: Drawable => c.draw(scene)
        case _ => throw new Exception("uh")
      }

      scene.camera.update()
    } else {
      Gdx.graphics.setTitle("Loading: " + scene.assets.getProgress)
    }
  }
}