package edany.level

import com.badlogic.gdx.{Input, Gdx}
import edany.{Actor, Scene, Logical, Component}
import edany.util.{Dimension, Vector2}
import com.badlogic.gdx.Input.Keys
import edany.components.ground.Ground
import com.badlogic.gdx.graphics.Texture

class LevelBuilder extends Component with Logical {
  override def update(scene: Scene) = {
    val x = Math.round(Gdx.input.getX / 32) * 32
    val y = Math.round(Gdx.input.getY / 32) * 32

    if (Gdx.input.isKeyPressed(Keys.S)) {
      LevelLoader.saveLevel(Gdx.files.internal("main/local/assets/test-level.xml"), scene)
    }

    val components = if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
      scene.components.filterNot {
        case c: Actor => c.position.x == x && c.position.y == y
        case _ => false
      }
    } else scene.components

    val components2 = if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
      val exists = components.contains {
        (c: Component) => c match {
          case c: Ground => c.rectangle.x == x && c.rectangle.y == y
          case _ => false
        }
      }

      if (!exists) components :+ Ground(texture = scene.assets.get("main/assets/images/grass.jpg", classOf[Texture]), position = Vector2(x, y), size = Dimension(32, 32))
      else components
    } else components

    scene.copy(components = components2)
  }
}