package edany

import com.badlogic.gdx.backends.lwjgl.{LwjglApplicationConfiguration, LwjglApplication}

object Test extends App {
  val cfg = new LwjglApplicationConfiguration
  cfg.title = "Edany"
  cfg.useGL20 = false
  cfg.width = 800
  cfg.height = 640

  new LwjglApplication(new Engine, cfg)
}