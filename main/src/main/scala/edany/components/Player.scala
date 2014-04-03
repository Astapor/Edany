package edany.components

import edany._
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import scala.Some

case class Player(rectangle: Rectangle, texture: Texture) extends Entity {
  val weight = 60 kg
  val movementSpeed = 200
  var jumpSpeed: Float = 0
  var currentGravity: Float = 0

  override def update(game: Game) {
    // Is there anything below us based on current gravity?
    Util.findSolidAt(game, new Rectangle(rectangle.x, rectangle.y - currentGravity - 1, rectangle.width, rectangle.height)) match {
      case Some(e: Entity) =>
        if (currentGravity > 0) {
          // We hit something, position us on top of it and reset gravity.
          rectangle.y = e.rectangle.y + rectangle.height
          currentGravity = 0
          jumpSpeed = 0
        } else {
          handleMovement(game, onGround = true)
        }
      case _ =>
        // We are falling down.
        rectangle.y -= currentGravity
        currentGravity += GravityManager.calculateGravityMomentum(weight) * Gdx.graphics.getDeltaTime

        handleMovement(game, onGround = false)
    }

    if (jumpSpeed > 0) {
      // Is there anything above us based on our jump speed?
      Util.findSolidAt(game, new Rectangle(rectangle.x, rectangle.y + jumpSpeed, rectangle.width, rectangle.height)) match {
        case Some(e: Entity) =>
          // Ouch, we hit our head on something!
          jumpSpeed = 0
          rectangle.y = e.rectangle.y - e.rectangle.height
          // health -= jumpSpeed
        case _ =>
          val amount = jumpSpeed * Gdx.graphics.getDeltaTime
          rectangle.y += amount

          jumpSpeed -= amount
          if (jumpSpeed < 1) jumpSpeed = 0
      }
    }
  }

  /** Handles the player movement. The player moves slower on air, faster on ground. */
  private[this] def handleMovement(game: Game, onGround: Boolean) = {
    val factor = if (onGround) 1 else 0.5.toFloat

    if (onGround && Gdx.input.isKeyPressed(Keys.UP)) jumpSpeed = 600

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      val amount = movementSpeed * Gdx.graphics.getDeltaTime * factor
      Util.findSolidAt(game, new Rectangle(rectangle.x - amount, rectangle.y, rectangle.width, rectangle.height)) match {
        case Some(e: Entity) => rectangle.x = e.rectangle.x + e.rectangle.width
        case _ => rectangle.x -= amount
      }
    }

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      val amount = movementSpeed * Gdx.graphics.getDeltaTime * factor
      Util.findSolidAt(game, new Rectangle(rectangle.x + amount, rectangle.y, rectangle.width, rectangle.height)) match {
        case Some(e: Entity) => rectangle.x = e.rectangle.x - rectangle.width
        case _ => rectangle.x += amount
      }
    }
  }
}