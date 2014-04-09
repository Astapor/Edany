package edany.components.ground

import com.badlogic.gdx.graphics.Texture
import edany._
import edany.util.{TileDrawer, Vector2, Dimension}
import edany.materials.Material

case class Ground(
  texture: Texture,
  material: Material = new Material,
  override val position: Vector2,
  override val size: Dimension
) extends Actor with Drawable with TileDrawer {
  override def solid = true
}