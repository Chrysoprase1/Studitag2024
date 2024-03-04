package FractionalKnapsackAlgorithm

class Item (val weight : Double, val value : Double) {


    fun averageValue () : Double {
        return this.value / this.weight
    }

}

