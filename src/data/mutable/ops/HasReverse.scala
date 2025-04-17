package scalacs.data.mutable
package ops

trait HasReverse[L[_]]:
  extension [T](list: L[T])
    /** Creates a new collection of the same type with the elements in reverse order. The original
      * collection remains unchanged.
      *
      * @return
      *   A new collection of type `L[T]` with the elements of the original collection in reversed
      *   order.
      */
    def reverse: L[T]
