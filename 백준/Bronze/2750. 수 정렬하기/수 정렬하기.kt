import java.io.BufferedReader
import java.io.InputStreamReader


fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val n = bf.readLine().toInt()
    val numbers = mutableListOf<Int>()
    repeat(n) {
        numbers.add(bf.readLine().toInt())
    }
    quickSort(numbers)

    numbers.forEach {
        println(it)
    }
}

fun quickSort(arr: MutableList<Int>, left: Int = 0, right: Int = arr.size - 1) {
    if (left >= right) return

    val pivot = partition(arr, left, right)

    quickSort(arr, left, pivot - 1)
    quickSort(arr, pivot + 1, right)
}

fun partition(arr: MutableList<Int>, left: Int, right: Int): Int {
    var low = left + 1
    var high = right
    val pivot = arr[left]

    while (low <= high) {
        while (arr[low] <= pivot && low < high) low++
        while (arr[high] > pivot && low <= high) high--

        if (low < high)
            swap(arr, low, high)
        else
            break
    }

    swap(arr, left, high)

    return high
}

fun swap(arr: MutableList<Int>, i: Int, j: Int) {
    var temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}