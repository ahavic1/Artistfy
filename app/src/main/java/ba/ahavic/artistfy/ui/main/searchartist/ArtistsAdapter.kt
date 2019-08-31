package ba.ahavic.artistfy.ui.main.searchartist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.artist.Artist
import ba.ahavic.artistfy.databinding.ItemArtistBinding
import com.squareup.picasso.Picasso

typealias OnArtistSelectedListener = (Artist) -> Unit

class ArtistsAdapter(
    private val onArtistSelectedListener: OnArtistSelectedListener
) : RecyclerView.Adapter<ArtistVH>() {

    private val items: MutableList<Artist> by lazy { ArrayList<Artist>() }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistVH {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return ArtistVH.create(parent, inflater, onArtistSelectedListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArtistVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemId(position: Int): Long = items[position].mbid.hashCode().toLong()

    fun setData(item: List<Artist>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }
}

class ArtistVH(
    private val binding: ItemArtistBinding,
    private val onArtistSelectedListener: OnArtistSelectedListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            inflater: LayoutInflater,
            listener: OnArtistSelectedListener
        ): ArtistVH {

            val binding: ItemArtistBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_artist,
                parent,
                false
            )
            return ArtistVH(binding, listener)
        }
    }

    fun bind(artist: Artist) {
        itemView.setOnClickListener {
            onArtistSelectedListener(artist)
        }
        binding.artist = artist

        artist.imageUrl?.let {
            if (it.isNotEmpty()) {
                Picasso.get().load(it).into(binding.imageArtistGraphic)
            }
        }
    }
}
