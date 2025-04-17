package scalacs.data.mutable
package ops

trait HasFilter[L[_]]:
  extension [T](list: L[T])
    /** Creates a new collection of the same type containing only the elements from the original
      * collection that satisfy the given predicate. The original collection remains unchanged.
      *
      * @param p
      *   The predicate function that determines which elements to include in the new filtered
      *   collection. The function takes an element of type `T` and returns `true` if the element
      *   should be included, and `false` otherwise.
      * @return
      *   A new collection of type `L[T]` containing only the elements that satisfy the predicate
      *   `p`.
      */
    def filter(p: T => Boolean): L[T]
