package edany

import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.{Music, Sound}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import edany.level.{LevelLoader, LevelBuilder}
import com.badlogic.gdx.assets.AssetManager
import java.io.File
import edany.util.Util

case class Scene(
  camera: OrthographicCamera,
  spriteBatch: SpriteBatch,
  components: Seq[Component],
  assets: AssetManager
)

object Scene {
  def create(): Scene = {
    val assets = new AssetManager()

    // Load assets.
    val allowedTypes = Seq("png", "jpg", "jpeg", "gif", "xml", "mp3", "wav")
    val assetTypes = Map[String, Class[_]]("images" -> classOf[Texture], "music" -> classOf[Music], "sounds" -> classOf[Sound])
    assetTypes.foreach {
      case (folderName, classType) =>
        Util.recursiveListFiles(new File(s"main/assets/$folderName")).foreach((file: File) => {
          if (allowedTypes.exists((typ: String) => file.getPath.endsWith(typ))) assets.load(file.getPath, classType)
        })
    }

    assets.finishLoading()

    // Miscellaneous.
    val camera = new OrthographicCamera
    camera.setToOrtho(true, Gdx.graphics.getWidth, Gdx.graphics.getHeight)

    // Let's loop some hard coded music!
    val m: Music = assets.get("main/assets/music/BloodAndIron.mp3", classOf[Music])
    m.play()
    m.setLooping(true)
    m.setVolume(0.5.toFloat)

    // Load a test level for dev purposes.
    val level = LevelLoader.loadLevel(assets, Gdx.files.internal("main/local/assets/test-level.xml"))

    Scene(
      spriteBatch = new SpriteBatch(),
      camera = camera,
      components = level.components :+ new LevelBuilder,
      assets = assets
    )
  }
}