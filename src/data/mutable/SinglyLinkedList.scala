package scalacs.data.mutable

import ops.{HasForeach, HasFind, HasReverse, HasFilter, HasContains}

/** Companion object for the SinglyLinkedList, containing the Node definition.
  */
object SinglyLinkedList {

  /** Represents a node in a singly linked list.
    *
    * @tparam T
    *   The type of data stored in the node.
    * @param data
    *   The data held by this node.
    * @param next
    *   A reference to the next node in the list, or null if this is the last node.
    */
  class Node[T](var data: T, var next: Node[T] = null)

  object ops:
    /** Provides a `foreach` extension method for `SinglyLinkedList`. */
    given HasForeach[SinglyLinkedList]:
      extension [T](list: SinglyLinkedList[T])
        def foreach(f: T => Unit): Unit =
          var current = list.head
          while (current != null) {
            f(current.data)
            current = current.next
          }

    /** Provides a `find` extension method for `SinglyLinkedList`. */
    given HasFind[SinglyLinkedList]:
      extension [T](list: SinglyLinkedList[T])
        def find(p: T => Boolean): Option[T] =
          var current = list.head
          while (current != null) {
            if (p(current.data)) return Some(current.data)
            current = current.next
          }
          return None

    given HasReverse[SinglyLinkedList]:
      extension [T](list: SinglyLinkedList[T])
        def reverse: SinglyLinkedList[T] =
          val reversedList = new SinglyLinkedList[T]()
          list.foreach { element =>
            reversedList.insertHead(element)
          }
          reversedList

    given HasFilter[SinglyLinkedList]:
      extension [T](list: SinglyLinkedList[T])
        def filter(p: T => Boolean): SinglyLinkedList[T] =
          val filteredList = new SinglyLinkedList[T]()
          list.foreach { element =>
            if (p(element))
              filteredList.insertHead(element)
          }
          filteredList.reverse

    given HasContains[SinglyLinkedList]:
      extension [T](list: SinglyLinkedList[T])
        def contains(elem: T): Boolean =
          list.find(_ == elem).isDefined
}

/** A mutable singly linked list implementation.
  *
  * @tparam T
  *   The type of elements stored in the list.
  */
class SinglyLinkedList[T] {
  import SinglyLinkedList.Node

  /** The head of the linked list. Null if the list is empty.
    */
  var head: Node[T] = null

  /** Checks if the linked list is empty.
    *
    * @return
    *   `true` if the list is empty (head is null), `false` otherwise.
    */
  def isEmpty: Boolean =
    head == null

  /** Inserts a new node with the given data at the beginning (head) of the list.
    *
    * @param newData
    *   The data to be inserted into the new head node.
    */
  def insertHead(newData: T): Unit = {
    val newNode = Node(newData)
    newNode.next = head
    head = newNode
  }

  /** Extracts and returns the data from the head of the list, removing the head node.
    *
    * @throws NoSuchElementException
    *   if the list is empty.
    * @return
    *   The data of the former head.
    */
  def extractHead(): T =
    if (isEmpty)
      throw new NoSuchElementException("Cannot extract head from an empty list")
    else {
      val data = head.data
      head = head.next
      data
    }
}
