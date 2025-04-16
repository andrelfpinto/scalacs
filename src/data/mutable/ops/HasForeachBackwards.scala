package scalacs.data.mutable
package ops

trait HasForeachBackwards[L[_]]:
  extension [T](list: L[T])
    /** Applies the given function `f` to each element in the list, traversing from the last node to
      * the head (backwards).
      *
      * @param f
      *   The function to be applied to each element.
      */
    def foreachBackwards(f: T => Unit): Unit
