package scalacs.graph.mutable

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map as MMap
import scala.collection.mutable.Set
import scalacs.data.mutable.Stack

object DepthFirstSearch {

  /** Performs a depth-first search (DFS) on a graph starting from a given root node. It searches
    * for a path to a node that satisfies the provided goal condition.
    *
    * @tparam N
    *   The type of the nodes in the graph.
    * @param graph
    *   A map representing the graph, where keys are nodes and values are their neighbors.
    * @param root
    *   The starting node for the DFS.
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

    val stack    = Stack[N]()
    val explored = Set[N]()
    val parent   = MMap[N, N]()

    stack.push(root)

    while (!stack.isEmpty) {
      val current = stack.pop()

      if (goal(current))
        return Some(reconstructPath(current, parent))

      if (!explored.contains(current)) {
        explored.add(current)

        graph.get(current).foreach { neighbors =>
          neighbors.foreach { neighbor =>
            parent(neighbor) = current
            stack.push(neighbor)
          }
        }
      }
    }
    None // Goal not found
  }

  /** Reconstructs the path from a goal node back to the root node using the parent map generated
    * during the depth-first search.
    *
    * @tparam N
    *   The type of the nodes in the graph.
    * @param goal
    *   The goal node from which to reconstruct the path.
    * @param parent
    *   A map where keys are nodes and values are their parent nodes in the DFS traversal.
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
