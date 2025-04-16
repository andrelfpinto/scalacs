package scalacs.data.mutable
package ops

trait HasFind[L[_]]:
  extension [T](list: L[T])
    /** Finds the first element in the collection that satisfies the given predicate.
      *
      * @param p
      *   The predicate function to test each element.
      * @return
      *   An Option containing the first element that satisfies the predicate, or None if no such
      *   element is found.
      */
    def find(p: T => Boolean): Option[T]
