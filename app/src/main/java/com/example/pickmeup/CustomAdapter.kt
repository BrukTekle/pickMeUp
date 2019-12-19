package com.example.pickmeup
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class CustomAdapter(var context:Context,val rideList: ArrayList<Trip>,var img:IntArray): RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    /**
     * Sets the contents of an item at a given position in the RecyclerView.
     * Called by RecyclerView to display the data at a specified position.
     *
     * @param holder The view holder for that position in the RecyclerView.
     * @param position The position of the item in the RecycerView.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder?.img.setImageResource(img[position])
        holder?.from.text=rideList[position].from
        holder?.to.text=rideList[position].to
         holder?.date.text=rideList[position].date
//        holder?.price.append(productList[position].price.toString())// .text=productList[position].price.toString()
//        holder?.color.append(productList[position].color)

        holder.parentLayout.setOnClickListener{
            val intentDetail=Intent(context,RideDetail::class.java)
            intentDetail.putExtra("image",img[position])
            intentDetail.putExtra("name",rideList[position].driverName)
            intentDetail.putExtra("from",rideList[position].from)
            intentDetail.putExtra("to",rideList[position].to)
            intentDetail.putExtra("date",rideList[position].date)
            intentDetail.putExtra("phone",rideList[position].phone)
            intentDetail.putExtra("price",rideList[position].price)
            intentDetail.putExtra("plateNumber",rideList[position].plateNumber)
            intentDetail.putExtra("color",rideList[position].carColor)
            intentDetail.putExtra("carType",rideList[position].carType)
            intentDetail.putExtra("seats",rideList[position].seats)
            context.startActivity(intentDetail)
        }
//holder.parentlayout.setOnClickListener {
//            val intent = Intent(context, DetailActivity::class.java)
//            var res = text1[position]
//            Toast.makeText(context," $res clicked",Toast.LENGTH_LONG).show()
//            intent.putExtra("image", img[position])
//            intent.putExtra("name", text1[position])
//            intent.putExtra("detail",text3[position])
//            context.startActivity(intent)
//        }

    }
    /**
     * Inflates an item view and returns a new view holder that contains it.
     * Called when the RecyclerView needs a new view holder to represent an item.
     *
     * @param parent The view group that holds the item views.
     * @param viewType Used to distinguish views, if more than one type of item view is used.
     * @return a view holder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.activity_ride_item, parent, false)
        return MyViewHolder(v)
    }
    /**
     * Returns the size of the container that holds the data.
     * @return Size of the list of data.
     */
    override fun getItemCount(): Int {
        return rideList.size
    }

    /*RecyclerView.Adapter accepts the generic type of your Adapter inner class ViewHolder type.
  In this example Adapter class name is CustomAdapter and the MyViewHolder  is the inner class */
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img=itemView.findViewById<ImageView>(R.id.image1)
        val from=itemView.findViewById<TextView>(R.id.txtFrom)
        val to=itemView.findViewById<TextView>(R.id.txtTo)
        val date=itemView.findViewById<TextView>(R.id.txtDate)
        val parentLayout=itemView.findViewById<RelativeLayout>(R.id.playout)
    }
}