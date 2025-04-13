package scalacs.graph.mutable

import scala.collection.mutable.Map as MMap

class BreadthFirstSearchSuite extends munit.FunSuite {
  /* format: off
   *
   *                1
   *        ┌───────┴───────┐
   *        ▼               ▼
   *       11               12
   *    ┌───┴───┐       ┌───┴───┐
   *    ▼       ▼       ▼       ▼
   *   21       22     23       24
   *  ┌─┴─┐   ┌─┴─┐   ┌─┴─┐   ┌─┴─┐
   *  ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼
   * 31   32 33   34 35   36 37   38
   * 
   * format: on
   */
  val graph = MMap(
    1  -> Iterable(11, 12),
    11 -> Iterable(21, 22),
    12 -> Iterable(23, 24),
    21 -> Iterable(31, 32),
    22 -> Iterable(33, 34),
    23 -> Iterable(35, 36),
    24 -> Iterable(37, 38)
  )

  val emptyGraph = MMap[Int, Iterable[Int]]()

  /* format: off
   * 
   *                1
   *        ┌───────┴───────┐
   *        ▼               ▼
   *       11               12
   *    ┌───┴───┐       ┌───┴───┐
   *    ▼       ▼       ▼       ▼
   *   21──────►22     23──────►24
   * 
   * format: on
   */
  val graphWithCycle = MMap(
    1  -> Iterable(11, 12),
    11 -> Iterable(21, 22),
    21 -> Iterable(22),
    22 -> Iterable(11),
    12 -> Iterable(23, 24),
    23 -> Iterable(24),
    24 -> Iterable(12)
  )

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
    val expected = Some(List(1, 12, 24))
    val obtained = BreadthFirstSearch(graphWithCycle, 1, n => n == 24)
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
