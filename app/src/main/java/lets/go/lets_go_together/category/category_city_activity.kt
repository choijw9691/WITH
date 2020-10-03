package lets.go.lets_go_together.category


import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import lets.go.lets_go_together.R
import lets.go.lets_go_together.staticclass.Companion.gyeongbuk


import java.util.ArrayList
import java.util.LinkedHashMap

class category_city_activity : AppCompatActivity() {






    /**
     * The Expandable list view.
     */
   lateinit var expandableListView: ExpandableListView

    // We have two  main category. (third one is left for example addition)

    /**
     * The Parent Group Names.
     */
    internal var parent = arrayOf("경북", "경남") // comment this when uncomment bottom
    //String[] parent = new String[]{"MOVIES", "GAMES", "SERIALS"}; // example for 3 main category lists

    /*
    If above line is uncommented uncomment the following too:
    - serials array
    - serials genre list
    - Datastructure for Third level Serials.
    - secondLevel.add(serials);
    - serials category all data
    - data.add(thirdLevelSerials);

     */

    /**
     * The Movies Genre List.
     */


    // We have two  main category. (third one is left for example addition)
    internal var movies = arrayOf("구미시", "김천시", "안동시")
    /**
     * The Games Genre List.
     */
    internal var games = arrayOf("대구시", "마산시", "부산시", "통영")

    /**
     * The Serials Genre List.
     */
    // String[] serials = new String[]{"Crime", "Family", "Comedy"};


    /**
     * The Horror movie list.
     */
    // movies category has further genres
    internal var horror = arrayOf("송정동", "원평동", "형곡동")
    /**
     * The Action Movies List.
     */
    internal var action = arrayOf("김천1시", "김천2시", "김천3시", "김천4시")
    /**
     * The Thriller Movies List.
     */
    internal var thriller = arrayOf(
        "안동1시",
        "안동2시",
        "안동3시",
        "안동4시"
    )


    /**
     * The Fps games.
     */
    // games category has further genres
    internal var fps =
        arrayOf("대구1시", "대구2시", "대구3시", "대구4시", "대구5시", "대구6시")
    /**
     * The Moba games.
     */
    internal var moba =
        arrayOf("마산1시", "마산2시", "마산3시", "마산4시", "마산5시")
    /**
     * The Rpg games.
     */
    internal var rpg = arrayOf(
        "부산1시",
        "부산2시",
        "부산3시",
        "부산4시",
        "부산5시",
        "부산6시",
        "부산7시"
    )
    /**
     * The Racing games.
     */
    internal var racing =
        arrayOf("통영1시", "통영2시", "통영3시", "통영4시")

    // serials genre list
    /*String[] crime = new String[]{"CSI: MIAMI", "X-Files", "True Detective", "Sherlock (BBC)", "Fargo", "Person of Interest"};

    String[] family = new String[]{"Andy Griffith", "Full House", "The Fresh Prince of Bel-Air", "Modern Family", "Friends"};

    String[] comedy = new String[]{"Family Guy", "Simpsons", "The Big Bang Theory", "The Office"};
*/


    /**
     * Datastructure for Third level movies.
     */
    internal var thirdLevelMovies = LinkedHashMap<String, Array<String>>()
    /**
     * Datastructure for Third level games.
     */
    internal var thirdLevelGames = LinkedHashMap<String, Array<String>>()

    /**
     * Datastructure for Third level Serials.
     */
    // LinkedHashMap<String, String[]> thirdLevelSerials = new LinkedHashMap<>();


    /**
     * The Second level.
     */
    internal var secondLevel: MutableList<Array<String>> = ArrayList()


    /**
     * The Data.
     */
    internal var data: MutableList<LinkedHashMap<String, Array<String>>> = ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_city)
        // second level category names (genres)

       /* val study = intent.getStringExtra("공부")
        val toeic = intent.getStringExtra("토익")*/





        secondLevel.add(movies)
        secondLevel.add(games)
        // secondLevel.add(serials);

        // movies category all data
        thirdLevelMovies[movies[0]] = horror
        thirdLevelMovies[movies[1]] = action
        thirdLevelMovies[movies[2]] = thriller


        // games category all data
        thirdLevelGames[games[0]] = fps
        thirdLevelGames[games[1]] = moba
        thirdLevelGames[games[2]] = rpg
        thirdLevelGames[games[3]] = racing


        // serials category all data
        /*  thirdLevelSerials.put(serials[0], crime);
        thirdLevelSerials.put(serials[1], family);
        thirdLevelSerials.put(serials[2], comedy);
*/


        // all data
        data.add(thirdLevelMovies)
        data.add(thirdLevelGames)
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



       // OPTIONAL : Show one list at a time
/*
        expandableListView.setOnGroupExpandListener(object :
            ExpandableListView.OnGroupExpandListener {
            internal var previousGroup = -1

            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }

        }


        )
*/

expandableListView.setOnGroupExpandListener {
        groupPosition: Int->   Toast.makeText(applicationContext, parent[groupPosition] + "List Collapsed.", Toast.LENGTH_SHORT).show()





    val intent= Intent(this, lets.go.lets_go_together.category.expandable_third_adapter::class.java)
    gyeongbuk=parent[groupPosition]





   /* intent.putExtra("공부",study)
    intent.putExtra("토익",toeic)
    intent.putExtra("경북",parent[groupPosition])*/

/*   var sa=secondLevel[groupPosition]
    songjung = data[groupPosition].toString()

    Toast.makeText(applicationContext, gumi+songjung, Toast.LENGTH_SHORT).show()*/






/*    expandableListView.setOnChildClickListener{
        parent, v, groupPosition, childPosition, id ->

var dd=secondLevel[groupPosition][childPosition]

        Toast.makeText(applicationContext, dd, Toast.LENGTH_SHORT).show()
        return@setOnChildClickListener false
    }*/





}






   //     expandableListView!!.setOnGroupExpandListener { groupPosition -> Toast.makeText(applicationContext, (titleList as ArrayList<String>)[groupPosition] + " List Expanded.", Toast.LENGTH_SHORT).show() }





  /*      secondLevelELV.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(context, "Clicked: " , Toast.LENGTH_SHORT).show()


            return@setOnChildClickListener false

        }

*/



    }

}
