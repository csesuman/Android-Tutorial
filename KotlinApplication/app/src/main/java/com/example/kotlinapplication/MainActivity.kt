package com.example.kotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a: Double = 6000.69;
        println("Hello world ==> " + a);

        var letter: Boolean = true;
//        letter = 'A';
        println("Letter is "+ "$letter");

        var rawString : String = "This is kotlin Array";
        println("Kotlin ==> $rawString");

        var numbers : IntArray = intArrayOf(1,2,3,4,5);

        println("Array is " + numbers[2] );


        var numberMut: MutableList<Int> = mutableListOf(1,2,3);

        println("My mutable list after ==> " + numberMut);

        var number: List<Int> = numberMut;

        println("My non mutable list after ==> " + number)

        var j : Int = 2;

        if(j in 1..4) {
            println(" WOW " + j);
        }

        for (k in 1..10)
            println(" ==> new Number = " + k);


        var x : Int = 3

        when(x) {

            1 -> println("x==1")
            2 -> println("x==2")
            3 -> println("x==3")
            4 -> println("x==4")

            else -> {
                print("x is Neighter 1 Nor 2")
            }
        }

        val items = listOf(1, 2, 3, 4)

        for(item in items) {
            println("Item is $item")
        }

        class myClass {
            private var name: String = "Tutorials ";

            fun printMe() {
                println("You are learning kotlin")
            }

            inner class Nested  {
                fun foo() = "Welcome to the Tutorialspoint!"
            }
        }

        val obj = myClass();
        println(obj.Nested().foo());



        var Programmar : Human = object:Human {
            override fun think() {
                println("This is overridden method");
            }
        }

        Programmar.think()


        class Person(val firstName: String, var age: Int) {

            val Message:String = "Heyy!!!!"
                constructor(name: String, age: Int, message: String):this(name,age) {

                }
        }

        val person = Person("Suman",50);


        println("Age is = ${person.age}")


        open class ABC {
            open fun thik() {
                println("I am from parent")
            }
        }

        class BCD: ABC() {
            override fun thik() {
                println("I am from child");
            }
        }

        var indata = ABC()

        indata.thik();

      class Alien {
          var skills: String = "null";

          fun printMySkill() {
              println(skills);
          }
      }

        fun Alien.addMySkills(a:Alien): String {
            var a4 = Alien();
            a4.skills = this.skills + " " + a.skills;
            return a4.skills;
        }

        var a1 = Alien();
        a1.skills = "JAVA --- ok";

        var a2 = Alien();
        a2.skills = "SQL --- ok"

        a1.addMySkills(a2)

        a1.printMySkill()


        val myExample : MyExample = MyExample.OP2()

        val output = when(myExample) {

            is MyExample.OP1 -> "Option Two Has been chosen";
            is MyExample.OP2 -> "Option One Has been chosen";
        }

        println( "===>>> " + output);

        val mylambda :(String)->Unit  = {s:String->print(s)}
        val v:String = "TutorialsPoint.com"
        mylambda(v)


        val s = Student("TutorialsPoint.com","Kotlin")
        val (name,subject) = s
        println("You are learning "+subject+" from "+name)
    }

    data class Student( val a :String,val b: String ){
        var name:String = a
        var subject:String = b
    }

    sealed class MyExample() {
        class OP1: MyExample()
        class OP2: MyExample()
    }


//    class multiInterface : Aa, Bb

    interface Aa {
        fun printMe() {
            println("This is Interace A")
        }
    }

    interface Bb {
        fun printMe() {
            println("This is Interace B")
        }
    }

    interface Human {
        fun think()
    }
}