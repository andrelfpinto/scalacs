package scalacs.data.mutable

/** A mutable implementation of a First-In-First-Out (FIFO) queue using a doubly linked list.
  *
  * @tparam T
  *   The type of elements stored in the queue.
  */
class Queue[T]:
  private val items = DoublyLinkedList[T]()

  /** Adds an element to the end of the queue.
    *
    * @param element
    *   The element to enqueue.
    */
  def enqueue(element: T): Unit =
    items.insertLast(element)

  /** Removes and returns the element at the front of the queue.
    *
    * @return
    *   The data of the former head.
    * @throws NoSuchElementException
    *   if the queue is empty.
    */
  def dequeue(): T =
    items.extractHead()

  /** Checks if the queue is currently empty.
    *
    * @return
    *   `true` if the queue contains no elements, `false` otherwise.
    */
  def isEmpty: Boolean =
    items.isEmpty
