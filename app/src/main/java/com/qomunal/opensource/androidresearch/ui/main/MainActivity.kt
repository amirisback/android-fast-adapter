package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.common.ext.UnspecifiedTypeItem
import com.qomunal.opensource.androidresearch.common.ext.performUpdates
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding
import com.qomunal.opensource.androidresearch.model.MainModel
import com.qomunal.opensource.androidresearch.ui.main.adapter.MainItem
import com.qomunal.opensource.androidresearch.ui.main.adapter.MainItemListener

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    //create the managing FastAdapter, by passing in the itemAdapter
    private val fastAdapter: FastItemAdapter<UnspecifiedTypeItem> = FastItemAdapter()

    private val listItem = mutableListOf<UnspecifiedTypeItem>(initItem())

    private val router: MainRouter by lazy {
        MainRouter(this)
    }

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initUI() {
        binding.apply {

            //set our adapters to the RecyclerView
            rv.adapter = fastAdapter
            rv.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            fastAdapter.performUpdates(listItem)

            btnSubmit.setOnClickListener {
                tvResult.text = ""
                (fastAdapter.adapterItems as List<MainItem>).forEachIndexed { index, mainItem ->
                    Log.d("Fast Adapter", "index : $index")
                    Log.d("Fast Adapter", "ID : ${mainItem.model.id}")
                    Log.d("Fast Adapter", "DATA : ${mainItem.model.data}")
                    Log.d("Fast Adapter", "UUID : ${mainItem.model.uuid}")
                    tvResult.append(
                        "index \t: $index \n" +
                        "data \t\t: ${mainItem.model.data} \n" +
                        "uuid \t\t: ${mainItem.model.uuid} \n" +
                        "----------------------------------------- \n"
                    )
                }
            }

        }
    }

    override fun initObserver() {
        viewModel.apply {

        }
    }

    private fun initItem(): MainItem {
        val model = MainModel()
        return MainItem(model, object : MainItemListener {
            override fun onPlusItemClicked(item: MainItem) {
                val position = fastAdapter.getPosition(item)
                listItem.add(position + 1, initItem())
                fastAdapter.performUpdates(listItem)
            }

            override fun onMinusItemClicked(item: MainItem) {
                listItem.remove(item)
                fastAdapter.performUpdates(listItem)
            }
        })
    }

}