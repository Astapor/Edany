package edany.components

import edany._
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import edany.util._
import edany.util.Rectangle
import edany.util.Vector2
import edany.util.Dimension
import scala.Some
import scalaz.State

case class Player(
  override val position: Vector2,
  override val size: Dimension,
  texture: Texture,
  //jumpSpeed: Float = 0,
  gravity: Float = 0,
  speed: Vector2 = Vector2.zero
) extends Actor with Logical with Drawable with SimpleDrawer {
  val weight = 65 kg
  val movementSpeed = 200

  override def update(scene: Scene) = Player.update(this, scene)
}

object Player {
  private[Player] def update(player: Player, scene: Scene) = {
    val newState = for {
      _ <- Player.handlePhysics(scene)
      s <- State.get
    } yield s

    scene.copy(
      components = scene.components.map((c: Component) => {
        if (c == player) newState.exec(player) else c
      })
    )
  }

  private[Player] def handlePhysics(scene: Scene): State[Player, Unit] = State(player => {
    val newSpeed = player.speed.copy(y = player.speed.y + player.gravity)
    val newGravity = player.gravity + GravityManager.calculateGravityMomentum(player.weight)

    if (newSpeed.y > 0) {
      // Is there anything below us based on our current gravity?
      Util.findSolidAt(scene, Rectangle(player.position.x, player.position.y + player.size.height, player.size.width, newSpeed.y * Gdx.graphics.getDeltaTime)) match {
        case Some(e: Actor) =>
          // We hit something, position us on top of it and reset gravity.
          val newY = e.position.y - player.size.height

          (player.copy(
            speed = player.speed.copy(y = 0),
            gravity = 0,
            position = player.position.copy(y = newY)
          ), ())
        case _ =>
          // We are falling down.
          val player2 = player.copy(
            position = player.position.copy(y = player.position.y + newSpeed.y * Gdx.graphics.getDeltaTime),
            gravity = newGravity,
            speed = newSpeed
          )

          Player.handleMovement(scene, onGround = false)(player2)
      }
    } else if (player.speed.y < 0) {
      // Is there anything above us based on our jump speed?
      val player2 = Util.findSolidAt(scene, Rectangle(player.position.x, player.position.y + newSpeed.y * Gdx.graphics.getDeltaTime, player.size.width, newSpeed.y.abs * Gdx.graphics.getDeltaTime)) match {
        case Some(e: Actor) =>
          // Ouch, we hit our head on something!
          val newY = e.position.y + e.size.height

          player.copy(speed = player.speed.copy(y = 0), position = player.position.copy(y = newY))
        case _ =>
          player.copy(gravity = newGravity, speed = newSpeed, position = player.position.copy(y = player.position.y + newSpeed.y * Gdx.graphics.getDeltaTime))
      }

      Player.handleMovement(scene, onGround = false)(player2)
    } else {
      if (Util.isPlaceFree(scene, Rectangle(player.position.x, player.position.y + player.size.height, player.size.width, 1))) {
        val player2 = player.copy(
          gravity = newGravity,
          speed = newSpeed
        )

        Player.handleMovement(scene, onGround = false)(player2)
      } else {
        Player.handleMovement(scene, onGround = true)(player)
      }
    }
  })

  /** Handles the player movement. The player moves slower on air, faster on ground. */
  private[Player] def handleMovement(scene: Scene, onGround: Boolean): State[Player, Unit] = State(player => {
    val factor = if (onGround) 1 else 0.75.toFloat

    val player2 = if (onGround && Gdx.input.isKeyPressed(Keys.UP)) player.copy(speed = player.speed.copy(y = -500)) else player

    val player3 = if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      val amount = player2.movementSpeed * Gdx.graphics.getDeltaTime * factor

      Util.findSolidAt(scene, Rectangle(player2.position.x - amount, player2.position.y, player2.size.width, player2.size.height)) match {
        case Some(e: Actor) => player2.copy(position = player2.position.copy(x = e.position.x + e.size.width))
        case _ => player2.copy(position = player2.position.copy(x = player2.position.x - amount))
      }
    } else player2

    val player4 = if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      val amount = player3.movementSpeed * Gdx.graphics.getDeltaTime * factor

      Util.findSolidAt(scene, Rectangle(player3.position.x + amount, player3.position.y, player3.size.width, player3.size.height)) match {
        case Some(e: Actor) => player3.copy(position = player3.position.copy(x = e.position.x - player3.size.width))
        case _ => player3.copy(position = player3.position.copy(x = player3.position.x + amount))
      }
    } else player3

    (player4, ())
  })
}