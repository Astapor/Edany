package edany

import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.{Music, Sound}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import edany.components.Player

case class Scene(
  textures: Map[String, Texture],
  sounds: Map[String, Sound],
  music: Map[String, Music],
  camera: OrthographicCamera,
  spriteBatch: SpriteBatch,
  components: Seq[Component]
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
    camera.setToOrtho(false, 800, 600)

    // Let's loop some hard coded music!
    music.head._2.setLooping(true)
    music.head._2.play()

    // Some components!
    val componentsBuilder = Vector.newBuilder[Component]

    componentsBuilder += Player(
      texture = textures("main/assets/images/player.png"),
      position = Vector2(96, 500),
      size = Dimension(32, 32)
    )

    Scene(
      textures = textures,
      sounds = sounds,
      music = music,
      spriteBatch = new SpriteBatch(),
      camera = camera,
      components = componentsBuilder.result()
    )
  }
}