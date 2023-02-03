package com.almufc.manualusuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.almufc.manualusuario.databinding.ActivityMainBinding
import com.almufc.manualusuario.fragments.Page1
import com.almufc.manualusuario.fragments.Page2
import com.almufc.manualusuario.fragments.Page3
import com.almufc.manualusuario.fragments.ScrollingPage4
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottomNavView =binding.bottomNavigation

        val mapFragment = Page1()
        val favFragment = Page2()
        val chatFragment = Page3()
        val profileFragment = ScrollingPage4()
        val navview = findViewById<NavigationView>(R.id.nav_view)
        setThatFragment(mapFragment)
        bottomNavView.setSelectedItemId(R.id.house)

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.house ->{
                    binding.navView.isVisible= false
                    binding.navView.clearAnimation()
                    setThatFragment(mapFragment)
                }
                R.id.likes ->{
                    binding.navView.isVisible= false
                    binding.navView.clearAnimation()
                    setThatFragment(favFragment)
                }
                R.id.chat ->{
                    binding.navView.isVisible= false
                    val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout)
                    fadeOut.interpolator = DecelerateInterpolator()
                    navview.startAnimation(fadeOut)
                    //binding.navView.clearAnimation()
                    setThatFragment(chatFragment)
                }
                R.id.profile ->{
                    binding.navView.isVisible= true


                    val animLeftNav = AnimationUtils.loadAnimation(this, R.anim.move_left_to_right)
                    animLeftNav.interpolator = DecelerateInterpolator()
                    navview.startAnimation(animLeftNav)
                }
            }
            true
        }


    }

    private fun setThatFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, fragment)
            commit()
        }

}