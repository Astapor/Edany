package edany

import com.badlogic.gdx.files.FileHandle
import scala.xml.{XML, Node}
import edany.components.{Player, Ground}
import com.badlogic.gdx.graphics.Texture

object LevelLoader {
  def loadLevel(file: FileHandle, textures: Map[String, Texture]): Level = {
    val document = XML.loadFile(file.file())
    val components = Vector.newBuilder[Component]

    val componentsElement = (document \\ "level" \\ "components").head
    componentsElement.descendant.foreach((node: Node) => {
      // TODO: Could we use reflection? Beautify this.
      if (node.label == "ground") {
        val x = node.attribute("x").get.text.toInt
        val y = node.attribute("y").get.text.toInt
        val width = node.attribute("width").get.text.toInt
        val height = node.attribute("height").get.text.toInt

        components += Ground(position = Vector2(x, y), size = Dimension(width, height), texture = textures("main/assets/images/ground.png"))
      }

      if (node.label == "player") {
        val x = node.attribute("x").get.text.toInt
        val y = node.attribute("y").get.text.toInt

        components += Player(position = Vector2(x, y), size = Dimension(32, 32), texture = textures("main/assets/images/player.png"))
      }
    })

    Level(components = components.result())
  }
}