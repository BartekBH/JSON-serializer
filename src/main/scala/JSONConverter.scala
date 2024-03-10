import JSONTypes._

object JSONConverter {

  import JSONTypes.JSONValue

  trait JSONConverter[T] {
    def convert(value: T): JSONValue
  }

  // conversion to JSON types
  implicit class JSONOps[T](value: T) {
    def toJSON(implicit converter: JSONConverter[T]): JSONValue =
      converter.convert(value)
  }

  /**
   * Existing data types implicits for JSONOps 
   */
  
  // Null
  implicit object NullConverter extends JSONConverter[Null] {
    def convert(value: Null): JSONValue = JSONNull(value)
  }

  // String
  implicit object StringConverter extends JSONConverter[String] {
    def convert(value: String): JSONValue = JSONString(value)
  }

  // Char
  implicit object CharConverter extends JSONConverter[Char] {
    def convert(value: Char): JSONValue = JSONChar(value)
  }

  // Boolean
  implicit object BooleanConverter extends JSONConverter[Boolean] {
    def convert(value: Boolean): JSONValue = JSONBoolean(value)
  }

  // Int
  implicit object IntConverter extends JSONConverter[Int] {
    def convert(value: Int): JSONValue = JSONInt(value)
  }

  // Float
  implicit object FloatConverter extends JSONConverter[Float] {
    def convert(value: Float): JSONValue = JSONFloat(value)
  }

  // Double
  implicit object DoubleConverter extends JSONConverter[Double] {
    def convert(value: Double): JSONValue = JSONDouble(value)
  }

  // List
  implicit object ListConverter extends JSONConverter[List[JSONValue]] {
    def convert(value: List[JSONValue]): JSONValue = JSONList(value)
  }

  // Array
  implicit object ArrayConverter extends JSONConverter[Array[JSONValue]] {
    def convert(value: Array[JSONValue]): JSONValue = JSONArray(value)
  }

}
