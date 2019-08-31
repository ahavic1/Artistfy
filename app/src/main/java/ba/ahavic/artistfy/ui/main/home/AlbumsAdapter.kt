package ba.ahavic.artistfy.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.databinding.ItemAlbumsBinding
import com.squareup.picasso.Picasso

typealias OnAlbumSelectedListener = (Album) -> Unit

class AlbumsAdapter(private val onAlbumSelectedListener: OnAlbumSelectedListener) :
    RecyclerView.Adapter<AlbumsVH>() {

    private val albums: MutableList<Album> by lazy { ArrayList<Album>() }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsVH {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return AlbumsVH.create(parent, inflater, onAlbumSelectedListener)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumsVH, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemId(position: Int): Long = albums[position].hashCode().toLong()

    fun setData(item: List<Album>) {
        albums.clear()
        albums.addAll(item)
        notifyDataSetChanged()
    }
}

class AlbumsVH(
    private val binding: ItemAlbumsBinding,
    private val onAlbumSelectedListener: OnAlbumSelectedListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            inflater: LayoutInflater,
            listener: OnAlbumSelectedListener
        ): AlbumsVH {

            val binding: ItemAlbumsBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_albums,
                parent,
                false
            )
            return AlbumsVH(binding, listener)
        }
    }

    fun bind(album: Album) {
        itemView.setOnClickListener {
            onAlbumSelectedListener(album)
        }
        binding.album = album

        if (album.image?.isNotEmpty() == true) {
            Picasso.get().load(album.image).into(binding.imageAlbumGraphic)
        }
    }
}
