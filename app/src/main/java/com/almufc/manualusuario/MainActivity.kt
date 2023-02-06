package com.almufc.manualusuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
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

        var isnavview= false
        val mapFragment = Page1()
        val favFragment = Page2()
        val chatFragment = Page3()
        val profileFragment = ScrollingPage4()
        val navview = findViewById<NavigationView>(R.id.nav_view)
        val animLeftNav = AnimationUtils.loadAnimation(this, R.anim.move_left_to_right)
        val slideout = AnimationUtils.loadAnimation(this, R.anim.slideout)
        setThatFragment(mapFragment)
        bottomNavView.setSelectedItemId(R.id.house)

/*PONER UNA VARIABLE QUE INDIQUE SI LA ANIMACION SE ESTA MOSTRANDO O NO Y EN FUNCION DE ESO OCULTARLA O NO*/

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.house ->{
                    binding.navView.isVisible= false
                    if(isnavview)
                    {
                        slideout.interpolator = DecelerateInterpolator()
                        navview.startAnimation(slideout)
                        isnavview=false
                    }
                    setThatFragment(mapFragment)
                }
                R.id.likes ->{
                    binding.navView.isVisible= false
                    if(isnavview)
                    {
                        slideout.interpolator = DecelerateInterpolator()
                        navview.startAnimation(slideout)
                        isnavview=false
                    }
                    setThatFragment(favFragment)
                }
                R.id.chat ->{
                    binding.navView.isVisible= false
                    if(isnavview)
                    {
                        slideout.interpolator = DecelerateInterpolator()
                        navview.startAnimation(slideout)
                        isnavview=false
                    }
                    setThatFragment(chatFragment)
                }
                R.id.profile ->{
                    binding.navView.isVisible= true
                    if(isnavview)
                    {
                        slideout.interpolator = DecelerateInterpolator()
                        navview.startAnimation(slideout)
                        isnavview=false
                    }
                    else
                    {
                        isnavview = true
                        animLeftNav.interpolator = DecelerateInterpolator()
                        navview.startAnimation(animLeftNav)
                    }
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