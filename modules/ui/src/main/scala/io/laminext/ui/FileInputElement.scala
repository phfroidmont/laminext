package io.laminext.ui

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.base.ComponentBase
import io.laminext.validation.ValidatedValue
import org.scalajs.dom.html
import org.scalajs.dom.raw.File

class FileInputElement[Err, Out](
  val el: ReactiveHtmlElement[html.Button],
  val value: Signal[Seq[File]],
  val validatedValue: Signal[ValidatedValue[Err, Out]],
  val validationError: Signal[Option[Err]]
) extends ComponentBase[html.Button]

object FileInputElement {

  type Styling = Signal[Status] => Mod[HtmlElement]

  object Styling {
    @inline def classes(styling: Status => String): Styling =
      $status => cls <-- $status.map(styling)
  }

  sealed trait Status extends Product with Serializable
  object Status {
    case object Ready     extends Status
    case object Selecting extends Status
    case object Invalid   extends Status
  }

}
