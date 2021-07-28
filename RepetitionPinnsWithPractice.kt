package by.issoft.introduction.kotlin

import kotlin.random.Random as KRand

fun foo():Nothing? = null

fun <T> printResult(function: () -> T,charSeq:String="\n\n") {
    print("$charSeq ${function()} $charSeq")

}

fun <T> decorateWith(vararg decorators: Decorator<T>, function: () -> T): T {
    return decorators.fold(initial = function()) { result, decorator -> decorator { result } }
}

typealias Decorator<T> = (() -> T) -> T

fun outl(smth:Any)= decorateWith(::printResult){
    smth
}

fun main() {

    outl("kjb")



    val p:Byte= 0b110011
    val a= arrayOf<Any>(5,"oflki",9U,21F,528e32,239_3e35,0xffac,'k',48f,978.88,(0b0111110).toByte())
    a.forEach { when(it){
        is Int-> println("Int")
        is Float->println("Float")
        is Byte-> println("Byte")
        else-> println("\t${it::class.simpleName!!}")
    } }

    var map= mapOf(2 to 4, 2 to 89,2 to 852689, 3 to 0x2, 2 to "poll", "8" to 'c' to 3 to 0b1001, 4 to 4 to 5, 2 to "pop")//as Conclusion I understood that in Kotlin all previous values for the same keys f.ex. key==2 
                                                                                                                          //are replaced

    println("\nMap element: ${map[2]}")

    val lambdaFun={key:Any?,value:Any?->print("${key!!} : ${value!!} ")}

    map.forEach(lambdaFun)

    val x:Any=789

    println(x::class.simpleName)

    val y:Any=4
    println(y::class.simpleName)

    val intRange=5..9
    println(intRange::class.simpleName)//IntRange





    var emptyArr= emptyArray<Any>()
    emptyArr+="Hello"
    emptyArr+="World"
    emptyArr+=987U
    emptyArr+=4e10
    emptyArr+=2578.toByte()
    emptyArr+=987-58+567.76

    var typearr= emptyArray<String?>()
    emptyArr.forEach {
        typearr+=it::class.simpleName
    }
    emptyArr.forEach {
        println(it)
    }
    for(i in typearr){
        print(i,' ')
    }
    println("\n${typearr.size} ${typearr.size==emptyArr.size}")

    println(null)

    Data("firstData", emptyArr).represent()

    val range=-25+-1..64 - -6 step 5*2-6//estimated range is -26..67 step 4
    println()
    println("Range: $range")

}

fun print(i: String?, c: Any) {
    print(i+c)
}

fun Array<Any>.toStringOther() = this.forEach {
    print("$it : ${it::class.simpleName}, ")
}

data class Data(val name:String, var arr:Array<Any>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (name != other.name) return false
        if (!arr.contentEquals(other.arr)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = KRand.nextInt() * result + arr.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "\b\b\nData(name='$name', arr=${arr.toStringOther()})"
    }


}

fun Data.represent()=print(this)