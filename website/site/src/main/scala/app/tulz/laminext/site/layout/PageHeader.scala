package app.tulz.laminext.site

package layout

import app.tulz.highlightjs
import com.raquo.laminar.api.L.{transition => _, _}
import app.tulz.tailwind._
import app.tulz.laminar.ext._
import app.tulz.laminext.site.Page
import app.tulz.laminext.site.Site
import app.tulz.laminext.site.SiteModule
import app.tulz.laminext.site.icons.Icons
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.raw.HTMLInputElement

object PageHeader {

  def apply(
    $module: Signal[Option[SiteModule]],
    $page: Signal[Option[Page]],
    $highlightStyle: Signal[String],
    highlightStyleObserver: Observer[String]
  ): ReactiveHtmlElement.Base = {
    val styleDropDownOpen = Var(false)
    val styleSearch       = Var("")
    val searchInput = input(
      cls := "appearance-none block w-full px-3 py-2 rounded-md text-cool-gray-900 border border-gray-300 placeholder-gray-400 focus:outline-none focus:ring-1 focus:ring-blue-500 focus:border-blue-500 transition duration-150 ease-in-out",
      placeholder := "search..."
    )
    searchInput.amend(
      searchInput.valueSignal --> styleSearch.writer
    )

    div(
      cls := "bg-cool-gray-900 text-white flex p-4 items-center space-x-8",
      nav(
        cls := "flex-1 flex space-x-4 justify-start",
        Site.modules.map { module =>
          a(
            cls <-- $module.map { currentModule =>
              Seq(
                "border-b-2 px-2 border-transparent flex text-lg font-medium"     -> true,
                "text-cool-gray-300 hover:border-cool-gray-300 hover:text-white " -> !currentModule.exists(_.path == module.path),
                "border-cool-gray-300 text-white"                                 -> currentModule.exists(_.path == module.path)
              )
            },
            href := s"/${module.path}",
            module.index.title
          )
        }
      ),
      div(
        cls := "relative inline-block text-left",
        div(
          button.btn.sm.text.white(
            `type` := "button",
            aria.hasPopup := true,
            aria.expanded <-- styleDropDownOpen.signal,
            onClick --> { _ => styleDropDownOpen.toggle() },
            "code theme",
            Icons.chevronDown(svg.cls := "-mr-1 ml-2 h-4 fill-current text-cool-gray-300").hiddenIf(styleDropDownOpen.signal),
            Icons.chevronUp(svg.cls := "-mr-1 ml-2 h-4 fill-current text-cool-gray-300").visibleIf(styleDropDownOpen.signal)
          )
        ),
        div(
          transition(styleDropDownOpen.signal),
          cls := "origin-top-right absolute max-h-128 overflow-auto right-0 mt-2 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 z-20 p-2",
          div(
            cls := "py-1",
            role := "menu",
            aria.orientation := "vertical",
            aria.labelledBy := "options-menu",
            div(
              cls := "mb-2",
              searchInput
            ),
            highlightjs.Styles.styles.map(style =>
              button(
                cls := "block w-full px-4 py-2 text-sm text-left",
                cls <-- styleSearch.signal.map(search => !style.contains(search)).cls("hidden"),
                cls <-- $highlightStyle.map { currentStyle =>
                  Seq(
                    "text-cool-gray-700 hover:bg-cool-gray-200 hover:text-cool-gray-900" -> (currentStyle != style),
                    "text-cool-gray-100 bg-cool-gray-600"                                -> (currentStyle == style)
                  )
                },
                onClick.mapTo(style) --> highlightStyleObserver,
                role := "menuitem",
                style
              )
            )
          )
        )
      )
    )
  }

}