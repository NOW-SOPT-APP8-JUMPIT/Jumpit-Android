import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class MyResumeResponse(
    @SerialName("userId")
    val userId: Long,
    @SerialName("resumes")
    val resumes: List<Resume>
) {
    @Serializable
    data class Resume(
        @SerialName("id")
        val id: Long,
        @SerialName("title")
        val title: String,
        @SerialName("isPrivate")
        val isPrivate: Boolean,
    )
}