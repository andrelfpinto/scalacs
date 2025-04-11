package scalacs.data.mutable

object DoublyLinkedList {
  // null for last node
  class Node[T](var data: T, var prev: Node[T], var next: Node[T])
}

class DoublyLinkedList[T] {
  import DoublyLinkedList.Node

  var head: Node[T] = null
  var last: Node[T] = null

  def isEmpty: Boolean = head == null

  def foreachRight(f: T => Unit) = {
    var current = head
    while (current != null) {
      f(current.data)
      current = current.next
    }
  }

  def foreachLeft(f: T => Unit) = {
    var current = last
    while (current != null) {
      f(current.data)
      current = current.prev
    }
  }

  // TODO make ascii art
  def insertAfter(node: Node[T], newNode: Node[T]) =
    newNode.prev = node
    if (node.next == null)
      newNode.next = null
      last = newNode
    else
      newNode.next = node.next
      node.next.prev = newNode
    node.next = newNode

  def insertBefore(node: Node[T], newNode: Node[T]) =
    newNode.next = node
    if (node.prev == null)
      newNode.prev = null
      head = newNode
    else
      newNode.prev = node.prev
      node.prev.next = newNode
    node.prev = newNode

  def insertHead(newNode: Node[T]) =
    if (head == null)
      head = newNode
      last = newNode
      newNode.prev = null
      newNode.next = null
    else insertBefore(head, newNode)

  def insertLast(newNode: Node[T]) =
    if (last == null)
      // TODO make similar to insertHead?
      insertHead(newNode)
    else insertAfter(last, newNode)
}
