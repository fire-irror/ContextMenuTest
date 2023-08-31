package kr.hs.emirim.contextmenutest

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var linear : LinearLayout
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "배경색 바꾸기(컨텍스트 메뉴)"
        linear = findViewById(R.id.linear)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn3.setOnClickListener{
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("안내문")
            dlg.setMessage("슈퍼블루문은 17년 뒤에 다시 나옵니다")
            dlg.setIcon(R.drawable.img)
            dlg.setPositiveButton("확인",null)
            dlg.setNegativeButton("취소",null)

            dlg.setPositiveButton("확인") { dialog, which ->
                linear.setBackgroundColor(Color.MAGENTA)
            }
            dlg.setNegativeButton("취소") {dialogInterface, i ->
                Toast.makeText(this@MainActivity, "취소 버튼이 클릭되었습니다",Toast.LENGTH_LONG).show()
            }
            dlg.show()
        }


        registerForContextMenu(btn1)
        registerForContextMenu(btn2)

        btn1.setOnClickListener{
            var toast = Toast.makeText(applicationContext, "컨텍스트 메뉴는 길게 눌러야 나타납니다", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var mInflater = this.menuInflater
        if(v===btn1){
            menu!!.setHeaderTitle("배경색 변경")
            mInflater.inflate(R.menu.menu,menu)
        }
        if(v === btn2){
            mInflater.inflate(R.menu.menu2,menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        super.onContextItemSelected(item)

        when(item.itemId){
            R.id.itemRed -> {
                linear.setBackgroundColor(Color.RED)
                return true
            }
            R.id.itemBlue -> {
                linear.setBackgroundColor(Color.BLUE)
                return true
            }
            R.id.itemYellow -> {
                linear.setBackgroundColor(Color.YELLOW)
                return true
            }
            R.id.subRotate -> {
                btn1.rotation = 180f
                return true
            }

            R.id.subSize -> {
                btn1.scaleX = 2.0f
                return true
            }
            R.id.subOriginal ->{
                btn1.rotation = 0f
                btn1.scaleX = 1f
                return true
            }

        }
        return false
    }
}