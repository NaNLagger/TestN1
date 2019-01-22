package com.nanlagger.testn1

import android.app.Application
import com.nanlagger.testn1.di.navigationModule
import com.nanlagger.testn1.di.networkModule
import com.nanlagger.testn1.di.repositoryModule
import com.nanlagger.testn1.di.schedulerModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import timber.log.Timber

class ProjectApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        bind() from instance(this@ProjectApplication as Application)
        import(navigationModule)
        import(schedulerModule)
        import(networkModule)
        import(repositoryModule)
    }

    override fun onCreate() {
        super. onCreate()
        kodeinTrigger?.trigger()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}