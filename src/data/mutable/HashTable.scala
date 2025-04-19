package scalacs.data.mutable

import scalacs.data.mutable.SinglyLinkedList as List
import scalacs.data.mutable.SinglyLinkedList.ops.given
import ops.HasContains

/** A hash table implementation using a fixed-size array and separate chaining for collision
  * resolution.
  *
  * @tparam K
  *   The type of the keys.
  * @tparam V
  *   The type of the values.
  * @param initialCapacity
  *   The initial capacity of the underlying array. Defaults to 16.
  */
class HashTable[K, V](initialCapacity: Int = 16) {
  private var table = new Array[List[(K, V)]](initialCapacity)
  private var size  = 0

  /** Returns the current number of key-value pairs in the hash table.
    *
    * @return
    *   The size of the hash table.
    */
  def getSize: Int = size

  /** Checks if the hash table is empty.
    *
    * @return
    *   `true` if the hash table contains no elements, `false` otherwise.
    */
  def isEmpty: Boolean = size == 0

  /** Calculates the index in the underlying array for a given key.
    *
    * @param key
    *   The key to get the index for.
    * @return
    *   The index in the `table` array.
    */
  private def getIndex(key: K): Int =
    key.hashCode().abs % table.length

  /** Inserts a key-value pair into the hash table. If the key already exists, the value associated
    * with it is updated.
    *
    * @param key
    *   The key to insert or update.
    * @param value
    *   The value to associate with the key.
    */
  def put(key: K, value: V): Unit = {
    val index = getIndex(key)
    // Check if the bucket at the calculated index is currently empty.
    if (table(index) == null)
      val newList = List[(K, V)]()
      newList.insertHead((key, value))
      table(index) = newList
      size += 1
    else
      // If the bucket is not empty, check if the key already exists in the list.
      table(index).find(_._1 == key) match {
        // If the key is found, update the value in the existing pair.
        case Some(pair) =>
          val newList = table(index).filter(_._1 != key)
          newList.insertHead((key, value))
          table(index) = newList
        // If the key is not found in the list, insert the new key-value pair.
        case None =>
          table(index).insertHead((key, value))
          size += 1
      }
  }

  /** Optionally retrieves the value associated with a given key.
    *
    * @param key
    *   The key to look up.
    * @return
    *   An `Option` containing the value if the key is found, `None` otherwise.
    */
  def get(key: K): Option[V] = {
    val index = getIndex(key)
    if (table(index) != null)
      table(index).find(_._1 == key).map(_._2)
    else
      None
  }

  /** Retrieves the value associated with a given key.
    *
    * @param key
    *   The key to look up.
    * @return
    *   The value associated with the key.
    * @throws NoSuchElementException
    *   If the key is not found in the hash table.
    */
  def apply(key: K): V =
    get(key) match {
      case Some(v) => v
      case None    => throw new NoSuchElementException(s"Key not found: $key")
    }
}

object HashTable:
  object ops:
    type HashTableKey[K] = HashTable[K, ?]

    given HasContains[HashTableKey]:
      extension [K](table: HashTableKey[K])
        def contains(key: K): Boolean =
          table.get(key).isDefined
