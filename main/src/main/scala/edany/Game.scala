package edany

import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.{Music, Sound}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import edany.components.{Ground, Player}
import com.badlogic.gdx.math.Rectangle

case class Game(
  textures: Map[String, Texture],
  sounds: Map[String, Sound],
  music: Map[String, Music],
  camera: OrthographicCamera,
  spriteBatch: SpriteBatch,
  components: Vector[Component]
)

object Game {
  def create(): Game = {
    // Let's load some content.
    val texturePaths = Vector("main/assets/images/droplet.png", "main/assets/images/ground.png", "main/assets/images/player.png")
    val textures = texturePaths.map((p) => (p, new Texture(Gdx.files.internal(p)))).toMap

    val soundPaths = Vector("main/assets/sounds/waterdrop.wav")
    val sounds = soundPaths.map((p) => (p, Gdx.audio.newSound(Gdx.files.internal(p)))).toMap

    val musicPaths = Vector("main/assets/music/raining.mp3")
    val music = musicPaths.map((p) => (p, Gdx.audio.newMusic(Gdx.files.internal(p)))).toMap

    // Miscellaneous.
    val camera = new OrthographicCamera()
    camera.setToOrtho(false, 800, 600)

    // Let's loop some hard coded music!
    music.head._2.setLooping(true)

    // Some components!
    val componentsBuilder = Vector.newBuilder[Component]

    componentsBuilder += Player(
      texture = textures("main/assets/images/player.png"),
      rectangle = new Rectangle(96, 500, 32, 32)
    )

    // Some terrain.
    for (i <- 2 to 10) {
      componentsBuilder += Ground(
        texture = textures("main/assets/images/ground.png"),
        rectangle = new Rectangle(i * 32, 32, 32, 32)
      )
    }

    componentsBuilder += Ground(
      texture = textures("main/assets/images/ground.png"),
      rectangle = new Rectangle(320, 64, 32, 32)
    )

    componentsBuilder += Ground(
      texture = textures("main/assets/images/ground.png"),
      rectangle = new Rectangle(320, 96, 32, 32)
    )

    for (i <- 1 to 5) {
      componentsBuilder += Ground(
        texture = textures("main/assets/images/ground.png"),
        rectangle = new Rectangle(320 + i * 32, 96, 32, 32)
      )
    }

    Game(
      textures = textures,
      sounds = sounds,
      music = music,
      spriteBatch = new SpriteBatch(),
      camera = camera,
      components = componentsBuilder.result()
    )
  }
}