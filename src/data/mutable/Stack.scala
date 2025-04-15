package scalacs.data.mutable

/** A mutable implementation of a last-in-first-out (LIFO) stack using a singly linked list.
  *
  * @tparam T
  *   The type of elements stored in the stack.
  */
class Stack[T]:
  private val items = SinglyLinkedList[T]()

  /** Adds an element to the top of the stack.
    *
    * @param element
    *   The element to add.
    */
  def push(element: T): Unit =
    items.insertHead(element)

  /** Removes and returns the element at the top of the stack.
    *
    * @return
    *   The data of the former top.
    * @throws NoSuchElementException
    *   if the stack is empty.
    */
  def pop(): T =
    items.extractHead()

  /** Checks if the stack is currently empty.
    *
    * @return
    *   `true` if the stack contains no elements, `false` otherwise.
    */
  def isEmpty: Boolean =
    items.isEmpty
