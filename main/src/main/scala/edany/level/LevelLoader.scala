package edany.level

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import edany.{Scene, Component}
import edany.components.{Player, Ground}
import edany.util.{Dimension, Vector2}
import scala.xml.{Text, Attribute, XML, Node}

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

  def saveLevel(file: FileHandle, scene: Scene) {
    def toXml(c: Component): Node = c match {
      case a: Ground =>
        <ground width="32" height="32"></ground> % Attribute(None, "x", Text(a.position.x.toInt.toString), Attribute(None, "y", Text(a.position.y.toInt.toString), xml.Null))
      case a: Player => <player></player> % Attribute(None, "x", Text(a.position.x.toInt.toString), Attribute(None, "y", Text(a.position.y.toInt.toString), xml.Null))
    }

    def canBeSaved(c: Component) = c match {
      case a: Ground => true
      case a: Player => true
      case _ => false
    }

    val document = <level>
      <components>
        { scene.components.filter(canBeSaved).map(toXml) }
      </components>
    </level>

    XML.save(file.file().getPath, document)
  }
}