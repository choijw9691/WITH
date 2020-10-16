package lets.go.lets_go_together

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.annotation.Nullable
import lets.go.lets_go_together.recycler.recycler_view
import lets.go.lets_go_together.staticclass.Companion.main_recyclerview
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import kotlinx.android.synthetic.main.activity_main.*

import java.security.MessageDigest
import java.util.ArrayList
import android.widget.Toast
import jxl.read.biff.BiffException
import jxl.Sheet
import jxl.Workbook

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.IOException

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity :AppCompatActivity() {
    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String>? = null

    val listData = LinkedHashMap<String, List<String>>()
    val micromaxMobiles = ArrayList<String>()
    val redmiMobiles = ArrayList<String>()
    val appleMobiles = ArrayList<String>()
    val samsungMobiles = ArrayList<String>()
    val counseling = ArrayList<String>()

    private var mContext: Context? = null


    val data: LinkedHashMap<String, List<String>>
    get() {

        micromaxMobiles.add("토익")
        micromaxMobiles.add("토플")
        micromaxMobiles.add("자격증")
        micromaxMobiles.add("중국어")
        micromaxMobiles.add("일어")
        micromaxMobiles.add("기타")

        redmiMobiles.add("헬스")
        redmiMobiles.add("축구")
        redmiMobiles.add("농구")
        redmiMobiles.add("테니스")
        redmiMobiles.add("탁구")

        redmiMobiles.add("기타")

        appleMobiles.add("롤")
        appleMobiles.add("배그")
        appleMobiles.add("서든")
        appleMobiles.add("피파")
        appleMobiles.add("스타")
        appleMobiles.add("카트")
        appleMobiles.add("기타")

        samsungMobiles.add("기타1")



        counseling.add("고민상담")

        listData["게임"] = appleMobiles
        listData["공부"] = micromaxMobiles
        listData["운동"] = redmiMobiles
        listData["고민상담"]=counseling
        listData["기타"] = samsungMobiles
        return listData
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        // 1. 툴바 사용 설정
            setSupportActionBar(toolbar_main)

            // 2. 툴바 왼쪽 버튼 설정
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)  // 왼쪽 버튼 사용 여부 true
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.toolbaricon)  // 왼쪽 버튼 아이콘 설정
            supportActionBar!!.setDisplayShowTitleEnabled(false)

            toolbar_title_main.setOnClickListener {

                val intent = Intent(this, lets.go.lets_go_together.MainActivity::class.java)
                startActivity(intent)

            }




        mContext = getApplicationContext();
        getHashKey(mContext);







        logout.setOnClickListener{


            UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                override fun onCompleteLogout() {
                    val intent = Intent(this@MainActivity, lets.go.lets_go_together.login.login_activity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            })

        }



        val loginID: TextView=findViewById(R.id.loginID)
      var expandableList:ExpandableListView = findViewById(R.id.expendableList_field)




        val pref1 = lets.go.lets_go_together.loginsession(this)
        val nick = pref1.getValue("loginid", "qwertasdfzxc##!4356kfdefefe")

        loginID.setText(nick)


        main_nickname.setOnClickListener{

            main_recyclerview=false
            val intent = Intent(this, lets.go.lets_go_together.profile::class.java)
            startActivity(intent)

        }


        main_comment_num.setOnClickListener{


            val intent = Intent(this, my_comment_list::class.java)
            startActivity(intent)
        }



        var main_write: Button = findViewById(R.id.main_write)

        main_write.setOnClickListener {

            main_recyclerview=false
            val intent = Intent(this, lets.go.lets_go_together.boardwrite::class.java)
            startActivity(intent)

        }



        expandableListView = findViewById(R.id.expendableList_field)

        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = lets.go.lets_go_together.category.category_second_adapter(
                this,
                titleList as ArrayList<String>,
                listData
            )
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(
                    applicationContext,
                    "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()




                /*          intent.putExtra("공부",  (titleList as ArrayList<String>)[groupPosition])
                          intent.putExtra("토익", listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition))
          */
                var study = (titleList as ArrayList<String>)[groupPosition]
               var toeic =
                    listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(childPosition)


                val intent = Intent(this, recycler_view::class.java)
                intent.putExtra("study_main",study)
                intent.putExtra("toeic_main",toeic)
                main_recyclerview=true
                startActivity(intent)


                finish()

                return@setOnChildClickListener false


            }
        }




    }





    // 프로젝트의 해시키를 반환

    @Nullable
    fun getHashKey(context: Context?): String? {

        val TAG = "KeyHash"

        var keyHash: String? = null

        try {

            val info =

                context!!.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNATURES
                )



            for (signature in info.signatures) {

                val md: MessageDigest

                md = MessageDigest.getInstance("SHA")

                md.update(signature.toByteArray())

                keyHash = String(Base64.encode(md.digest(), 0))

                Log.d(TAG, keyHash)

            }

        } catch (e: Exception) {

            Log.e("name not found", e.toString())

        }



        return keyHash

    }



}
