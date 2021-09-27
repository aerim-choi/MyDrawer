package org.techtown.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 상단의 앱바레이아웃과 툴바를 이용하여 타이틀을 직접 만들었다
        //함수를 이용하여 상단의 타이틀쪽에 상단툴바를 설정할수있다.
        setSupportActionBar(toolbar);

        //네비게이션drawer만들기
        val toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //하단의 메뉴를 선택했을때 프레그먼트 이동
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    onFragmentSelected(0)
                }
                R.id.item2 -> {
                    onFragmentSelected(1)
                }
                R.id.item3 -> {
                    onFragmentSelected(2)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)//close할때 왼쪽부분으로 붙여
            return@setNavigationItemSelectedListener true;
        }
    }
    fun onFragmentSelected(index:Int){
        var fragment:Fragment = Fragment1()//초기

        when(index){
            0->{
                toolbar.title="첫번째 화면"
                fragment=Fragment1()
            }
            1->{
                toolbar.title="두번째 화면"
                fragment=Fragment2()
            }
            2->{
                toolbar.title="세번째 화면"
                fragment=Fragment3()
            }
        }
        with(supportFragmentManager.beginTransaction()){
            replace(R.id.container,fragment)
        }.commit()

    }

    override fun onBackPressed() {
        //오픈되어있다면 백키를 누르면 클로즈를 하여라
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        //아니면 그냥 뒤로가라
        else{
            super.onBackPressed()
        }
    }
}