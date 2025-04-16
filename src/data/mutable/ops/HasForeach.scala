package scalacs.data.mutable
package ops

trait HasForeach[L[_]]:
  extension [T](list: L[T])
    /** Applies the given function `f` to each element in the list.
      *
      * @param f
      *   The function to be applied to each element.
      */
    def foreach(f: T => Unit): Unit
