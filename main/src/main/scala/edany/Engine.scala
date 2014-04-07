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
    Gdx.gl.glClearColor(0, 0, 0.2f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    scene.components.foreach((c: Component) => {
      val isLogical = c.isInstanceOf[Logical]
      val isDrawable = c.isInstanceOf[Drawable]

      if (isLogical) scene = c.asInstanceOf[Logical].update(scene)
      if (isDrawable) c.asInstanceOf[Drawable].draw(scene)

      if (!isLogical && !isDrawable) throw new Exception("Components should be either logical or drawable, or both.")
    })

    scene.camera.update()
  }
}