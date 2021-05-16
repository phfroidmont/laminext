package io.laminext.site.examples.fetch

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object FetchCirceExample
    extends CodeExample(
      id = "example-fetch-circe",
      title = "circe example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      div(
        "temporarily commenting this out – circe's not ready for scala 3-RC2"
      )
//      import io.laminext.syntax.core._
//      import io.laminext.fetch.circe._
//      import io.circe._
//      import org.scalajs.dom
//      import scala.util.Failure
//      import scala.util.Success
//      import com.raquo.laminar.CollectionCommand
//
//      case class Data(s: String)
//      implicit val codeData: Codec[Data] = Codec.from(
//        Decoder.decodeString.map(Data(_)),
//        Encoder.encodeString.contramap(_.s),
//      )
//
//      val inputElement = input(
//        tpe := "text",
//        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
//        placeholder := "send a message"
//      )
//      val (responsesStream, responseReceived) = EventStream.withCallback[FetchResponse[Json]]
//      div(
//        EventStream.fromValue(1).debugLogStarts --> { _ => },
//        cls := "space-y-4",
//        div(inputElement),
//        div(
//          cls := "flex space-x-4",
//          button(
//            cls := "btn-md-fill-blue",
//            "send",
//            thisEvents(onClick)
//              .sample(inputElement.value)
//              .flatMap { inputValue =>
//                /* <focus> */
//                Fetch
//                  .post("https://httpbin.org/anything", body = jsonRequestBody(Data(s = inputValue))) // Data has an implicit Encoder[Data] auto-derived
//                  .decode[Json]                                                                       // Json has a corresponding implicit Decoder[Json]
//                /* </focus> */
//              } --> responseReceived,
//          ),
//        ),
//        div(
//          div(
//            code("received:")
//          ),
//          div(
//            cls := "flex flex-col space-y-4 p-4 max-h-96 overflow-auto bg-gray-900",
//            children.command <-- responsesStream.recoverToTry.map {
//              case Success(response) =>
//                CollectionCommand.Append(
//                  div(
//                    div(
//                      cls := "flex space-x-2 items-center",
//                      code(cls := "text-green-500", "Status: "),
//                      code(cls := "text-green-300", s"${response.status} ${response.statusText}")
//                    ),
//                    div(
//                      cls := "text-green-400 text-xs",
//                      pre(response.data.spaces2)
//                    )
//                  )
//                )
//              case Failure(exception) =>
//                CollectionCommand.Append(
//                  div(
//                    div(
//                      cls := "flex space-x-2 items-center",
//                      code(cls := "text-red-500", "Error: "),
//                      code(cls := "text-red-300", exception.getMessage)
//                    )
//                  )
//                )
//            }
//          )
//        )
//      )
    })
