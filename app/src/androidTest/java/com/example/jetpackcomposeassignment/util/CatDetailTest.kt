package com.example.jetpackcomposeassignment.utilimport com.example.jetpackcomposeassignment.feature_listcat.data.model.CatDetailimport com.example.jetpackcomposeassignment.feature_listcat.domain.use_case.CatDetailViewimport kotlin.random.Randomfun getCatDetailTest() = CatDetail(    id = "24253",    tags = listOf("Cute"),    mimetype = "image/png",    name = "Abyssinian",    temperament = listOf("Active", "Energetic", "Independent", "Intelligent", "Gentle"),    origin = "Egypt",    date = "2024-03-12T15:30:00Z",    description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",    childFriendly = Random.nextBoolean(),    energyLevel = Random.nextInt(0, 10),    dogFriendly = Random.nextBoolean(),    strangerFriendly = Random.nextBoolean(),    acceptLap = Random.nextBoolean(),    indoor = Random.nextBoolean(),)fun getCatDetailViewTest() = CatDetailView(    id = "24253",    mimetype = "image/png",    name = "Abyssinian",    tags = listOf("Cute", "Active", "Energetic", "Independent", "Intelligent", "Gentle"),    origin = "Egypt",    date = "2024-03-12T15:30:00Z",    description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",    importantInfo = listOf(    ))