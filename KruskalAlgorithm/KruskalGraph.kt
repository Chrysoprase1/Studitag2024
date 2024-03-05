package KruskalAlgorithm

// Kruskal-Algorithmus, der einen minimalen Spannbaum aus einem einzutragendem KruskalGraph Objekt printet
// erst wird die liste edges (Konstruktor) sortiert. hierfür ist edges eine Variable
// dann wird eine leere Liste für die Kanten und Knoten angefertigt
    // dann werden alle Nodes ohne kanten in parent eingetragen
    // die sortierte Liste an Kanten (Konstruktor) wird abgelaufen und jeweils die entsprechende Kante eingetragen,
    // wenn die beiden Knoten, die die Kante verbindet, noch nicht abgespeichert ist
    // Algorithmus terminiert, wenn Knotenmenge-1 Kanten eingetragen wurden
        // dies entspricht dem Minimum an Kanten für einen Baum, da sonst nicht alle Kanten angebunden sind
        // es erfolgt eine Printausgabe des minimalen Spannbaumes

class KruskalGraph (private val nodes: Int, private var edges: MutableList<Edge>) {

    fun kruskalMinimumSpanningTree() {

        quickSort (this.edges, 0, this.edges.size - 1)

        val result = mutableListOf<Edge>()
        val parent = IntArray(nodes) { it }

        fun find (value : Int): Int {

            if (parent [value] != value) {
                parent[value] = find(parent[value])
            }
            return parent[value]
        }

        fun merge (firstValue : Int, secondValue: Int) {
            val containsFirst = find(firstValue)
            val containsSecond = find(secondValue)
            parent[containsFirst] = containsSecond
        }

        var iterateEdgesCounter = 0
        var includedEdgesCounter = 0

        while (includedEdgesCounter < nodes - 1 && iterateEdgesCounter < edges.size) {

            val nextEdge = edges [iterateEdgesCounter++]
            val parentSourceNode = find(nextEdge.source)
            val parentDestinationNode = find(nextEdge.destination)

            if (parentSourceNode != parentDestinationNode) {
                result.add(nextEdge)
                merge(parentSourceNode, parentDestinationNode)
                includedEdgesCounter++
            }
        }

        println("Krustalks minimaler Spannbaum: ")
        for (edge in result) {
            println("" + edge.source + "---" + edge.destination + "(weight: " + edge.weight + ")")
        }
    }

    private fun quickSort(edges: MutableList<Edge>, low: Int, high: Int) {
        if (low < high) {
            val pivot = partition (edges, low, high)
            quickSort (edges, low, pivot - 1)
            quickSort (edges, pivot + 1, high)
        }
    }

    private fun partition (edges: MutableList<Edge>, low: Int, high: Int): Int {
        val pivot = edges [high].weight
        var i = low - 1

        for (j in low until high) {
            if (edges [j].weight <= pivot) {
                i++
                val temp = edges [i]
                edges [i] = edges [j]
                edges [j] = temp
            }
        }

        val temp = edges [i + 1]
        edges [i + 1] = edges[high]
        edges [high] = temp

        return i + 1
    }
}