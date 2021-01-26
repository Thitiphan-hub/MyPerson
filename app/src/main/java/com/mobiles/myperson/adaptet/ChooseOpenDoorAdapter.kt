package com.mobiles.myperson.adaptet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiles.myperson.R
import com.mobiles.myperson.model.getdoor.GetDoorInfoResponse
import com.mobiles.myperson.ui.getdoor.ChooseOpenDoorActivity
import kotlinx.android.synthetic.main.view_item_door.view.*

class ChooseOpenDoorAdapter(
    var doorList: ArrayList<GetDoorInfoResponse>,
    var onOpenClickListener: ChooseOpenDoorActivity
) : RecyclerView.Adapter<ChooseOpenDoorAdapter.DoorVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorVHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_door, parent, false)
        return DoorVHolder(itemView)

    }

    override fun getItemCount() =   doorList.size

    override fun onBindViewHolder(holder: DoorVHolder, position: Int) {
        val current = doorList[position]
        holder.bind(current, onOpenClickListener)

    }

    inner class DoorVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namehead = itemView.textview_Head_office
        var namefront = itemView.textview_Front_door

        fun bind(getDoorInfoResponse: GetDoorInfoResponse, act: onOpenClickListener) {
            namehead?.text = getDoorInfoResponse.Floor
            namefront?.text = getDoorInfoResponse.DoorShowName

            itemView.setOnClickListener {
                act.onClick(adapterPosition)

            }

        }

    }

}


interface onOpenClickListener {
    fun onClick(position: Int)
}


