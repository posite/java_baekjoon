import java.io.BufferedReader
import java.io.InputStreamReader

val apart = Array(15) { IntArray(14) { 0 } }
fun main() {

    for (i in 0 until 14) {
        apart[0][i] = i + 1
    }
    for (i in 1 until 15) {
        for (j in 0 until 14) {
            apart[i][j] = getApart(i - 1, j, apart[i - 1][j])
        }
    }
    
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val t = bf.readLine().toInt()
    for (i in 0 until t) {
        val k = bf.readLine().toInt()
        val n = bf.readLine().toInt()
        println(apart[k][n - 1])
    }


}

fun getApart(floor: Int, number: Int, apartNumber: Int): Int {
    if (number <= 0) {
        return apartNumber
    }
    return getApart(floor, number - 1, apartNumber + apart[floor][number - 1])
}