package scalacs.data.mutable

import ops.{HasForeach, HasForeachBackwards}

/** Companion object for the DoublyLinkedList, containing the Node definition.
  */
object DoublyLinkedList {

  /** Represents a node in a doubly linked list.
    *
    * @tparam T
    *   The type of data stored in the node.
    * @param data
    *   The data held by this node.
    * @param prev
    *   A reference to the previous node in the list, or null if this is the first node.
    * @param next
    *   A reference to the next node in the list, or null if this is the last node.
    */
  class Node[T](var data: T, var prev: Node[T] = null, var next: Node[T] = null)

  object ops:
    /** Provides a `foreach` extension method for `DoublyLinkedList`. */
    given HasForeach[DoublyLinkedList]:
      extension [T](list: DoublyLinkedList[T])
        def foreach(f: T => Unit): Unit =
          var current = list.head
          while (current != null) {
            f(current.data)
            current = current.next
          }

    /** Provides a `foreachBackwards` extension method for `DoublyLinkedList`. */
    given HasForeachBackwards[DoublyLinkedList]:
      extension [T](list: DoublyLinkedList[T])
        def foreachBackwards(f: T => Unit): Unit =
          var current = list.last
          while (current != null) {
            f(current.data)
            current = current.prev
          }
}

/** A mutable doubly linked list implementation.
  *
  * @tparam T
  *   The type of elements stored in the list.
  */
class DoublyLinkedList[T] {
  import DoublyLinkedList.Node

  /** The head of the doubly linked list. Null if the list is empty.
    */
  var head: Node[T] = null

  /** The last node of the doubly linked list. Null if the list is empty.
    */
  var last: Node[T] = null

  /** Checks if the doubly linked list is empty.
    *
    * @return
    *   `true` if the list is empty (head is null, which also implies last is null), `false`
    *   otherwise.
    */
  def isEmpty: Boolean =
    head == null

  /** Inserts a new node with the given data at the beginning (head) of the list. Updates both the
    * `next` pointer of the new node and the `prev` pointer of the former head. If the list is
    * empty, the new node becomes both the head and the last node.
    *
    * @param newData
    *   The data to be inserted into the new head node.
    */
  def insertHead(newData: T): Unit = {
    val newNode = Node(newData)
    if (isEmpty) {
      head = newNode
      last = newNode
    } else {
      newNode.next = head
      head.prev = newNode
      head = newNode
    }
  }

  /** Inserts a new node with the given data at the end (last) of the list. Updates both the `prev`
    * pointer of the new node and the `next` pointer of the former last node. If the list is empty,
    * the new node becomes both the head and the last node.
    *
    * @param newData
    *   The data to be inserted into the new last node.
    */
  def insertLast(newData: T): Unit = {
    val newNode = Node(newData)
    if (isEmpty) {
      head = newNode
      last = newNode
    } else {
      newNode.prev = last
      last.next = newNode
      last = newNode
    }
  }

  /** Extracts and returns the data from the head of the list, removing the head node. Updates the
    * `prev` pointer of the new head (if it exists) to null.
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
      if (head != null)
        head.prev = null
      else
        last = null // List is now empty
      data
    }

  /** Extracts and returns the data from the last node of the list, removing the last node. Updates
    * the `next` pointer of the new last node (if it exists) to null.
    *
    * @throws NoSuchElementException
    *   if the list is empty.
    * @return
    *   The data of the former last node.
    */
  def extractLast(): T =
    if (isEmpty)
      throw new NoSuchElementException("Cannot extract last from an empty list")
    else {
      val data = last.data
      last = last.prev
      if (last != null)
        last.next = null
      else
        head = null // List is now empty
      data
    }
}
