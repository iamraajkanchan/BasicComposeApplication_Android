package com.chinky.family.usecases

import com.chinky.family.data.repository.UserRepository
import com.chinky.family.domain.model.User
import com.chinky.family.domain.usecase.HandleUserUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class HandleUserUseCaseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var handleUserUseCase: HandleUserUseCase

    @Before
    fun setup() {
        userRepository = mock(UserRepository::class.java)
        handleUserUseCase = HandleUserUseCase(userRepository)
    }

    @Test
    fun shouldGetUserFromAPI() = runTest {
        // Arrange
        val expectedUsers = emptyList<User>()
        val expectedResponse: List<User>? = Response.success(expectedUsers) as List<User>?

        // Correctly mock the suspend function to return Response<List<User>>
        whenever(userRepository.getUsers()).thenReturn(expectedResponse)

        // Act
        val actualUsers = handleUserUseCase.getUsers()

        // Assert
        assertEquals(expectedUsers, actualUsers)
    }


}