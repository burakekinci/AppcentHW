package com.example.appcenthw.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenthw.MainActivity
import com.example.appcenthw.R
import com.example.appcenthw.databinding.SehirCardDesignBinding
import com.example.appcenthw.models.NearbyCity
import com.example.appcenthw.ui.SehirDetayFragment

class NearbyCitiesAdapter(private var nearbyCityList: ArrayList<NearbyCity>) :
    RecyclerView.Adapter<NearbyCitiesAdapter.NearbyCityCardDesign>() {

    class NearbyCityCardDesign(val nearbyCityCardBinding: SehirCardDesignBinding) :
        RecyclerView.ViewHolder(nearbyCityCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyCityCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val nearbyCityCardBinding = SehirCardDesignBinding.inflate(layoutInflater, parent, false)
        return NearbyCityCardDesign(nearbyCityCardBinding)
    }

    override fun onBindViewHolder(holder: NearbyCityCardDesign, position: Int) {
        val city = nearbyCityList[position]

        //her bir recyclerView itemi için şehir adını ve id'sini uygula
        holder.nearbyCityCardBinding.apply {
            sehirAdTextView.text = city.title
            sehirWoeidTextView.text = city.woeid.toString()
        }

        //tıklanan herhangi bir recyclerView itemi için şehir'id sini fragmenta gönder detaySayfasına yönlendir
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("woeid" to nearbyCityList.get(position).woeid)
            holder.itemView.findNavController()
                .navigate(R.id.action_sehirlerFragment_to_sehirDetayFragment, bundle)
        }
    }


    override fun getItemCount(): Int {
        return nearbyCityList.size
    }

}