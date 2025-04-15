package scalacs.data.mutable

class StackSuite extends munit.FunSuite {
  test("push elements at the end and pop in LIFO order") {
    val stack = Stack[Int]()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    assertEquals(stack.pop(), 3)
    assertEquals(stack.pop(), 2)
    assertEquals(stack.pop(), 1)
  }

  test("pop throws NoSuchElementException on an empty stack") {
    val stack = Stack[Int]()
    assert(stack.isEmpty)
    intercept[NoSuchElementException] {
      stack.pop()
    }
  }

  test("handle a mix of push and pop operations") {
    val stack = Stack[Char]()
    stack.push('a')
    stack.push('b')
    assertEquals(stack.pop(), 'b')
    stack.push('c')
    assertEquals(stack.pop(), 'c')
    stack.push('d')
    assertEquals(stack.pop(), 'd')
    assertEquals(stack.pop(), 'a')
    intercept[NoSuchElementException] {
      stack.pop()
    }
  }

  test("isEmpty returns true for a newly created stack") {
    val stack = Stack[Int]()
    assert(stack.isEmpty)
  }

  test("isEmpty returns false after pushing an element") {
    val stack = Stack[Int]()
    stack.push(1)
    assert(!stack.isEmpty)
  }

  test("isEmpty returns true after pushing and then popping all elements") {
    val stack = Stack[Int]()
    stack.push(1)
    stack.pop()
    assert(stack.isEmpty)
  }
}
