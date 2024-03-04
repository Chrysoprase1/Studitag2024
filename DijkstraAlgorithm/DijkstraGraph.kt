package DijkstraAlgorithm

import java.util.*

// Dijsktra-Algorithmus zur Greedybestimmung der kürzesten Wege in einem Graphen
// Knoten und Kanten können vor Ausführung eingefügt werden
// code ist funktional
// nicht funktional bei negativen Kanten

data class DijkstraGraph(
    var adjacencyMap: MutableMap<Node, MutableSet<Node>> = mutableMapOf(),
    val weights: MutableMap<Pair<Node, Node>, Double> = mutableMapOf()) {


    fun addNode(node: Node) {
        if (adjacencyMap[node] == null) {
            adjacencyMap[node] = mutableSetOf<Node>()
        }
    }

    fun addEdge(fromNode: Node, toNode: Node, weight: Double) {
        addNode(fromNode)
        addNode(toNode)
        adjacencyMap[fromNode]!!.add(toNode)
        weights[fromNode to toNode] = weight
    }

    fun dijkstraShortestPath(source: Node): Pair<Map<Node, Double>, Map<Node, Node?>> {
        val distanceMap = mutableMapOf<Node, Double>()
        val predecessorMap = mutableMapOf<Node, Node?>()
        val queue = PriorityQueue<Pair<Node, Double>>(compareBy { it.second })

        for (node in adjacencyMap.keys) {
            distanceMap[node] = Double.POSITIVE_INFINITY
            predecessorMap[node] = null
        }

        distanceMap[source] = 0.0
        queue.offer(source to 0.0)

        while (queue.isNotEmpty()) {
            val (u, distU) = queue.poll() ?: break

            for (v in adjacencyMap[u] ?: continue) {
                val alt = distU + weights[u to v]!!
                if (alt < distanceMap[v]!!) {
                    distanceMap[v] = alt
                    predecessorMap[v] = u
                    queue.offer(v to alt)
                }
            }
        }

        return distanceMap to predecessorMap
    }

}