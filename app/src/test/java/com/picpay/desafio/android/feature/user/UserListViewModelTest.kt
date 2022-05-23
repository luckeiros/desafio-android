package com.picpay.desafio.android.feature.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.picpay.desafio.android.feature.user.repository.UserRepository
import com.picpay.desafio.android.feature.user.viewmodel.UserListViewModel
import com.picpay.desafio.android.feature.user.viewstate.UserListState
import com.picpay.desafio.android.factory.usersList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)

class UserListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val argumentCaptor: ArgumentCaptor<UserListState> = ArgumentCaptor.forClass(UserListState::class.java)

    @Mock
    lateinit var repository: UserRepository

    @Mock
    lateinit var observer: Observer<UserListState>

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        viewModel = UserListViewModel(repository)
        viewModel.viewState.userListState.observeForever(observer)
    }

    @Test
    fun `when request users is success should emit loading then success`() = runBlockingTest {
        /* given */
        Mockito.`when`(repository.getUsers()).thenReturn(usersList())

        /* when */
        viewModel.loadUsers()

        /* then */
        argumentCaptor.run {
            verify(observer, Mockito.times(2)).onChanged(capture())
            assert(this.allValues[0] is UserListState.Loading)
            assert(this.allValues[1] is UserListState.Display)
        }
    }

    @Test
    fun `when request users is failure should emit loading then error`() = runBlockingTest {
        /* given */
        Mockito.`when`(repository.getUsers()).thenThrow(RuntimeException())

        /* when */
        viewModel.loadUsers()

        /* then */
        argumentCaptor.run {
            verify(observer, Mockito.times(2)).onChanged(capture())
            assert(this.allValues[0] is UserListState.Loading)
            assert(this.allValues[1] is UserListState.Error)
        }
    }

}
