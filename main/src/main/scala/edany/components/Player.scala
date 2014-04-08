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

case class Player(
  override val position: Vector2,
  override val size: Dimension,
  texture: Texture,
  jumpSpeed: Float = 0,
  currentGravity: Float = 0
) extends Actor with Logical with Drawable {
  val weight = 65 kg
  val movementSpeed = 200

  override def draw(scene: Scene) {
    scene.spriteBatch.setProjectionMatrix(scene.camera.combined)
    scene.spriteBatch.begin()
    scene.spriteBatch.draw(texture, position.x, position.y)
    scene.spriteBatch.end()
  }

  override def update(scene: Scene) = Player.update(this, scene)
}

object Player {
  private[Player] def update(player: Player, scene: Scene) = {
    // Is there anything below us based on current gravity?
    val player2 = Player.handleGravity(player, scene)
    val player3 = Player.handleJumping(player2, scene)

    scene.copy(
      components = scene.components.map((c: Component) => {
        if (c == player) player3 else c
      })
    )
  }

  /** Handles player jumping. */
  private[Player] def handleJumping(player: Player, scene: Scene): Player = {
    if (player.jumpSpeed > 0) {
      val jumpAmount = player.jumpSpeed * Gdx.graphics.getDeltaTime

      // Is there anything above us based on our jump speed?
      Util.findSolidAt(scene, Rectangle(player.position.x, player.position.y + jumpAmount, player.size.width, player.size.height)) match {
        case Some(e: Actor) =>
          // Ouch, we hit our head on something!
          val newY = e.position.y - e.size.height

          player.copy(jumpSpeed = 0, position = player.position.copy(y = newY))
        case _ =>
          val newY = player.position.y + jumpAmount

          var jumpSpeed = player.jumpSpeed - jumpAmount
          if (jumpSpeed < 1) jumpSpeed = 0

          player.copy(jumpSpeed = jumpSpeed, position = player.position.copy(y = newY))
      }
    } else player
  }

  /** Handles gravitational changes. */
  private[Player] def handleGravity(player: Player, scene: Scene): Player = {
    Util.findSolidAt(scene, Rectangle(player.position.x, player.position.y - player.currentGravity - 1, player.size.width, player.size.height)) match {
      case Some(e: Actor) =>
        if (player.currentGravity > 0) {
          // We hit something, position us on top of it and reset gravity.
          val newY = e.position.y + player.size.height

          player.copy(
            jumpSpeed = 0,
            currentGravity = 0,
            position = player.position.copy(y = newY)
          )
        } else {
          Player.handleMovement(player, scene, onGround = true)
        }
      case _ =>
        // We are falling down.
        val newY = player.position.y - player.currentGravity
        val newGravity = player.currentGravity + GravityManager.calculateGravityMomentum(player.weight) * Gdx.graphics.getDeltaTime

        val player2 = player.copy(
          position = player.position.copy(y = newY),
          currentGravity = newGravity
        )

        Player.handleMovement(player2, scene, onGround = false)
    }
  }

  /** Handles the player movement. The player moves slower on air, faster on ground. */
  private[Player] def handleMovement(player: Player, scene: Scene, onGround: Boolean): Player = {
    val factor = if (onGround) 1 else 0.5.toFloat

    val player2 = if (onGround && Gdx.input.isKeyPressed(Keys.UP)) player.copy(jumpSpeed = 700) else player

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

    player4
  }
}