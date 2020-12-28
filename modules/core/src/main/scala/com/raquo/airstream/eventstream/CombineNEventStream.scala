package com.raquo.airstream.eventstream

import com.raquo.airstream.features.CombinedSeqObservable
import com.raquo.airstream.features.CombineObservable
import com.raquo.airstream.features.InternalParentObserver

import scala.util.Try

abstract class CombineNEventStream[A, Out](
  protected[this] val parents: Seq[EventStream[A]]
) extends EventStream[Out]
    with CombineObservable[Out] {

  override protected[airstream] val topoRank: Int = if (parents.nonEmpty) {
    parents.map(_.topoRank).max + 1
  } else {
    1
  }

  private[this] val maybeLastParentValues: Array[Option[Try[A]]] = Array.fill(parents.size)(None)

  parentObservers.push(
    parents.zipWithIndex.map { case (parent, index) =>
      InternalParentObserver.fromTry[A](
        parent,
        (nextParentValue, transaction) => {
          maybeLastParentValues(index) = Some(nextParentValue)
          if (maybeLastParentValues.forall(_.isDefined)) {
            internalObserver.onTry(
              CombinedSeqObservable.guardedSeqCombinator(maybeLastParentValues.toIndexedSeq.map(_.get), toOut),
              transaction
            )
          }
        }
      )
    }: _*
  )

  protected def toOut(seq: Seq[A]): Out

  override protected[this] def onStop(): Unit = {
    maybeLastParentValues.indices.foreach(maybeLastParentValues(_) = None)
    super.onStop()
  }

}