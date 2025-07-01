package com.example.fefufit.features.activity_new

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fefufit.R
import com.example.fefufit.data.enums.ActivityType
import com.example.fefufit.databinding.ItemActivityTypeBinding
import com.google.android.material.card.MaterialCardView

class BottomSheetAdapter(
    private val items: List<ActivityType>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var _selectedPosition: Int = 0

    class BottomSheetViewHolder(private val binding: ItemActivityTypeBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(activityTypeName: String) {
                binding.tvActivityType.text = activityTypeName
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemActivityTypeBinding.inflate(inflater, parent, false)

        return BottomSheetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun setCardColorBorder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = holder.itemView as MaterialCardView
        val context = holder.itemView.context
        val color = if (position == _selectedPosition)
            ContextCompat.getColor(context, R.color.blue) else
            ContextCompat.getColor(context, R.color.border_card_grey)

        card.strokeColor = color
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        setCardColorBorder(holder, position)

        holder.itemView.setOnClickListener {
            val previousPosition = _selectedPosition
            _selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(_selectedPosition)
        }

        (holder as BottomSheetViewHolder).bind(items[position].typeName)
    }

    fun getActivityType(): ActivityType {
        return items[_selectedPosition]
    }
}