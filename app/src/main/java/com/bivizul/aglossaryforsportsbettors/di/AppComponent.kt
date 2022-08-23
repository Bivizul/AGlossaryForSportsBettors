package com.bivizul.aglossaryforsportsbettors.di

import com.bivizul.aglossaryforsportsbettors.ui.domanaka.DomanakaFragment
import com.bivizul.aglossaryforsportsbettors.ui.glossary.GlossaryFragment
import com.bivizul.aglossaryforsportsbettors.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(domanakaFragment: DomanakaFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(glossaryFragment: GlossaryFragment)

}