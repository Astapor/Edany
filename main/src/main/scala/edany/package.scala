import edany.util.Kilogram

package object edany {
  implicit def intToKilogram(value: Int): Kilogram = Kilogram(kg = value)
}
