package com.udacity.shoestore.util.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.view_shoe.view.*

class ShoeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_shoe, this)
    }

    fun setData(shoe: Shoe) {
        name.text = resources.getString(R.string.name_shoe, shoe.name)
        company.text = resources.getString(R.string.company_shoe, shoe.company)
        size.text = resources.getString(R.string.size_shoe, shoe.size)
        description.text = resources.getString(R.string.description_shoe, shoe.description)
    }
}