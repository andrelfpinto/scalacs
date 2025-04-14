package scalacs.graph.mutable

import scala.collection.mutable.Map as MMap

trait GraphDefinitions:
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
   *   21──────►22     23◄──────24
   * 
   * format: on
   */
  val graphWithCycle = MMap(
    1  -> Iterable(11, 12),
    11 -> Iterable(21, 22),
    12 -> Iterable(23, 24),
    21 -> Iterable(22),
    24 -> Iterable(23)
  )
