import java.util.*

fun main() {
    val digit = """\d+""".toRegex()
    fun parse(s:String):List<Any>{
        val stack = Stack<Any>()
        var cnt = 0
        while(cnt < s.length){
           if(s[cnt] == '['){
                stack.push('[')
           }else if (digit.matchesAt(s, cnt)){
               val v = digit.find(s, cnt)!!.value.toInt()
               stack.push(v)
               if (v > 9) cnt++
           }else if (s[cnt] == ']'){
               val l = mutableListOf<Any>()
               while (stack.peek() != '['){
                   l.add(stack.pop())
               }
               stack.pop()

               if (stack.isEmpty())
                   return l.reversed()

               stack.push(l.reversed())
           }
            cnt++
        }

        return emptyList()
    }


    fun isRightOrder(l1: List<Any>, l2: List<Any>):Boolean{
        val lower = kotlin.math.min(l1.size, l2.size)

        (0 until lower).forEach {
            if (l1[it] is List<*> && l2[it] is List<*>){
                if (!isRightOrder(l1[it] as List<Any>, l2[it] as List<Any>)) return false
            }else if (l1[it] is Int && l2[it] is Int){
                if (l1[it] as Int > l2[it] as Int) return false
                if ((l1[it] as Int) < l2[it] as Int) return true
            }else if(l1[it] is Int){
                if (!isRightOrder(listOf(l1[it] as Int), l2[it] as List<Any>)) return false
            }else{
                if (!isRightOrder(l1[it] as List<Any>,  listOf(l2[it] as Int))) return false
            }
        }

        return l1.size <= l2.size
    }


    fun part1(input: List<String>): Int {
        return input.chunked(3).mapIndexed { index, strings ->
            if (isRightOrder(parse(strings[0].trim()), parse(strings[1].trim()))) index + 1 else 0
        }.sum()
    }

    fun part2(input: List<String>): Int{
        return -1
    }


    var input = readInput("Test13")
    println(part1(input))
    println(part2(input))

    input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}
