package scalacs.data.mutable

class HashTableSuite extends munit.FunSuite {
  test("empty hash table should have size 0 and be empty") {
    val ht = new HashTable[String, Int]()
    assertEquals(ht.getSize, 0)
    assert(ht.isEmpty)
  }

  test("put and retrieve a single element") {
    val ht = new HashTable[Int, String]()
    ht.put(1, "apple")
    assertEquals(ht.getSize, 1)
    assert(!ht.isEmpty)
    assertEquals(ht.apply(1), "apple")
    intercept[NoSuchElementException](ht.apply(2))
  }

  test("put and retrieve multiple elements") {
    val ht = new HashTable[String, Int]()
    ht.put("apple", 1)
    ht.put("banana", 2)
    ht.put("cherry", 3)
    assertEquals(ht.getSize, 3)
    assertEquals(ht.apply("apple"), 1)
    assertEquals(ht.apply("banana"), 2)
    assertEquals(ht.apply("cherry"), 3)
    intercept[NoSuchElementException](ht.apply("date"))
  }

  test("put with duplicate key updates the value") {
    val ht = new HashTable[String, Int]()
    ht.put("apple", 1)
    assertEquals(ht.apply("apple"), 1)
    ht.put("apple", 10)
    assertEquals(ht.getSize, 1)
    assertEquals(ht.apply("apple"), 10)
  }

  test("handle collisions (multiple keys hashing to the same index)") {
    // Force collisions by using keys that are likely to have the same hash code modulo table size
    val ht = new HashTable[Int, String](initialCapacity = 2)
    ht.put(1, "one")
    ht.put(3, "three") // (1 % 2 == 1) and (3 % 2 == 1) - likely collision
    assertEquals(ht.getSize, 2)
    assertEquals(ht.apply(1), "one")
    assertEquals(ht.apply(3), "three")
    intercept[NoSuchElementException](ht.apply(5))

    ht.put(1, "updated one")
    assertEquals(ht.apply(1), "updated one")
    assertEquals(ht.getSize, 2)
  }

  test("apply should throw NoSuchElementException if key is not found") {
    val ht = new HashTable[String, Int]()
    ht.put("apple", 1)
    intercept[NoSuchElementException](ht.apply("banana"))
  }

  test("apply should throw NoSuchElementException if bucket is empty for the key") {
    val ht = new HashTable[Int, String](initialCapacity = 2)
    // Insert an element that will likely go into bucket 0 (e.g., key 2)
    ht.put(2, "two")
    // Now try to retrieve a key that will likely go into bucket 1 (e.g., key 1)
    intercept[NoSuchElementException](ht.apply(1))
  }

  test("put and get multiple elements") {
    val ht = new HashTable[String, Int]()
    ht.put("apple", 1)
    ht.put("banana", 2)
    ht.put("cherry", 3)
    assertEquals(ht.getSize, 3)
    assertEquals(ht.get("apple"), Some(1))
    assertEquals(ht.get("banana"), Some(2))
    assertEquals(ht.get("cherry"), Some(3))
    assertEquals(ht.get("date"), None)
  }
}
