package com.sopt.now.jumpit.data.remote.response

data class SearchResult(
    val id: Int,
    val company: String,
    val title: String,
    val image: String,
    val tags: String,
) {
    companion object {
        val dummyResults = listOf(
            SearchResult(
                id = 0,
                company = "토스뱅크",
                title = "[토스뱅크] Frontend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "React · TypeScript · Next.js",
            ),
            SearchResult(
                id = 1,
                company = "네이버",
                title = "[네이버] Backend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Java · Spring · JPA",
            ),
            SearchResult(
                id = 2,
                company = "카카오",
                title = "[카카오] Android Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Kotlin · Android · MVVM",
            ),
            SearchResult(
                id = 3,
                company = "우아한형제들",
                title = "[우아한형제들] iOS Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Swift · iOS · VIPER",
            ),
            SearchResult(
                id = 4,
                company = "라인",
                title = "[라인] Frontend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Vue.js · JavaScript · Webpack",
            ),
            SearchResult(
                id = 5,
                company = "쿠팡",
                title = "[쿠팡] Backend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Python · Django · REST API",
            ),
            SearchResult(
                id = 6,
                company = "우아한형제들",
                title = "[우아한형제들] Android Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Kotlin · Android · MVVM",
            ),
            SearchResult(
                id = 7,
                company = "네이버",
                title = "[네이버] iOS Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Swift · iOS · VIPER",
            ),
            SearchResult(
                id = 8,
                company = "카카오",
                title = "[카카오] Backend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Java · Spring · JPA",
            ),
            SearchResult(
                id = 9,
                company = "토스뱅크",
                title = "[토스뱅크] Frontend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "React · TypeScript · Next.js",
            ),
            SearchResult(
                id = 10,
                company = "라인",
                title = "[라인] Backend Developer",
                image = "https://avatars.githubusercontent.com/u/127238018?v=4",
                tags = "Python · Django · REST API",
            ),
        )
    }
}
