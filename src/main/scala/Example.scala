import JSONConverter.*
import JSONTypes.*
import java.sql.Date


object Example extends App {

  // Custom types
  case class User(name: String, age: Int, email: String)
  case class Post(content: String, createdAt: Date)
  case class Feed(user: User, posts: Array[Post])

  // Implicit converters for custom types
  implicit object UserConverter extends JSONConverter[User] {
    def convert(user: User): JSONValue = JSONObject(Map(
      "name" -> JSONString(user.name),
      "age" -> JSONInt(user.age),
      "email" -> JSONString(user.email)
    ))
  }

  implicit object PostConverter extends JSONConverter[Post] {
    def convert(post: Post): JSONValue = JSONObject(Map(
      "content" -> JSONString(post.content),
      "createdAt" -> JSONString(post.createdAt.toString)
    ))
  }

  implicit object FeedConverter extends JSONConverter[Feed] {
    def convert(feed: Feed): JSONValue = JSONObject(Map(
      "user" -> feed.user.toJSON,
      "posts" -> JSONArray(feed.posts.map(_.toJSON))
    ))
  }

  // call stringify on result
  val now = new Date(System.currentTimeMillis())
  val john = User("John", 34, "john@mailbox.com")
  val feed = Feed(john, Array(
    Post("Hello world", now),
    Post("Some interesting post", now)
  ))

  println(feed.toJSON.stringify)
}
