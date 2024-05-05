import androidx.room.Entity
import androidx.room.PrimaryKey

// 例
@Entity(tableName = "")
data class SampleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val isFavorite: Boolean
)