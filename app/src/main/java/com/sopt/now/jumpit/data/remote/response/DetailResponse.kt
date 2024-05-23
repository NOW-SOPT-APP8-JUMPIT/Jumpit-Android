package com.sopt.now.jumpit.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailResponse(
    @SerialName("position")
    val position: Position,
    @SerialName("company")
    val company: Company,
    @SerialName("skills")
    val skills: List<Skill>
)

@Serializable
data class Position(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("career")
    val career: String,
    @SerialName("education")
    val education: String,
    @SerialName("deadline")
    val deadline: String,
    @SerialName("location")
    val location: String,
    @SerialName("responsibilities")
    val responsibilities: String,
    @SerialName("qualifications")
    val qualifications: String,
    @SerialName("preferred")
    val preferred: String,
    @SerialName("benefits")
    val benefits: String,
    @SerialName("notice")
    val notice: String
)
@Serializable
data class Company(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("description")
    val description: String
)

@Serializable
data class Skill(
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String
)