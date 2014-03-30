package edany

import org.lwjgl.opengl.DisplayMode
import edany.components.RandomStuff

case class Game(
  displayMode: DisplayMode,
  components: Vector[Component]
)

object Game {
  def run() {
    val game = Game(
      displayMode = new DisplayMode(800, 600),
      components = Vector(new RandomStuff)
    )

    Engine.start(game)
  }
}