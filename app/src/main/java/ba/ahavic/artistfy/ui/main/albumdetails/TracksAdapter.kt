package ba.ahavic.artistfy.ui.main.albumdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.ItemTrackBinding
import ba.ahavic.artistfy.ui.data.Track

class TracksAdapter : RecyclerView.Adapter<TrackVH>() {

    private val items: MutableList<Track> by lazy { ArrayList<Track>() }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return TrackVH.create(parent, inflater)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()

    fun setData(item: List<Track>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }
}

class TrackVH(private val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, inflater: LayoutInflater): TrackVH {

            val binding: ItemTrackBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_track,
                parent,
                false
            )
            return TrackVH(binding)
        }
    }

    fun bind(track: Track) {
        binding.track = track
    }
}
