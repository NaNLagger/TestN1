package com.nanlagger.testn1.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nanlagger.testn1.data.OffersService
import com.nanlagger.testn1.domain.OfferDataSourceFactory
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

const val UI_THREAD = "UI_THREAD"
const val IO_THREAD = "IO_THREAD"

val networkModule = Kodein.Module("networkModule") {

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl("https://api.n1.ru/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    bind<OffersService>() with singleton { instance<Retrofit>().create(OffersService::class.java) }
}

val repositoryModule = Kodein.Module("repositoryModule") {

    bind<OfferDataSourceFactory>() with singleton { OfferDataSourceFactory(instance(), instance(UI_THREAD), instance(IO_THREAD)) }
}

val schedulerModule = Kodein.Module("SchedulerModule") {

    bind<Scheduler>(tag = UI_THREAD) with singleton { AndroidSchedulers.mainThread() }

    bind<Scheduler>(tag = IO_THREAD) with singleton { Schedulers.io() }
}

val navigationModule = Kodein.Module("NavigationModule") {

    bind<Cicerone<Router>>() with singleton { Cicerone.create() }

    bind<Router>() with singleton { instance<Cicerone<Router>>().router }

    bind<NavigatorHolder>() with singleton {
        instance<Cicerone<Router>>().navigatorHolder
    }
}
