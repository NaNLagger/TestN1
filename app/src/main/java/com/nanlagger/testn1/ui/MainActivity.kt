package com.nanlagger.testn1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nanlagger.testn1.R
import com.nanlagger.testn1.di.mainModule
import com.nanlagger.testn1.ui.common.navigation.OfferListScreen
import com.nanlagger.testn1.ui.common.navigation.SupportAppNavigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MainActivity : AppCompatActivity(), KodeinAware {
    private val _parentKodein by closestKodein()
    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein)
        bind() from instance(this@MainActivity)
        import(mainModule)
    }

    private val navigationHolder: NavigatorHolder by instance()
    private val router: Router by instance()

    private val navigator: SupportAppNavigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, R.id.container_screens)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationHolder.removeNavigator()
        router.newRootScreen(OfferListScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}