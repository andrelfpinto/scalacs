package scalacs.graph.mutable

class BreadthFirstSearchSuite extends munit.FunSuite with GraphDefinitions {
  test("BFS should find a path to a reachable goal node") {
    val expected = Some(List(1, 11, 21, 31))
    val obtained = BreadthFirstSearch(graph, 1, n => n == 31)
    assertEquals(obtained, expected)
  }

  test("BFS should find a path to a different reachable goal node") {
    val expected = Some(List(1, 12, 24, 38))
    val obtained = BreadthFirstSearch(graph, 1, n => n == 38)
    assertEquals(obtained, expected)
  }

  test("BFS should find a path if the goal node is the root node") {
    val expected = Some(List(1))
    val obtained = BreadthFirstSearch(graph, 1, n => n == 1)
    assertEquals(obtained, expected)
  }

  test(
    "BFS should not find a path if the goal node is unreachable from the root node"
  ) {
    val expected = None
    val obtained = BreadthFirstSearch(graph, 11, n => n == 38)
    assertEquals(obtained, expected)
  }

  test("BFS should not find a path if the goal node is not in the graph") {
    val expected = None
    val obtained = BreadthFirstSearch(graph, 1, n => n == 99)
    assertEquals(obtained, expected)
  }

  test("BFS should not find a path if the root node is not in the graph") {
    val expected = None
    val obtained = BreadthFirstSearch(graph, 99, n => n == 1)
    assertEquals(obtained, expected)
  }

  test(
    "BFS should not find a path if the root node is not in the graph and is the goal node"
  ) {
    val expected = None
    val obtained = BreadthFirstSearch(graph, 99, n => n == 99)
    assertEquals(obtained, expected)
  }

  test("BFS should not find a path if the graph is empty") {
    val expected = None
    val obtained = BreadthFirstSearch(emptyGraph, 1, n => n == 1)
    assertEquals(obtained, expected)
  }

  test("BFS should find a path to a reachable node in a graph with cycles") {
    val expected = Some(List(1, 12, 23))
    val obtained = BreadthFirstSearch(graphWithCycle, 1, n => n == 23)
    assertEquals(obtained, expected)
  }

  test(
    "BFS should not find a path if the goal node is unreachable from the root node in a graph with cycles"
  ) {
    val expected = None
    val obtained = BreadthFirstSearch(graphWithCycle, 1, n => n == 99)
    assertEquals(obtained, expected)
  }

  test("BFS should explore level by level") {
    val visitedOrder = scala.collection.mutable.ListBuffer[Int]()
    def recordingGoal(n: Int): Boolean = {
      visitedOrder += n
      n == 31
    }
    BreadthFirstSearch(graph, 1, recordingGoal)
    val expected = List(1, 11, 12, 21, 22, 23, 24, 31)
    val obtained = visitedOrder.toList
    assertEquals(obtained, expected)
  }
}
