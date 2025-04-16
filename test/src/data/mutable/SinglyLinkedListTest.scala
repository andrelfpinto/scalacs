package scalacs.data.mutable

import scalacs.data.mutable.SinglyLinkedList.ops.given

class SinglyLinkedListSuite extends munit.FunSuite {
  test("isEmpty should return true for an empty list") {
    val list = new SinglyLinkedList[Int]()
    assert(list.isEmpty)
  }

  test("isEmpty should return false for a non-empty list") {
    val list = new SinglyLinkedList[String]()
    list.insertHead("hello")
    assert(!list.isEmpty)
  }

  test("insertHead should add a new element to the beginning of the list") {
    val list = new SinglyLinkedList[Int]()
    list.insertHead(1)
    assertEquals(list.head.data, 1)
    assertEquals(list.head.next, null)

    list.insertHead(2)
    assertEquals(list.head.data, 2)
    assertEquals(list.head.next.data, 1)
    assertEquals(list.head.next.next, null)
  }

  test("extractHead should remove and return the first element of the list") {
    val list = new SinglyLinkedList[String]()
    list.insertHead("a")
    list.insertHead("b")
    list.insertHead("c")

    assertEquals(list.extractHead(), "c")
    assertEquals(list.head.data, "b")
    assertEquals(list.head.next.data, "a")
    assertEquals(list.head.next.next, null)

    assertEquals(list.extractHead(), "b")
    assertEquals(list.head.data, "a")
    assertEquals(list.head.next, null)

    assertEquals(list.extractHead(), "a")
    assertEquals(list.head, null)
  }

  test("extractHead should throw NoSuchElementException on an empty list") {
    val list = new SinglyLinkedList[Double]()
    intercept[NoSuchElementException] {
      list.extractHead()
    }
    assertEquals(list.head, null)
  }

  test("foreach should apply the given function to each element") {
    val list = new SinglyLinkedList[Int]()
    list.insertHead(1)
    list.insertHead(2)
    list.insertHead(3)

    var sum = 0
    list.foreach(sum += _)
    assertEquals(sum, 6)

    val stringList = new SinglyLinkedList[String]()
    stringList.insertHead("a")
    stringList.insertHead("b")
    stringList.insertHead("c")

    var result = ""
    stringList.foreach(result += _)
    assertEquals(result, "cba")
  }

  test("foreach should do nothing on an empty list") {
    val list  = new SinglyLinkedList[Int]()
    var count = 0
    list.foreach(_ => count += 1)
    assertEquals(count, 0)
  }
}
