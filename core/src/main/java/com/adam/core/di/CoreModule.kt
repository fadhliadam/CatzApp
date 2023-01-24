package com.adam.core.di

import androidx.room.Room
import com.adam.core.BuildConfig
import com.adam.core.data.source.local.LocalDataSource
import com.adam.core.data.source.local.room.CatDatabase
import com.adam.core.data.CatRepository
import com.adam.core.data.source.remote.RemoteDataSource
import com.adam.core.data.source.remote.network.ApiService
import com.adam.core.domain.repository.ICatRepository
import com.adam.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CatDatabase>().breedDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("AdamTampanTiadaDuanya".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CatDatabase::class.java, "Cat.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.thecatapi.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/MR3W/VK0WldHp/FlaBELlsY98SRylXXUrZ0fueygAgM=")
            .add(hostname, "sha256/cXjPgKdVe6iojP8s0YQJ3rtmDFHTnYZxcYvmYGFiYME=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val baseUrl = BuildConfig.BASEURL
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICatRepository> { CatRepository(get(), get(), get()) }
}