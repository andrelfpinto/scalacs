package scalacs.graph.mutable

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map as MMap
import scala.collection.mutable.Set
import scalacs.data.mutable.Queue

object BreadthFirstSearch {

  /** Performs a breadth-first search (BFS) on a graph starting from a given root node. It searches
    * for a path to a node that satisfies the provided goal condition.
    *
    * @tparam N
    *   The type of the nodes in the graph.
    * @param graph
    *   A map representing the graph, where keys are nodes and values are their neighbors.
    * @param root
    *   The starting node for the BFS.
    * @param goal
    *   A function that takes a node and returns true if it is the goal node, false otherwise.
    * @return
    *   An Option containing a List of nodes representing the shortest path from the root to the
    *   first encountered goal node. Returns None if no path to a goal node is found.
    */
  def apply[N](
      graph: MMap[N, Iterable[N]],
      root: N,
      goal: N => Boolean
  ): Option[List[N]] = {
    if (graph.isEmpty || !graph.contains(root))
      return None

    val queue    = Queue[N]()
    val explored = Set[N]()
    val parent   = MMap[N, N]()

    explored.add(root)
    queue.enqueue(root)

    while (!queue.isEmpty) {
      val current = queue.dequeue()

      if (goal(current))
        return Some(reconstructPath(current, parent))

      graph.get(current).foreach { neighbors =>
        neighbors.foreach { neighbor =>
          if (!explored.contains(neighbor)) {
            explored.add(neighbor)
            parent(neighbor) = current
            queue.enqueue(neighbor)
          }
        }
      }
    }
    None // Goal not found
  }

  /** Reconstructs the path from a goal node back to the root node using the parent map generated
    * during the breadth-first search.
    *
    * @tparam N
    *   The type of the nodes in the graph.
    * @param goal
    *   The goal node from which to reconstruct the path.
    * @param parent
    *   A map where keys are nodes and values are their parent nodes in the BFS traversal.
    * @return
    *   A List of nodes representing the path from the root to the goal node (inclusive). The list
    *   is ordered from the root to the goal.
    */
  private def reconstructPath[N](goal: N, parent: MMap[N, N]): List[N] = {
    val path    = ListBuffer[N](goal)
    var current = goal
    while (parent.contains(current)) {
      current = parent(current)
      path.prepend(current)
    }
    path.toList
  }
}
