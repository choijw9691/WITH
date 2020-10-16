package lets.go.lets_go_together.category


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jxl.Workbook
import jxl.read.biff.BiffException

import lets.go.lets_go_together.R
import lets.go.lets_go_together.staticclass.Companion.gyeongbuk
import java.io.IOException


import java.util.ArrayList
import java.util.LinkedHashMap

class category_city_activity : AppCompatActivity() {


    lateinit var expandableListView: ExpandableListView


    var arrayList1 = ArrayList<String>()
    var arrayList2 = ArrayList<String>()
    var arrayList22 = ArrayList<String>()
    var arrayList3 = ArrayList<String>()
    var arrayList33 = ArrayList<String>()
    var parent = ArrayList<String>()
    var movies = ArrayList<String>()
    var moviess = ArrayList<String>()
    var horror = ArrayList<String>()
    var horrorr = ArrayList<String>()


    internal var thirdLevelMovies = LinkedHashMap<String, ArrayList<String>>()
    var thirdLevelMovies1 = ArrayList<LinkedHashMap<String, ArrayList<String>>>()
    //internal var thirdLevelGames = LinkedHashMap<String, Array<String>>()


    internal var secondLevel: MutableList<ArrayList<String>> = ArrayList()


    internal var data: MutableList<LinkedHashMap<String, ArrayList<String>>> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_city)



        for (i in 0..249){





        }





        val TAG = "TEST"
        try {
            Log.d(TAG, "START")
            val `is` = baseContext.resources.assets.open("cityname.xls")
            val wb = Workbook.getWorkbook(`is`)

            if (wb != null) {
                var aa = 0;
                val sheet = wb.getSheet(0)   // 시트 불러오기
                if (sheet != null) {
                    val colTotal = sheet.columns    // 전체 컬럼
                    val rowIndexStart = 0                  // row 인덱스 시작
                    val rowTotal = sheet.getColumn(colTotal - 1).size
                    var first = 1
                    var ii = 1
                    var uu=0
                    var iii=1
                    var second = 1
                    var third = 0
                    var a1 = 0
                    var yy=250
                    for (row in rowIndexStart until rowTotal) {

                        for (col in 0 until colTotal) {
                            val contents = sheet.getCell(col, row).contents

                            if (col == 0) {

                                if (arrayList1.size == 0) {
                                    arrayList1.add(contents)

                                } else if (arrayList1[arrayList1.size - 1] != contents) {

                                    arrayList1.add(contents)

                                }

                            }

                            if (col == 1) {


                                if (arrayList2.size == 0) {
                                    arrayList2.add(contents)
                                    arrayList22.add(contents)
                                    horror.add(contents)
                                    a1++

                                } else if (arrayList2[arrayList2.size - 1] != contents) {
                                    arrayList22.add(contents)
                                    arrayList2.add(contents)
                                    horror.add(contents)
                                    a1++

                                }


                            }


                            if (col == 2) {

                                arrayList3.add(contents)


                            }


                        }

                        if (arrayList1.size > first) {

                            third++
                            for (q in 0..arrayList22.size - 2) {

                                moviess.add(arrayList22[q])

                            }

                            var a = arrayList22[arrayList22.size - 1]
                            secondLevel.add(moviess)

                            first++
                            arrayList22 = ArrayList<String>()

                            moviess = ArrayList<String>()
                            moviess.add(a)

                            // data.add(thirdLevelMovies)


                        }

                        if (row == 3501) {
                            third++
                            for (q in 0..arrayList22.size - 1) {

                                moviess.add(arrayList22[q])

                            }


                            secondLevel.add(moviess)



                        }


                        if (a1 >ii) {
                            var gg=a1-2



                             if(third==0){

                             var b=arrayList3[arrayList3.size-1]
                               arrayList3.removeAt(arrayList3.size-1)

                                 Log.d(TAG, ii.toString())
                             thirdLevelMovies[horror[a1 - 2]] = arrayList3

                             arrayList3 = ArrayList<String>()



                                 arrayList3.add(b)

                         }

else if(third>0){

                             var b=arrayList3[arrayList3.size-1]
                             arrayList3.removeAt(arrayList3.size-1)

                             Log.d(TAG, ii.toString())
                             thirdLevelMovies[horror[a1 - 2]] = arrayList3
                             data.add(thirdLevelMovies)

                             arrayList3 = ArrayList<String>()
                             third=0
                         //    data.add(thirdLevelMovies400xz

                             thirdLevelMovies = LinkedHashMap<String, ArrayList<String>>()
                             arrayList3.add(b)
                         }
                            ii++
                        }

if(a1>=yy){





    thirdLevelMovies[horror[a1 - 1]] = arrayList3
    data.add(thirdLevelMovies)



    Log.d(TAG, ii.toString()+"jjjjjjjjjjjj")



}





                    }


                    for (i in 0..arrayList1.size - 1) {
                        parent.add(arrayList1[i])
                    }



/*
for(i in 0..thirdLevelMovies1.size-1){
    data.add(thirdLevelMovies1[i])

}*/
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: BiffException) {
            e.printStackTrace()
        }


        // secondLevel.add(games)

        // thirdLevelMovies[movies[0]] = horror
/*
        thirdLevelMovies[movies[1]] = action
        thirdLevelMovies[movies[2]] = thriller
*/



/*
        for (i in 0..thirdLevelMovies1.size-1){
            data.add(thirdLevelMovies1[i])

        }*/

        //data.add(thirdLevelSerials);


        // expandable listview
        expandableListView = findViewById(R.id.expendableList) as ExpandableListView

        // parent adapter
        val threeLevelListAdapterAdapter =
            lets.go.lets_go_together.category.expandable_third_adapter(
                this,
                parent,
                secondLevel,
                data
            )


        // set adapter

        expandableListView.setAdapter(threeLevelListAdapterAdapter)




        expandableListView.setOnGroupExpandListener { groupPosition: Int ->
            Toast.makeText(
                applicationContext,
                parent[groupPosition] + "List Collapsed.",
                Toast.LENGTH_SHORT
            ).show()


            val intent =
                Intent(this, lets.go.lets_go_together.category.expandable_third_adapter::class.java)
            gyeongbuk = parent[groupPosition]


        }


    }

}
