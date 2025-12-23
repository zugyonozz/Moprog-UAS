package com.example.myapplication.api

import com.example.myapplication.model.HouseModel
import retrofit2.http.GET

/**
 * Interface ini bertugas sebagai jembatan (kontrak) antara aplikasi kita dan Server.
 * Retrofit akan membaca anotasi di sini untuk membuat request HTTP yang sesuai.
 */
interface ApiService {

    // @GET menandakan kita mau mengambil data (HTTP GET Method).
    // String "houses" di dalam kurung adalah ENDPOINT.
    // Jadi kalau Base URL kamu nanti: "https://api.myinterior.com/"
    // Maka fungsi ini akan nembak ke: "https://api.myinterior.com/houses"

    @GET("houses")
    suspend fun getHouses(): List<HouseModel>

    // Catatan untuk Rara:
    // 1. 'suspend': Wajib ada karena kita pakai Coroutines.
    //    Ini biar fungsi ini bisa dipause (non-blocking) saat nunggu data dari internet,
    //    jadi UI hp kamu nggak nge-freeze.

    // 2. 'List<HouseModel>': Ini tipe data yang kita harapkan dari server.
    //    Pastikan JSON dari server bentuknya Array [ {...}, {...} ].
    //    Kalau bentuknya Object { "data": [...] }, tipe kembaliannya harus disesuaikan lagi.
}
