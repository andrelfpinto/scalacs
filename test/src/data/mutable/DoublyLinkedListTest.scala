package scalacs.data.mutable

import munit.FunSuite

class DoublyLinkedListSuite extends FunSuite {
  test("isEmpty should return true for an empty list") {
    val list = new DoublyLinkedList[Int]()
    assert(list.isEmpty)
  }

  test("isEmpty should return false for a non-empty list") {
    val list = new DoublyLinkedList[String]()
    list.insertHead("hello")
    assert(!list.isEmpty)

    list.extractHead()
    list.insertLast("world")
    assert(!list.isEmpty)
  }

  test("insertHead should add a new element to the beginning of the list") {
    val list = new DoublyLinkedList[Int]()
    list.insertHead(1)
    assertEquals(list.head.data, 1)
    assertEquals(list.head.next, null)
    assertEquals(list.head.prev, null)
    assertEquals(list.last, list.head)

    list.insertHead(2)
    assertEquals(list.head.data, 2)
    assertEquals(list.head.next.data, 1)
    assertEquals(list.head.next.next, null)
    assertEquals(list.head.prev, null)
    assertEquals(list.head.next.prev.data, 2)
    assertEquals(list.last.data, 1)
  }

  test("insertHead on an empty list should set both head and last") {
    val list = new DoublyLinkedList[Int]()
    list.insertHead(1)
    assertEquals(list.head.data, 1)
    assertEquals(list.last.data, 1)
    assertEquals(list.head, list.last)
    assertEquals(list.head.next, null)
    assertEquals(list.head.prev, null)
  }

  test("insertLast should add a new element to the end of the list") {
    val list = new DoublyLinkedList[Int]()
    list.insertLast(1)
    assertEquals(list.head.data, 1)
    assertEquals(list.head.next, null)
    assertEquals(list.head.prev, null)
    assertEquals(list.last, list.head)

    list.insertLast(2)
    assertEquals(list.last.data, 2)
    assertEquals(list.last.prev.data, 1)
    assertEquals(list.last.next, null)
    assertEquals(list.head.data, 1)
    assertEquals(list.head.next.data, 2)
    assertEquals(list.head.next.prev.data, 1)
  }

  test("insertLast on an empty list should set both head and last") {
    val list = new DoublyLinkedList[Int]()
    list.insertLast(1)
    assertEquals(list.head.data, 1)
    assertEquals(list.last.data, 1)
    assertEquals(list.head, list.last)
    assertEquals(list.head.next, null)
    assertEquals(list.head.prev, null)
  }

  test("extractHead should remove and return the first element of the list") {
    val list = new DoublyLinkedList[String]()
    list.insertHead("a")
    list.insertHead("b")
    list.insertHead("c")

    assertEquals(list.extractHead(), "c")
    assertEquals(list.head.data, "b")
    assertEquals(list.head.next.data, "a")
    assertEquals(list.head.next.next, null)
    assertEquals(list.head.prev, null)
    assertEquals(list.last.data, "a")
    assertEquals(list.last.prev.data, "b")

    assertEquals(list.extractHead(), "b")
    assertEquals(list.head.data, "a")
    assertEquals(list.head.next, null)
    assertEquals(list.head.prev, null)
    assertEquals(list.last.data, "a")
    assertEquals(list.last.prev, null)

    assertEquals(list.extractHead(), "a")
    assertEquals(list.head, null)
    assertEquals(list.last, null)
  }

  test("extractHead on a single element list should make the list empty") {
    val list = new DoublyLinkedList[Int]()
    list.insertHead(1)
    assertEquals(list.extractHead(), 1)
    assertEquals(list.head, null)
    assertEquals(list.last, null)
  }

  test("extractHead should throw NoSuchElementException on an empty list") {
    val list = new DoublyLinkedList[Double]()
    intercept[NoSuchElementException] {
      list.extractHead()
    }
    assertEquals(list.head, null)
    assertEquals(list.last, null)
  }

  test("extractLast should remove and return the last element of the list") {
    val list = new DoublyLinkedList[String]()
    list.insertLast("a")
    list.insertLast("b")
    list.insertLast("c")

    assertEquals(list.extractLast(), "c")
    assertEquals(list.last.data, "b")
    assertEquals(list.last.prev.data, "a")
    assertEquals(list.last.next, null)
    assertEquals(list.head.data, "a")
    assertEquals(list.head.next.data, "b")

    assertEquals(list.extractLast(), "b")
    assertEquals(list.last.data, "a")
    assertEquals(list.last.prev, null)
    assertEquals(list.last.next, null)
    assertEquals(list.head.data, "a")
    assertEquals(list.head.next, null)

    assertEquals(list.extractLast(), "a")
    assertEquals(list.head, null)
    assertEquals(list.last, null)
  }

  test("extractLast on a single element list should make the list empty") {
    val list = new DoublyLinkedList[Int]()
    list.insertLast(1)
    assertEquals(list.extractLast(), 1)
    assertEquals(list.head, null)
    assertEquals(list.last, null)
  }

  test("extractLast should throw NoSuchElementException on an empty list") {
    val list = new DoublyLinkedList[Double]()
    intercept[NoSuchElementException] {
      list.extractLast()
    }
    assertEquals(list.head, null)
    assertEquals(list.last, null)
  }

  test("foreachRight should apply the given function to each element") {
    val list = new DoublyLinkedList[Int]()
    list.insertHead(1)
    list.insertHead(2)
    list.insertHead(3)

    var sum = 0
    list.foreachRight(sum += _)
    assertEquals(sum, 6)

    val stringList = new DoublyLinkedList[String]()
    stringList.insertHead("a")
    stringList.insertHead("b")
    stringList.insertHead("c")

    var result = ""
    stringList.foreachRight(result += _)
    assertEquals(result, "cba")
  }

  test("foreachRight should do nothing on an empty list") {
    val list  = new DoublyLinkedList[Int]()
    var count = 0
    list.foreachRight(_ => count += 1)
    assertEquals(count, 0)
  }

  test("foreachLeft should apply the given function to each element in reverse order") {
    val list = new DoublyLinkedList[Int]()
    list.insertLast(1)
    list.insertLast(2)
    list.insertLast(3)

    var sum = 0
    list.foreachLeft(sum += _)
    assertEquals(sum, 6)

    val stringList = new DoublyLinkedList[String]()
    stringList.insertLast("a")
    stringList.insertLast("b")
    stringList.insertLast("c")

    var resultString = ""
    stringList.foreachLeft(resultString += _)
    assertEquals(resultString, "cba")
  }

  test("foreachLeft should do nothing on an empty list") {
    val list  = new DoublyLinkedList[Int]()
    var count = 0
    list.foreachLeft(_ => count += 1)
    assertEquals(count, 0)
  }
}
