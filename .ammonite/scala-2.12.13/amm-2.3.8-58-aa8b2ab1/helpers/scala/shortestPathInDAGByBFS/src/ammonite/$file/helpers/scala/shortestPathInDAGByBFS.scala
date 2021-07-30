
package ammonite
package $file.helpers.scala
import _root_.ammonite.interp.api.InterpBridge.{
  value => interp
}
import _root_.ammonite.interp.api.InterpBridge.value.{
  exit
}
import _root_.ammonite.interp.api.IvyConstructor.{
  ArtifactIdExt,
  GroupIdExt
}
import _root_.ammonite.compiler.CompilerExtensions.{
  CompilerInterpAPIExtensions,
  CompilerReplAPIExtensions
}
import _root_.ammonite.runtime.tools.{
  browse,
  grep,
  time,
  tail
}
import _root_.ammonite.compiler.tools.{
  desugar,
  source
}
import _root_.mainargs.{
  arg,
  main
}
import _root_.ammonite.repl.tools.Util.{
  PathRead
}


object shortestPathInDAGByBFS{
/*<script>*/import scala.collection.mutable.Queue
import scala.reflect.ClassTag

trait EdgeDistance[T] {
    def add(a:T,b:T):T
    def mzero:T
    def empty:T
}

  implicit val intDistance = new EdgeDistance[Int]{
    def add(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
    def empty:Int = -1
  }
  implicit val doubleDistance = new EdgeDistance[Double]{
    def add(a:Double,b:Double):Double = a + b
    def mzero :Double = 0.0
    def empty:Double = -1.0
  }


    
// 二次元配列で表現された無向グラフが与えられたとき、ノードi から ノードj への距離を求める
// graph は graph(i)(j) がノードiからノードjへのコストをあらわす二次元配列である
// エッジがある場合ノードからノードへのコストを正の数で、ない場合-1であらわしたものを与える
// 負のコストは探索しない
def shortestPathInDAGByBFS[T](
    from:Int/*start node index*/,
    graph:Array[Array[T]])
(implicit comparator: Ordering[T],
 edgeDistance: EdgeDistance[T],ct:ClassTag[T]):(Int,T,Array[T])
/*(furthest node index,the furthest distance,distances)*/
= {
    // queue of indexes to search
    var q = Queue[Int]()
    // interim furthest node index and distance
    var furthest:(Int,T) = (from,edgeDistance.mzero)
    // distances between from node and `k`th node. (k=0,1,...,graph.length-1)
    var dist: Array[T]= Array.fill(graph.length)(edgeDistance.empty)
    
    dist(from)=edgeDistance.mzero
    
    q.enqueue(from)
    while(!q.isEmpty){
        val pwd = q.dequeue
        val nexts = graph(pwd)
        nexts.zipWithIndex.foreach{ case (d,idx)=>
            // d can be Int,Double,Float,BigInt
          if(comparator.compare(d,edgeDistance.mzero) >0){ // pwdからの距離
              if(dist(idx) == -1){// from から idx番目のノードへの距離が未探索
                    dist(idx) = edgeDistance.add(dist(pwd),d)
                   if(comparator.compare(dist(idx),furthest._2)> 0){
                       furthest = (idx,dist(idx))
                   }
                    q.enqueue(idx)
              }
          }
        }
        
    }
    (furthest._1,furthest._2,dist)
}


/*</script>*/ /*<generated>*/
def $main() = { scala.Iterator[String]() }
  override def toString = "shortestPathInDAGByBFS"
  /*</generated>*/
}
