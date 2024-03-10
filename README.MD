JSON serializer written in Scala, which provides converters for:
- Int
- Float
- Double
- Boolean
- Char
- String
- Null

For custom types you need to define custom implicit converter, e.g.


    implicit object UserConverter extends JSONConverter[User] {
    def convert(user: User): JSONValue = JSONObject(Map(
    "name" -> JSONString(user.name),
    "age" -> JSONInt(user.age),
    "email" -> JSONString(user.email)
    ))
    }

Then you can call stringify on your custom type to see result:

    val john = User("John", 34, "john@mailbox.com")
    println(john.toJSON.stringify)
Result:

    {
        "name": "John",
        "age": 34,
        "email": "john@mailbox.com"
    }
Look at Example.scala for more detailed example.