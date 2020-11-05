package ro.upt.ac.chiuitter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chiuit.view.*
import android.util.Log

class ChiuitRecyclerViewAdapter(
        private val chiuitList: ArrayList<Chiuit>,
        private val onClick: (Chiuit) -> (Unit))
    : RecyclerView.Adapter<ChiuitRecyclerViewAdapter.ChiuitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChiuitViewHolder {
        //TODO("8. Inflate the item layout and return the view holder")
        val viewLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chiuit, parent, false)
        return ChiuitViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        //TODO("9. Return the size of samples")
        return chiuitList.size;

    }

    override fun onBindViewHolder(holder: ChiuitViewHolder, position: Int) {
        //TODO("10. Bind the view holder with chiuit data sample")
        val ch = chiuitList[position]
        holder.bind(ch)

    }

    fun addItem(chiuit: Chiuit) {
        //TODO("12. Add the new item to the list then SMARTLY notify an addition")
        Log.d("Chiuit", chiuit.description)
        Log.d("list", chiuitList.toString())
        chiuitList.add(chiuit)
        Log.d("Update", "lista updatata")
        notifyItemInserted(getItemCount())
    }

    inner class ChiuitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.ibt_share.setOnClickListener { onClick(chiuitList[adapterPosition]) }
        }

        fun bind(chiuit: Chiuit) {
            //TODO("11. Set the text view with the content of chiuit's description")
            itemView.txv_content.setText(chiuit.description)
        }

    }

}