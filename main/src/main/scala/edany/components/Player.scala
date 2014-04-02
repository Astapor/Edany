package edany.components

import edany.Entity
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.Texture

case class Player(
  rectangle: Rectangle,
  texture: Texture
) extends Entity