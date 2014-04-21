package edany.util


object GravityManager {
  // How much there is gravity in the universe.
  val Gravity = 0.05.toFloat

  def calculateGravityMomentum(weight: Kilogram) = Gravity * weight.kg
}
