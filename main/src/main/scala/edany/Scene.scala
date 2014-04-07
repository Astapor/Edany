package edany

import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.{Music, Sound}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import edany.level.{LevelLoader, LevelBuilder}

case class Scene(
  textures: Map[String, Texture],
  sounds: Map[String, Sound],
  music: Map[String, Music],
  camera: OrthographicCamera,
  spriteBatch: SpriteBatch,
  components: Seq[Component],
  levelBuilder: LevelBuilder = new LevelBuilder
)

object Scene {
  def create(): Scene = {
    // Let's load some content.
    val texturePaths = Seq("main/assets/images/droplet.png", "main/assets/images/ground.png", "main/assets/images/player.png")
    val textures = texturePaths.map((p) => (p, new Texture(Gdx.files.internal(p)))).toMap

    val soundPaths = Seq("main/assets/sounds/waterdrop.wav")
    val sounds = soundPaths.map((p) => (p, Gdx.audio.newSound(Gdx.files.internal(p)))).toMap

    val musicPaths = Seq("main/assets/music/raining.mp3")
    val music = musicPaths.map((p) => (p, Gdx.audio.newMusic(Gdx.files.internal(p)))).toMap

    // Miscellaneous.
    val camera = new OrthographicCamera()
    camera.setToOrtho(false, 800, 640)

    // Let's loop some hard coded music!
    music.head._2.setLooping(true)
    music.head._2.play()

    val level = LevelLoader.loadLevel(Gdx.files.internal("main/assets/levels/level1.xml"), textures)

    Scene(
      textures = textures,
      sounds = sounds,
      music = music,
      spriteBatch = new SpriteBatch(),
      camera = camera,
      components = level.components
    )
  }
}