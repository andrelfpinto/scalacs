package scalacs.data.mutable

object LinkedList {
  // null for last node
  class Node[T](var data: T, var next: Node[T])
}

class LinkedList[T] {
  import LinkedList.Node

  var head: Node[T] = null

  def isEmpty: Boolean = head == null

  def foreach(f: T => Unit) = {
    var current = head
    while (current != null) {
      f(current.data)
      current = current.next
    }
  }

  def insertAfter(node: Node[T], newNode: Node[T]) =
    newNode.next = node.next
    node.next = newNode

  def insertHead(newNode: Node[T]) =
    newNode.next = head
    head = newNode

  def deleteAfter(node: Node[T]) =
    obsoleteNode := node.next
    node.next := node.next.next
    destroy obsoleteNode

  def deleteHead() =
    obsoleteNode := list.firstNode
    list.firstNode := list.firstNode.next // point past deleted node
    destroy obsoleteNode
}
