package com.example.imgurapidemo.domain.model

import com.google.gson.annotations.SerializedName

data class ImageData(
    @field:SerializedName("datetime")
    val datetime: Long,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("link")
    val link: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("images")
    val images: List<Image>?,
   /* @SerializedName("account_id")
    val account_id: Int,
    @SerializedName("account_url")
    val account_url: String,
    @SerializedName("ad_config")
    val ad_config: AdConfig,
    @SerializedName("ad_type")
    val ad_type: Int,
    @SerializedName("ad_url")
    val ad_url: String,
    @SerializedName("comment_count")
    val comment_count: Int,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_height")
    val cover_height: Int,
    @SerializedName("cover_width")
    val cover_width: Int,
    @SerializedName("datetime")

    @SerializedName("downs")
    val downs: Int,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("favorite_count")
    val favorite_count: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("images_count")
    val images_count: Int,
    @SerializedName("in_gallery")
    val in_gallery: Boolean,
    @SerializedName("in_most_viral")
    val in_most_viral: Boolean,
    @SerializedName("include_album_ads")
    val include_album_ads: Boolean,
    @SerializedName("is_ad")
    val is_ad: Boolean,
    @SerializedName("is_album")
    val is_album: Boolean,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("nsfw")
    val nsfw: Boolean,
    @SerializedName("points")
    val points: Int,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("section")
    val section: String,
   *//* @SerializedName("tags")
    val tags: List<Tag>,*//*
    @SerializedName("ups")
    val ups: Int,
    @SerializedName("views")
    val views: Int,
*/
)