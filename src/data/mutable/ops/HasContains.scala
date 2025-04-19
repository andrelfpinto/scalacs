package scalacs.data.mutable
package ops

trait HasContains[C[_]]:
  extension [T](collection: C[T])
    /** Checks if the collection contains an element.
      *
      * @param elem
      *   The element to check for existence.
      * @return
      *   `true` if the element exists in the collection, `false` otherwise.
      */
    def contains(elem: T): Boolean
