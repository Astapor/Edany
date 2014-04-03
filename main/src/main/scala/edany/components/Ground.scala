package edany.components

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.Texture
import edany.Entity

case class Ground(
  rectangle: Rectangle,
  texture: Texture,
  override val solid: Boolean = true
) extends Entity
