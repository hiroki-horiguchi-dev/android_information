/// NOTE 3つの実装例で model について書いた

/// NOTE 一般的な例
package com.example.androidinformationrepository.androidimpl.model_entity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetail(
    val id: String,
    val volumeInfo: VolumeInfo?
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null,
    val small: String? = null,
    val medium: String? = null
)


/// NOTE Json レスポンス名と一致させたくない場合
@Serializable
data class MarsPhoto(
    val id: String,
    /// Json レスポンスと同名の変数名を宣言することで、モデルを API レスポンス返却時に指定してあげるといい感じにやってくれる。
    /// ただし、Json レスポンスの名称がモデルで宣言する変数名として不適切な場合、
    /// 以下のようにすれば、アプリで使う変数名と Json レスポンスのプロパティ名を置き換えることができる。
    @SerialName(value = "img_src")
    val imgSrc: String
)

/// NOTE Drawable や String リソースなどを使う場合はアノテーションで視覚化してあげたほうがいいね
data class Dog(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)