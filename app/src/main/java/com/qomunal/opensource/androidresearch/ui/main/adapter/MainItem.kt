package com.qomunal.opensource.androidresearch.ui.main.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.qomunal.opensource.androidresearch.databinding.ItemMainBinding
import com.qomunal.opensource.androidresearch.model.MainModel

/**
 * Created by faisalamircs on 12/10/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


class MainItem(
    var model: MainModel,
    private var listener: MainItemListener
) : AbstractBindingItem<ItemMainBinding>() {

    override val type: Int
        get() = this.hashCode()

    override fun bindView(binding: ItemMainBinding, payloads: List<Any>) {
        binding.apply {

            etEdit.setText(model.data)

            etEdit.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                    
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    model.data = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                    
                }
            })

            btnAdd.setOnClickListener {
                listener.onPlusItemClicked(this@MainItem)
            }

            btnDecrease.setOnClickListener {
                listener.onMinusItemClicked(this@MainItem)
            }
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemMainBinding {
        return ItemMainBinding.inflate(inflater, parent, false)
    }
}