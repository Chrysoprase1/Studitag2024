package FractionalKnapsackAlgorithm

// Algorithmus zur Lösung eines Bruchteil-Rucksack Problemes
// Rucksack besteht aus Inhaltsliste und Kapazität
// Methode fractionalKnapsack nimmt eine Hashmap an Items + Menge
    // Methode gibt eine Map mit der optimalen Wert-Gewicht-Bilanz an Items aus
// funktional, aber noch overengineered
// TODO: Redundanz entfernen l23 - l65

data class Knapsack (var contents : HashMap<Item, Double>, val capacity : Double) {

    fun fractionalKnapsack () : HashMap<Item, Double> {

        var currentCapacity = 0.0
        var worthList = ArrayList<Item>()
        var resultMap = HashMap<Item, Double>()

        // gibt Items-Arraylist sortiert zurück
        for (values in this.contents) {
            worthList.add (Item (values.key.weight, values.key.averageValue()))
        }
        quickSort(worthList, 0, worthList.size - 1)

        // addiere wertigstes Item, bis die Kapazität erreicht ist voll oder teilweise
        // subtrahiere jeweils 1 oder Teil des Items von der Verfügbarkeitsmap
        // entferne bei Verfügbarkeit == 0 das item
        // loop, so lange bis Kapazität erreicht oder die Liste leer ist
        while (worthList.isNotEmpty() && currentCapacity < this.capacity) {

            if (resultMap.contains (worthList.get (worthList.size - 1))) {
                var storeQuantity : Double = 0.0
                var fraction : Double = 1.0

                if (currentCapacity + worthList.get (worthList.size - 1).weight <= this.capacity) {
                    storeQuantity = this.contents!!.get(worthList.get (worthList.size - 1))!! - 1.0
                } else {
                    fraction = (this.capacity - currentCapacity) / (currentCapacity + worthList.get (worthList.size - 1).weight)
                    storeQuantity =this.contents!!.get(worthList.get (worthList.size - 1))!!  - fraction
                }

                resultMap.put (worthList.get (worthList.size - 1), fraction)
                this.contents.put (worthList.get (worthList.size - 1), storeQuantity)
                currentCapacity = currentCapacity + (worthList.get (worthList.size - 1).weight * fraction)

                if (storeQuantity <= 0) {
                    worthList.remove (worthList.get (worthList.size - 1))
                }
            }

            if (!(resultMap.contains (worthList.get (worthList.size - 1)))) {
                var storeQuantity : Double = 0.0
                var fraction : Double = 1.0

                if (currentCapacity + worthList.get (worthList.size - 1).weight <= this.capacity) {
                    storeQuantity = this.contents!!.get(worthList.get (worthList.size - 1))!! - fraction
                } else {
                    fraction = (this.capacity - currentCapacity) / (currentCapacity + worthList.get (worthList.size - 1).weight)
                    storeQuantity =this.contents!!.get(worthList.get (worthList.size - 1))!! - fraction
                }

                resultMap.put (worthList.get (worthList.size - 1), fraction)
                this.contents.put (worthList.get (worthList.size - 1), storeQuantity)
                currentCapacity = currentCapacity + (worthList.get (worthList.size - 1).weight * fraction)

                if (storeQuantity <= 0) {
                    worthList.remove (worthList.get (worthList.size - 1))
                }
            }
        }
        return resultMap
    }
}

// sortierhelper
fun quickSort(input: ArrayList<Item>, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(input, low, high)
        quickSort(input, low, pivotIndex - 1)
        quickSort (input, pivotIndex + 1, high)
    }
}

fun partition(input: ArrayList<Item>, low: Int, high: Int): Int {
    val pivot = input [high]
    var i = low - 1
    for (j in low until high) {
        if (input[j].value < pivot.value) {
            i++
            swap(input, i, j)
        }
    }
    swap(input, i + 1, high)
    return i + 1
}

fun swap(input: ArrayList<Item>, i: Int, j: Int) {
    val temp = input[i]
    input[i] = input[j]
    input[j] = temp
}