package scalacs.data.mutable

class QueueSuite extends munit.FunSuite {
  test("enqueue elements at the end and dequeue in FIFO order") {
    val queue = Queue[Int]()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    assertEquals(queue.dequeue(), 1)
    assertEquals(queue.dequeue(), 2)
    assertEquals(queue.dequeue(), 3)
  }

  test("dequeue throws NoSuchElementException on an empty queue") {
    val queue = Queue[Int]()
    assert(queue.isEmpty)
    intercept[NoSuchElementException] {
      queue.dequeue()
    }
  }

  test("handle a mix of enqueue and dequeue operations") {
    val queue = Queue[Char]()
    queue.enqueue('a')
    queue.enqueue('b')
    assertEquals(queue.dequeue(), 'a')
    queue.enqueue('c')
    assertEquals(queue.dequeue(), 'b')
    queue.enqueue('d')
    assertEquals(queue.dequeue(), 'c')
    assertEquals(queue.dequeue(), 'd')
    intercept[NoSuchElementException] {
      queue.dequeue()
    }
  }

  test("isEmpty returns true for a newly created queue") {
    val queue = Queue[Int]()
    assert(queue.isEmpty)
  }

  test("isEmpty returns false after enqueuing an element") {
    val queue = Queue[Int]()
    queue.enqueue(1)
    assert(!queue.isEmpty)
  }

  test("isEmpty returns true after enqueuing and then dequeueing all elements") {
    val queue = Queue[Int]()
    queue.enqueue(1)
    queue.dequeue()
    assert(queue.isEmpty)
  }
}
