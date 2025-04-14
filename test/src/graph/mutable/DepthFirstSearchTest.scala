package scalacs.graph.mutable

class DepthFirstSearchSuite extends munit.FunSuite with GraphDefinitions {
  test("DFS should find a path to a reachable goal node") {
    val expected = Some(List(1, 11, 21, 31))
    val obtained = DepthFirstSearch(graph, 1, n => n == 31)
    assertEquals(obtained, expected)
  }

  test("DFS should find a path to a different reachable goal node") {
    val expected = Some(List(1, 12, 24, 38))
    val obtained = DepthFirstSearch(graph, 1, n => n == 38)
    assertEquals(obtained, expected)
  }

  test("DFS should find a path if the goal node is the root node") {
    val expected = Some(List(1))
    val obtained = DepthFirstSearch(graph, 1, n => n == 1)
    assertEquals(obtained, expected)
  }

  test(
    "DFS should not find a path if the goal node is unreachable from the root node"
  ) {
    val expected = None
    val obtained = DepthFirstSearch(graph, 11, n => n == 38)
    assertEquals(obtained, expected)
  }

  test("DFS should not find a path if the goal node is not in the graph") {
    val expected = None
    val obtained = DepthFirstSearch(graph, 1, n => n == 99)
    assertEquals(obtained, expected)
  }

  test("DFS should not find a path if the root node is not in the graph") {
    val expected = None
    val obtained = DepthFirstSearch(graph, 99, n => n == 1)
    assertEquals(obtained, expected)
  }

  test(
    "DFS should not find a path if the root node is not in the graph and is the goal node"
  ) {
    val expected = None
    val obtained = DepthFirstSearch(graph, 99, n => n == 99)
    assertEquals(obtained, expected)
  }

  test("DFS should not find a path if the graph is empty") {
    val expected = None
    val obtained = DepthFirstSearch(emptyGraph, 1, n => n == 1)
    assertEquals(obtained, expected)
  }

  test("DFS should find a path to a reachable node in a graph with cycles") {
    val expected = Some(List(1, 12, 24, 23))
    val obtained = DepthFirstSearch(graphWithCycle, 1, n => n == 23)
    assertEquals(obtained, expected)
  }

  test(
    "DFS should not find a path if the goal node is unreachable from the root node in a graph with cycles"
  ) {
    val expected = None
    val obtained = DepthFirstSearch(graphWithCycle, 1, n => n == 99)
    assertEquals(obtained, expected)
  }

  test("DFS should explore as far as possible along each branch") {
    val visitedOrder = scala.collection.mutable.ListBuffer[Int]()
    def recordingGoal(n: Int): Boolean = {
      visitedOrder += n
      n == 31
    }
    DepthFirstSearch(graph, 1, recordingGoal)
    val expected = List(1, 12, 24, 38, 37, 23, 36, 35, 11, 22, 34, 33, 21, 32, 31)
    val obtained = visitedOrder.toList
    assertEquals(obtained, expected)
  }
}
