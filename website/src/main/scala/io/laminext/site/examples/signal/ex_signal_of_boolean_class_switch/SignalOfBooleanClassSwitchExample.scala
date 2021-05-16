package io.laminext.site.examples.signal.ex_signal_of_boolean_class_switch

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SignalOfBooleanClassSwitchExample
    extends CodeExample(
      id = "example-signal-of-boolean-class-switch",
      title = "Signal of Boolean Class Switch",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.core._
      import com.raquo.laminar.api.L._

      val booleanVar = Var(false)

      div(
        cls := "space-y-4",
        div(
          button(
            cls := "btn-md-fill-blue",
            "toggle",
            onClick --> booleanVar.toggleObserver
          )
        ),
        div(
          cls := "flex space-x-4 items-center  ",
          code("signal:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- booleanVar.signal.map(_.toString)
          )
        ),
        div(
          cls := "p-8",
          /* <focus> */
          booleanVar.signal.classSwitch(
            whenTrue = "bg-green-600 text-green-50",
            whenFalse = "bg-yellow-600 text-yellow-50",
          ),
          /* </focus> */
          "class switch"
        )
      )
    })
