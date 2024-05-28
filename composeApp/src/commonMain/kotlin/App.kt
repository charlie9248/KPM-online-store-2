import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import api.ProductsRepositoryApi
import api.ProductsRepositoryApiImpl
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import home.HomeScreen
import home.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.core.context.startKoin
import org.koin.dsl.module

@Composable
@Preview
fun App() {

    initializeKoin()
    MaterialTheme {
        Navigator(HomeScreen()){
            SlideTransition(it)
        }
    }
}

val appModule  = module {
    single<ProductsRepositoryApi> { ProductsRepositoryApiImpl() }
    factory { HomeViewModel(get()) }
}

fun initializeKoin(){
    startKoin{
        modules(appModule)
    }
}