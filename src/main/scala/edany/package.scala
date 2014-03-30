package object edany {
  implicit def intToFrameRate(i: Int): FrameRate = FrameRate(i)
  final def WindowTitle = "Edany"
}
