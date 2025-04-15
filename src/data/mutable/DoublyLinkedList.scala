package scalacs.data.mutable

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

  /** Inserts a new node with the given data at the beginning (head) of the list. Updates both the
    * `next` pointer of the new node and the `prev` pointer of the former head. If the list is
    * empty, the new node becomes both the head and the last node.
    *
    * @param newData
    *   The data to be inserted into the new head node.
    */
  def insertHead(newData: T): Unit = {
    val newNode = Node(newData)
    if (head == null) {
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
    if (last == null) {
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
    if (head == null)
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
    if (last == null)
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

  /** Applies the given function `f` to each element in the list, traversing from the head to the
    * last node (rightward).
    *
    * @param f
    *   The function to be applied to each element.
    */
  def foreachRight(f: T => Unit): Unit = {
    var current = head
    while (current != null) {
      f(current.data)
      current = current.next
    }
  }

  /** Applies the given function `f` to each element in the list, traversing from the last node to
    * the head (leftward).
    *
    * @param f
    *   The function to be applied to each element.
    */
  def foreachLeft(f: T => Unit): Unit = {
    var current = last
    while (current != null) {
      f(current.data)
      current = current.prev
    }
  }
}
