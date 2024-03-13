package com.example.jetpackcomposeassignment.feature_listcat.presentation.detailimport com.example.jetpackcomposeassignment.common.RequestResourceimport com.example.jetpackcomposeassignment.common.UiTextimport com.example.jetpackcomposeassignment.feature_listcat.data.model.Catimport com.example.jetpackcomposeassignment.feature_listcat.domain.CatUseCasesimport com.example.jetpackcomposeassignment.feature_listcat.domain.use_case.CatDetailViewimport com.example.jetpackcomposeassignment.feature_listcat.presentation.cats.CatStateimport com.example.jetpackcomposeassignment.feature_listcat.util.assertInstanceOfimport com.example.jetpackcomposeassignment.feature_listcat.util.assertSameClassimport com.example.jetpackcomposeassignment.feature_listcat.util.getCatDetailTestimport com.example.jetpackcomposeassignment.feature_listcat.util.getCatDetailViewTestimport io.mockk.clearAllMocksimport io.mockk.coEveryimport io.mockk.mockkimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.cancelimport kotlinx.coroutines.flow.flowOfimport kotlinx.coroutines.test.UnconfinedTestDispatcherimport kotlinx.coroutines.test.resetMainimport kotlinx.coroutines.test.runTestimport kotlinx.coroutines.test.setMainimport org.junit.Afterimport org.junit.Beforeimport org.junit.Testinternal class CatDetailViewModelTest {    private lateinit var catUseCases: CatUseCases    private lateinit var catDetailViewModel: CatDetailViewModel    private val testDispatcher = UnconfinedTestDispatcher()    @Before    fun setup() {        Dispatchers.setMain(testDispatcher)        catUseCases = mockk()        catDetailViewModel = CatDetailViewModel(catUseCases)    }    @After    fun tearDown() {        Dispatchers.resetMain()        testDispatcher.cancel()        clearAllMocks()    }    @Test    fun `should update catState on successful cat retrieval`() = runTest {        // Arrange        val catId = "123"        val catMock = getCatDetailViewTest()        val successResult = RequestResource.Success(catMock)        coEvery { catUseCases.getOneCat(id = catId) } returns flowOf(successResult)        // Act        catDetailViewModel.on(CatDetailEvent.FindOneCat(catId))        // Assert        assertInstanceOf<CatState.CatSuccess>(catDetailViewModel.catState.value)        assertSameClass(            (catDetailViewModel.catState.value as CatState.CatSuccess).cat,            catMock        )    }    @Test    fun `should update catState on error during cat retrieval`() {        // Arrange        val catId = "456"        val uiText = UiText.Dynamic("Some error message")        val errorResult = RequestResource.Error<CatDetailView>(            message = uiText        )        coEvery { catUseCases.getOneCat(id = catId) } returns flowOf(errorResult)        // Act        catDetailViewModel.on(CatDetailEvent.FindOneCat(catId))        // Assert        assertInstanceOf<CatState.TryAgain>(catDetailViewModel.catState.value)        assertSameClass(            uiText,            (catDetailViewModel.catState.value as CatState.TryAgain).errorMessage        )    }    @Test    fun `should update catState on loading during cat retrieval`() {        // Arrange        val catId = "789"        val loadingResult = RequestResource.Loading<CatDetailView>()        coEvery { catUseCases.getOneCat(id = catId) } returns flowOf(loadingResult)        // Act        catDetailViewModel.on(CatDetailEvent.FindOneCat(catId))        // Assert        assertInstanceOf<CatState.Loading>(catDetailViewModel.catState.value)        assertSameClass(            CatState.Loading,            catDetailViewModel.catState.value        )    }}