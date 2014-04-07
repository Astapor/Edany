package edany

import com.badlogic.gdx.{Input, Gdx}
import edany.components.Ground

class LevelBuilder extends Component with Logical {
  override def update(scene: Scene) = {
    val x = Math.round(Gdx.input.getX / 32) * 32
    val y = 640 - 32 - Math.round(Gdx.input.getY / 32) * 32
/*
    if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
      scene.components = scene.components.filterNot {
        case c: Ground => c.position.x == x && c.position.y == y
        case _ => false
      }
    }

    if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
      val exists = scene.components.contains {
        (c: Component) => c match {
          case c: Ground => c.rectangle.x == x && c.rectangle.y == y
          case _ => false
        }
      }

      if (!exists) scene.components = scene.components :+ Ground(texture = scene.textures("main/assets/images/ground.png"), position = Vector2(x, y), size = Dimension(32, 32))
    }
    */

    scene
  }
}