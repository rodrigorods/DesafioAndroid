package com.picpay.desafio.android.api

import com.picpay.desafio.android.User
import com.picpay.desafio.android.util.MockWebServerResponsePath
import com.picpay.desafio.android.util.MockWebServerRule
import com.picpay.desafio.android.util.createTestApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PicPayServiceTest {

    @get:Rule
    val webServerRule = MockWebServerRule()

    private lateinit var picPayApi: PicPayService

    @Before
    fun setup() {
        picPayApi = createTestApi(webServerRule.provideUrl())
    }

    @Test
    @MockWebServerResponsePath("mock_user_list_response.json")
    fun getAllUsers() = runTest {
        val response = picPayApi.getUsers()
        assertEquals(response.size , 3)
        assertEquals(
            response[1],
            User(
                "https://randomuser.me/api/portraits/men/2.jpg",
                "Carli Carroll",
                2,
                "Constantin_Sawayn")
            )
    }
}
