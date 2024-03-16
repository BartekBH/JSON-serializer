object JSONTypes {

  /**
   * Intermediate data type
   */
  sealed trait JSONValue {
    def stringify: String
  }

  /**
   * Basic data types
   */

  // Null
  final case class JSONNull(value: Null) extends JSONValue {
    def stringify: String = "null"
  }

  // String
  final case class JSONString(value: String) extends JSONValue {
    def stringify: String = "\"" + value + "\""
  }

  // Char
  final case class JSONChar(value: Char) extends JSONValue {
    def stringify: String = "\'" + value + "\'"
  }

  final case class JSONBoolean(value: Boolean) extends JSONValue {
    def stringify: String = value.toString
  }

  // Int
  final case class JSONInt(value: Int) extends JSONValue {
    def stringify: String = value.toString
  }

  // Float
  final case class JSONFloat(value: Float) extends JSONValue {
    def stringify: String = value.toString
  }

  // Double
  final case class JSONDouble(value: Double) extends JSONValue {
    def stringify: String = value.toString
  }

  // List
  final case class JSONList(values: List[JSONValue]) extends JSONValue {
    def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }

  // Array
  final case class JSONArray(values: Array[JSONValue]) extends JSONValue {
    def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }

  /**
   * Object
   */
  final case class JSONObject(values: Map[String, JSONValue]) extends JSONValue {
    def stringify: String = values.map {
      case (key, value: JSONObject) =>
        "  \"" + key + "\": " + value.stringify
      case (key, value: JSONArray) =>
        "  \"" + key + "\": " + value.stringify
      case (key, value) =>
        "    \"" + key + "\": " + value.stringify
    }
      .mkString("{\n", ",\n", "\n}")
  }
}
