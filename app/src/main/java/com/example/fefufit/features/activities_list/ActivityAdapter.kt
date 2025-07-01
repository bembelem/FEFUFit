package com.example.fefufit.features.activities_list

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fefufit.databinding.ItemActivityEntryBinding
import com.example.fefufit.databinding.ItemDateHeaderBinding

class ActivityAdapter(
    val isMy: Boolean,
    private var items: List<ActivityItem> = emptyList(),
    private val onActivityClick: (activityId: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_DATE = 0
        private const val VIEW_TYPE_ACTIVITY = 1
    }

    class DateViewHolder(private val binding: ItemDateHeaderBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ActivityItem.DateHeader) {
            binding.tvItemDateHeader.text = item.date
        }
    }

    class ActivityViewHolder(
        private val binding: ItemActivityEntryBinding,
        private val onActivityClick: (activityId: Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
            fun bind(item: ActivityItem.ActivityEntry) {
                with(binding) {
                    root.setOnClickListener {
                        onActivityClick(item.id)
                    }

                    tvDistance.text = item.distance
                    tvTimeDuration.text = item.timeDuration
                    tvActivityName.text = item.activityName
                    tvTimeFinishAgo.text = item.timeFinishAgo
                    tvUser.text = item.user

                    tvUser.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                }
            }
        }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is ActivityItem.DateHeader -> VIEW_TYPE_DATE
            is ActivityItem.ActivityEntry -> VIEW_TYPE_ACTIVITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_DATE -> {
                val binding = ItemDateHeaderBinding.inflate(inflater, parent, false)
                DateViewHolder(binding)
            }
            VIEW_TYPE_ACTIVITY -> {
                val binding = ItemActivityEntryBinding.inflate(inflater, parent, false)
                ActivityViewHolder(binding, onActivityClick)
            }
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ActivityItem.DateHeader -> (holder as DateViewHolder).bind(item)
            is ActivityItem.ActivityEntry -> (holder as ActivityViewHolder).bind(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ActivityItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}