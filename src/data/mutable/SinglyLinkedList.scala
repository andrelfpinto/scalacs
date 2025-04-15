package scalacs.data.mutable

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
    if (head == null)
      throw new NoSuchElementException("Cannot extract head from an empty list")
    else {
      val data = head.data
      head = head.next
      data
    }

  /** Applies the given function `f` to each element in the linked list.
    *
    * @param f
    *   The function to be applied to each element.
    */
  def foreach(f: T => Unit): Unit = {
    var current = head
    while (current != null) {
      f(current.data)
      current = current.next
    }
  }
}
