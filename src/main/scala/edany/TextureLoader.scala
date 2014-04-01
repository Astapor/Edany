package edany

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import java.nio.ByteBuffer

class TextureLoader {
  private[this] def convertImageData(bufferedImage: BufferedImage, texture: Texture) = {

  }

  private[this] def loadImage(ref: String): BufferedImage = {
    ImageIO.read(new File(ref))
  }
}

object TextureLoader {

}